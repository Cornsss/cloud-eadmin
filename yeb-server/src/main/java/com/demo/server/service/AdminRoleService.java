package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.AdminRole;
import com.demo.server.result.Result;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Component
public interface AdminRoleService extends IService<AdminRole> {

    Result updateAdminRole(Integer adminid, Integer[] rids);
}
