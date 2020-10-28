package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.entity.vo.UserRoleVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-24 19:18
 * @description:用户信息接口类
 * @version:
 */
public interface UserManageSysService {
    /**
     * 更新用户信息
     *
     * @param userManageSysList
     * @return
     */
    void batchSyncUserManageSys(List<UserManageSys> userManageSysList,List<String> retainUserNames);

    /**
     * 查询用户信息
     *
     * @param userName
     * @param password
     * @returna
     */
    CommonResult<UserManageSysVo> authLogin(String userName, String password, HttpServletRequest httpRequest);

    /**
     * 更新用户信息
     *
     * @param userManageSys
     * @return CommonResult
     */
    CommonResult updateUserManageSys(UserManageSys userManageSys);

    /**
     * 分页查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param departmentList
     * @param usageStatus
     * @param title
     * @return
     */
    CommonResult<PageInfo<List<UserManageSysVo>>> pageUserManageSys(Integer pageNum, Integer pageSize, List<String> departmentList,
                                                                    Integer usageStatus, String title, Long roleId, Boolean our);

    /**
     * 新增用户角色
     *
     * @param userRoleVos
     * @return CommonResult
     */
    CommonResult addUserRole(List<UserRoleVo> userRoleVos);

    /**
     * 新增用户角色
     *
     * @param userRoleVo
     * @return CommonResult
     */
    CommonResult updateUserRole(UserRoleVo userRoleVo);

    /**
     * 查询用户菜单栏
     *
     * @param userNum
     * @return CommonResult<PageInfo < List < MenuPrivilegedVo>>>
     */
    CommonResult<List<MenuPrivilegedVo>> queryMenuPrivileged(String userNum);

}
