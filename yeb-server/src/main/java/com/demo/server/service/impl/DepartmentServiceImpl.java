package com.demo.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.Department;
import com.demo.server.mapper.DepartmentMapper;
import com.demo.server.result.Result;
import com.demo.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments(Integer pid) {
        List<Department> allDepartments = departmentMapper.getAllDepartments(pid);
        return allDepartments;
    }

    @Override
    public Result addDep(Department department) {
        Result result = Result.ok();
        department.setEnabled("1");
        departmentMapper.addDep(department);
        if(1 != department.getResult()){// 这里犯过错误，调用过存储过程返回的结果会存储在对象中
            result.setCode(201);
            result.setMessage("添加失败");
        }else {
            Department rtndept = departmentMapper.selectById(department.getId());
            result.data("dept",rtndept);
        }
        return result;
    }

    @Override
    public Result deleteDep(Integer id) {
        Department department = new Department();
        department.setId(id);
        Result result = Result.ok();
        departmentMapper.deleteDep(department);
        Integer rtnResult = department.getResult();
        if(rtnResult == -2){
            result.setCode(201);
            result.setMessage("删除当前部门失败，原因：当前部门存在子部门");
        }else if (rtnResult == -1){
            result.setCode(201);
            result.setMessage("删除当前部门失败，原因：当前部门仍存在员工");
        }
        return result;
    }
}
