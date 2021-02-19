package com.demo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.Role;
import com.demo.server.util.JwtTokenUtil;
import com.demo.server.entity.Admin;
import com.demo.server.mapper.AdminMapper;
import com.demo.server.result.Result;
import com.demo.server.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenheader;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 用户名或密码错误
        if(userDetails == null || !encoder.matches(password,userDetails.getPassword())){
            return Result.error();
        }

        // 更新登陆用户信息放入springsecurity全文中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 登陆成功，生成令牌
        String token = jwtTokenUtil.generateUserLoginToken(userDetails);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenheader);

        return new Result(true,200,"登陆成功",map);
    }

    @Override
    public Admin getAdminByUserName(String name) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",name)
                                                              .eq("enabled",true));
    }
    
//    @Override
    public List<Role> getRolesById(Integer id) {
        // 获取用户id
        List<Role> rolesById = adminMapper.getRolesById(id);
        return rolesById;
    }

    @Override
    public List<Admin> getAllAdmin(String keywords) {
        Admin principal = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return adminMapper.getAllAdmin(principal.getId(),keywords);
    }
}
