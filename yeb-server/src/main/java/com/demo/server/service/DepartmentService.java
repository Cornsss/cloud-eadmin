package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.Department;
import com.demo.server.result.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 获取所有部门信息
     * @return
     */
    List<Department> getDepartments(Integer pid);

    /**
     * 添加部门信息
     * @param department
     * @return
     */
    Result addDep(Department department);

    /**
     * 删除部门信息
     * @param
     * @return
     */
    Result deleteDep(Integer id);
}
