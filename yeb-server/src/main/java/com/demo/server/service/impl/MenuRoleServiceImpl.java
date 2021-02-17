package com.demo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.server.entity.MenuRole;
import com.demo.server.mapper.MenuRoleMapper;
import com.demo.server.result.Result;
import com.demo.server.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新菜单信息
     * @param rid
     * @param mids
     */
    @Override
    @Transactional
    public Result updateMenuRoleWithRid(Integer rid, Integer[] mids) {
        Result result = Result.error();
        // 先删除该角色id下的菜单信息
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<MenuRole>().eq("rid", rid);
        menuRoleMapper.delete(wrapper);
        if(mids == null || mids.length == 0){
            result.setMessage("更新成功");
        }else {
            Integer integer = menuRoleMapper.insertMenuRoleRecord(rid, mids);
            if(integer == mids.length){
                result.setMessage("更新成功");
            }
        }
        return result;
    }
}
