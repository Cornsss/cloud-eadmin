package com.demo.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.server.entity.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单列表
     * @param id
     * @return
     */
    public List<Menu> getAllMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    public List<Menu> getAllMenusWithRole();

    /**
     * 查询所有菜单信息
     * @return
     */
    public List<Menu> getAllMenus();
}
