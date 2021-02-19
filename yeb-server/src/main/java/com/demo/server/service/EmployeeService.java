package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.Employee;
import com.demo.server.result.Result;
import com.demo.server.result.ResultPage;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 查询员工信息（分页）
     * @param currentPage
     * @param pageSize
     * @param employee
     * @param dateScope
     * @return
     */
    ResultPage getEmployeeList(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] dateScope);

    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    Result addEmployyeeRecord(Employee employee);

    /**
     * 获取自增后的员工工号
     * @return
     */
    Result maxWorkID();

    /**
     * 导出员工数据查询方法
     * @param o
     * @return
     */
    List<Employee> getAllEmployeeList(Integer id);
}
