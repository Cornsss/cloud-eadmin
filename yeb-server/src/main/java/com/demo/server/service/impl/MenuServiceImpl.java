package com.demo.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.Admin;
import com.demo.server.entity.Menu;
import com.demo.server.mapper.MenuMapper;
import com.demo.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Menu> getMenuByUserid() {
        // 获取用户id
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        List<Menu> allMenusByAdminId = (List)value.get("menu_" + admin.getId());
        if(CollectionUtils.isEmpty(allMenusByAdminId)){// 不存在缓存中
            allMenusByAdminId = menuMapper.getAllMenusByAdminId(admin.getId());
            // 将数据存入redis中
            value.set("menu_"+admin.getId(),allMenusByAdminId);
        }
        return allMenusByAdminId;
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        List<Menu> allMenusWithRole = menuMapper.getAllMenusWithRole();
        return allMenusWithRole;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
