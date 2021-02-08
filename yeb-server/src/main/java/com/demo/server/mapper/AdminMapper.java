package com.demo.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.server.entity.Admin;
import com.demo.server.entity.Role;
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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户id获取角色列表
     * @param id
     * @return
     */
    public List<Role> getRolesById(int id);
}
