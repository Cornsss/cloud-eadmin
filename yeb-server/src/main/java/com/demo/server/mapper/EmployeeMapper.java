package com.demo.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.server.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Component
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage getEmployeeListByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("dateScope") LocalDate[] dateScope);

    Integer maxWorkID();

    List<Employee> getAllEmployeeList(Integer id);
}
