package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Role;
import com.fb.xujimanage.entity.RoleMenuPriviRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author chengjie
 * @date 2020-08-25 13:59
 * @description:角色相关
 * @version:
 */
@Repository
public interface RoleDao {
    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    Integer addRole(Role role);

    /**
     * 角色新增菜单角色
     *
     * @param roleMenuPriviRelate
     * @return
     */
    Integer addRolePrivileged(RoleMenuPriviRelate roleMenuPriviRelate);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    Integer updateRole(Role role);

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    Integer batchDeleteRole(@Param("ids") List<Long> ids);

    /**
     * 批量删除角色菜单栏角色
     *
     * @param roleIds
     * @return
     */
    Integer batchDeleteRolePrivilegedRelate(@Param("roleIds") Set<Long> roleIds);

    /**
     * 分页查询角色列表
     *
     * @return
     */
    List<Role> pageRole();
}
