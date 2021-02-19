package com.demo.server.controller;


import com.demo.server.entity.Department;
import com.demo.server.result.Result;
import com.demo.server.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有部门信息")
    @GetMapping("/")
    public List<Department> getDepartments(){
        List<Department> departments = departmentService.getDepartments(-1);
        return departments;
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public Result addDepartment(@RequestBody Department department){
        Result result = departmentService.addDep(department);
        return result;
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable Integer id){
        Result result = departmentService.deleteDep(id);
        return result;
    }

}
