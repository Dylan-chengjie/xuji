package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.Role;
import com.fb.xujimanage.entity.RoleMenuPriviRelate;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-24 19:18
 * @description:接口接口类
 * @version:
 */
public interface RoleService {

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    CommonResult addRole(Role role);

    /**
     * 角色新增菜单角色
     *
     * @param roleMenuPriviRelates
     * @return
     */
    CommonResult addRolePrivileged(List<RoleMenuPriviRelate> roleMenuPriviRelates);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    CommonResult updateRole(Role role);

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    CommonResult batchDeleteRole(List<Long> ids);

    /**
     * 分页查询角色列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonResult<PageInfo<List<Role>>> pageRole(Integer pageNum, Integer pageSize);
}
