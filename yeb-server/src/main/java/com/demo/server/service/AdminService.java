package com.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.server.entity.Admin;
import com.demo.server.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
public interface AdminService extends IService<Admin> {

    Result login(String username, String password);

    Admin getAdminByUserName(String name);
}
