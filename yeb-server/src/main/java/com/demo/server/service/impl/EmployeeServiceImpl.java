package com.demo.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.Employee;
import com.demo.server.mapper.EmployeeMapper;
import com.demo.server.result.Result;
import com.demo.server.result.ResultPage;
import com.demo.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResultPage getEmployeeList(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] dateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage,pageSize);
        // 查询员工
        IPage employeeListByPage = employeeMapper.getEmployeeListByPage(page, employee, dateScope);
        // 返回结果
        ResultPage resultPage = new ResultPage(employeeListByPage.getTotal(),employeeListByPage.getRecords());
        return resultPage;
    }

    @Override
    public Result addEmployyeeRecord(Employee employee) {
        // 计算员工工龄
        LocalDateTime beginContract = employee.getBeginContract();
        LocalDateTime endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        double contractTerm = Double.parseDouble(decimalFormat.format(days / 365));
        employee.setContractTerm(contractTerm);
        // 插入数据
        if(employeeMapper.insert(employee) == 1){
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result maxWorkID() {
        Result result = Result.ok();
        Integer maxWorkID = employeeMapper.maxWorkID();
        maxWorkID = Integer.parseInt(String.format("%08d", maxWorkID + 1));
        Map<String,Object> map = new HashMap<>();
        map.put("workId",maxWorkID);
        result.setData(map);
        return result;
    }

    @Override
    public List<Employee> getAllEmployeeList(Integer id) {
        List<Employee> list = employeeMapper.getAllEmployeeList(id);
        return list;
    }


}
