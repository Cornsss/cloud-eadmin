package com.demo.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.server.entity.MenuRole;
import com.demo.server.result.Result;
import com.demo.server.service.MenuRoleService;
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
@RequestMapping("/system/cfg")
public class MenuRoleController {

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "根据角色ID查询菜单信息")
    @GetMapping("/mid/{rid}")
    public List<Integer> getAllMenusByRid(@PathVariable Integer rid){
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<MenuRole>().eq("rid", rid);
        return menuRoleService.list(wrapper).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation(value = "更新菜单信息")
    @PutMapping("/mid/{rid}")
    public Result updateMenu(Integer rid, Integer[] mids){
        Result result = menuRoleService.updateMenuRoleWithRid(rid, mids);
        return result;
    }

}
