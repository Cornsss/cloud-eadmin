package com.demo.server.controller;

import com.demo.server.entity.Admin;
import com.demo.server.entity.AdminLoginParams;
import com.demo.server.result.Result;
import com.demo.server.service.AdminRoleService;
import com.demo.server.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "登陆返回token")
    @PostMapping("/login")
    public Result login(@RequestBody AdminLoginParams loginParams){
        return adminService.login(loginParams.getUsername(),loginParams.getPassword());
    }

    @GetMapping("/admin/info")
    @ApiOperation(value = "获取当前登录用户信息")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        Admin admin = adminService.getAdminByUserName(principal.getName());
        admin.setRoles(adminService.getRolesById(admin.getId()));
        admin.setPassword(null);
        return admin;
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public Result logout() {
        return new Result(true,200,"退出成功",null);
    }

}
