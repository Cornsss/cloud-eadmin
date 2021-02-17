package com.demo.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.server.entity.MenuRole;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Component
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新菜单信息
     * @param rid
     * @param mids
     * @return
     */
    Integer insertMenuRoleRecord(Integer rid, Integer[] mids);
}
