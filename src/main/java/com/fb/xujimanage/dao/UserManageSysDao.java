package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-14 13:59
 * @description:用户相关
 * @version:
 */
@Repository
public interface UserManageSysDao {
    /**
     * 新增用户
     *
     * @param userManageSys
     * @returna
     */
    Integer addUserManageSys(UserManageSys userManageSys);

    /**
     * 新增用户
     *
     * @returna
     */
    Integer deleteUserManageSysAll(@Param("retainUserNames") List<String> retainUserNames);

    /**
     * 查询用户信息
     *
     * @param userName
     * @param password
     * @returna
     */
    UserManageSysVo queryUserManage(@Param("userName") String userName, @Param("password") String password);

    /**
     * 更新用户信息
     *
     * @param userManageSys
     * @return CommonResult
     */
    Integer updateUserManageSys(UserManageSys userManageSys);

    /**
     * 根据员工工号禁用工号
     *
     * @param usageStatus
     * @param userNums
     * @return CommonResult
     */
    Integer updateUsageStatusByUserNum(@Param("usageStatus") Integer usageStatus, @Param("userNums") List<String> userNums);

    /**
     * 分页查询用户列表
     *
     * @param departmentList
     * @param usageStatus
     * @param title
     * @return
     */
    List<UserManageSysVo> pageUserManageSys(@Param("departmentList") List<String> departmentList, @Param("usageStatus") Integer usageStatus,
                                            @Param("title") String title, @Param("userNums") List<String> userNums);

    /**
     * 分页查询未授角色的用户用户列表
     *
     * @return
     */
    List<UserManageSysVo> pageUserNotRole(@Param("departmentList") List<String> departmentList, @Param("title") String title);

    //查询所有的用户信息
    List<UserManageSysVo> getAllUserUserManageSysInfo(UserManageSys userManageSys);
}
