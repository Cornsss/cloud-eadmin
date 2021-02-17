package com.demo.server.controller;


import com.demo.server.entity.Menu;
import com.demo.server.entity.Role;
import com.demo.server.result.Result;
import com.demo.server.result.ResultCode;
import com.demo.server.service.MenuService;
import com.demo.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取所有角色列表")
    @GetMapping("/")
    public List<Role> getAllPositions(){
        return roleService.list();
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/")
    public Result addRole(@RequestBody Role role){
        Result result = Result.ok();
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "更新角色信息")
    @PutMapping("/")
    public Result updateRoles(@RequestBody Role role){
        Result result = Result.ok();
        if(roleService.updateById(role)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable Integer id){
        Result result = Result.ok();
        if(roleService.removeById(id)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

}
