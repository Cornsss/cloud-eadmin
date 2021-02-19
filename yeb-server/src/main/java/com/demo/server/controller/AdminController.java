package com.demo.server.controller;


import com.demo.server.entity.Admin;
import com.demo.server.result.Result;
import com.demo.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "查询所有的操作用户")
    @GetMapping("/")
    public List<Admin> getAdminInfo(String keywords){
        List<Admin> allAdmin = adminService.getAllAdmin(keywords);
        return allAdmin;
    }

    @ApiOperation(value = "更新操作用户")
    @PutMapping("/")
    public Result updateAdminInfo(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "删除操作用户")
    @DeleteMapping("/{id}")
    public Result deleteAdminInfo(@PathVariable Integer id){
        if(adminService.removeById(id)){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}
