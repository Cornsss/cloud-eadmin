package com.demo.server.controller;


import com.demo.server.result.Result;
import com.demo.server.service.AdminRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "设置操作员角色")
    @PutMapping("/adminrole/{rid}")
    public Result updateAdminRole(Integer adminid, Integer[] rids){
        Result result = adminRoleService.updateAdminRole(adminid, rids);
        return result;
    }

}
