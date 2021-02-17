package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    public List<Menu> getMenuByUserid();

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
