package com.demo.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.server.entity.Department;
import org.springframework.stereotype.Component;

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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer did);

    void addDep(Department department);

    void deleteDep(Department department);
}
