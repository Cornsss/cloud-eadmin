package com.demo.server.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    // 密钥
    @Value("${jwt.secret}")
    private String secret;

    // 失效时间
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateUserLoginToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateUserLoginToken(claims);
    }

    public Date generateExpirationTime(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 根据用户信息生成token
     * @param claims
     * @return
     */
    public String generateUserLoginToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationTime())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /***
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String userName;
        try {
            Claims claims = getClaimsFormToken(token);
            userName = claims.getSubject();
        }catch (Exception e){
            throw e;
        }
        return userName;
    }

    /**
     * 从token中获取荷载信息
     * @param token
     * @return
     */
    public Claims getClaimsFormToken(String token){
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            throw e;
        }
        return body;
    }

    /**
     * 判断token是否失效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isValidToken(String token,UserDetails userDetails){
        String userNameFromToken = getUserNameFromToken(token);
        return userNameFromToken.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    /**
     * 判断荷载中的token失效时间是否大于当前时间
     * @param token
     * @return
     */
    public boolean isTokenExpire(String token){
        Date expiredDateFromToken = getExpiredDateFromToken(token);
        return expiredDateFromToken.before(new Date());
    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token){
        Claims claimsFormToken = getClaimsFormToken(token);
        Date expiration = claimsFormToken.getExpiration();
        return expiration;
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return  !isTokenExpire(token);
    }

    /**
     * 刷新token:修改登陆的时间
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claimsFormToken = getClaimsFormToken(token);
        claimsFormToken.put(CLAIM_KEY_CREATED,new Date());
        return generateUserLoginToken(claimsFormToken);
    }
}
