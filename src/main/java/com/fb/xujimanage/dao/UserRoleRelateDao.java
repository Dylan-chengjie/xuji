package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.UserRoleRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-14 13:59
 * @description:用户角色相关
 * @version:
 */
@Repository
public interface UserRoleRelateDao {
    /**
     * 新增用户角色
     *
     * @param userRoleRelate
     * @return Integer
     */
    Integer addUserRole(UserRoleRelate userRoleRelate);

    /**
     * 删除用户角色
     *
     * @param roleIds
     * @return Integer
     */
    Integer deleteUserRole(@Param("employeeNum") String employeeNum, @Param("roleIds") List<Long> roleIds);

    /**
     * 判断角色下是否有用户
     *
     * @param roleIds
     * @return Integer
     */
    Integer countUserByRoleId(@Param("roleIds") List<Long> roleIds);

    /**
     * 查询角色id列表
     *
     * @param employeeNum
     * @return Integer
     */
    List<Long> listRoleId(String employeeNum);

    /**
     * 查询员工工号
     *
     * @return
     */
    List<String> listUserNumByRoleId(@Param("roleId") Long roleId);
}
