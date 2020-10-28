package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.RoleDao;
import com.fb.xujimanage.dao.UserRoleRelateDao;
import com.fb.xujimanage.entity.Role;
import com.fb.xujimanage.entity.RoleMenuPriviRelate;
import com.fb.xujimanage.service.RoleService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: xujimanage
 * @description: 角色接口实现类
 * @author: chengjie
 * @date: Created in 2020/8/24 14:34
 **/
@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private UserRoleRelateDao userRoleRelateDao;

    public RoleServiceImpl(RoleDao roleDao, UserRoleRelateDao userRoleRelateDao) {
        this.roleDao = roleDao;
        this.userRoleRelateDao = userRoleRelateDao;
    }

    @Override
    public CommonResult addRole(Role role) {
        role.setId(IdUtil.getUUID());
        roleDao.addRole(role);
        return CommonResult.ok("添加角色成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addRolePrivileged(List<RoleMenuPriviRelate> roleMenuPriviRelates) {
        if (!CollectionUtils.isEmpty(roleMenuPriviRelates)) {
            Set<Long> roleIds = roleMenuPriviRelates.stream().map(RoleMenuPriviRelate::getRoleId).collect(Collectors.toSet());
            roleDao.batchDeleteRolePrivilegedRelate(roleIds);
        }
        roleMenuPriviRelates.forEach(roleMenuPriviRelate -> {
            roleMenuPriviRelate.setId(IdUtil.getUUID());
            roleDao.addRolePrivileged(roleMenuPriviRelate);
        });
        return CommonResult.ok("角色新增菜单角色");
    }

    @Override
    public CommonResult updateRole(Role role) {
        String updateBy = TokenUtils.getUserNum();
        role.setUpdateBy(updateBy);
        return roleDao.updateRole(role) > 0 ?
                CommonResult.ok("更新角色成功") : CommonResult.fail("更新角色失败");
    }

    @Override
    public CommonResult batchDeleteRole(List<Long> ids) {
        if (userRoleRelateDao.countUserByRoleId(ids) > 0) {
            return CommonResult.fail("角色下存在用户，禁止删除");
        }
        return roleDao.batchDeleteRole(ids) > 0 ?
                CommonResult.ok("删除角色成功") : CommonResult.fail("删除角色失败");
    }

    @Override
    public CommonResult<PageInfo<List<Role>>> pageRole(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleDao.pageRole();
        return CommonResult.ok("分页查询角色列表成功", new PageInfo<Role>(roles));
    }
}
