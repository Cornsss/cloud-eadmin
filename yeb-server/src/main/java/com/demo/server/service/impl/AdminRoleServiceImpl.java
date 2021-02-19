package com.demo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.AdminRole;
import com.demo.server.mapper.AdminRoleMapper;
import com.demo.server.result.Result;
import com.demo.server.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public Result updateAdminRole(Integer adminid, Integer[] rids) {
        // 先删除角色
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("admin_id", adminid));
        if(rids == null || rids.length == 0){
            return Result.ok();
        }
        // 再添加角色
        int count = adminRoleMapper.addAdminRole(adminid,rids);
        if(count == rids.length){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}
