package com.demo.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.server.entity.Menu;
import com.demo.server.entity.MenuRole;
import com.demo.server.mapper.MenuMapper;
import com.demo.server.result.Result;
import com.demo.server.service.MenuRoleService;
import com.demo.server.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "根据用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByUserid(){
        List<Menu> menuByUserid = menuService.getMenuByUserid();
        return menuByUserid;
    }

    @ApiOperation(value = "查询所有菜单信息")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

}
