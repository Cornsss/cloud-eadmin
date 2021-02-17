package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.MenuRole;
import com.demo.server.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
public interface MenuRoleService extends IService<MenuRole> {

    /**
     * 更新菜单信息
     * @param rid
     * @param mids
     */
    Result updateMenuRoleWithRid(Integer rid, Integer[] mids);
}
