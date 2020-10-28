package com.fb.xujimanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.dao.MenuPrivilegedDao;
import com.fb.xujimanage.dao.UserManageSysDao;
import com.fb.xujimanage.dao.UserRoleRelateDao;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.UserRoleRelate;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.entity.vo.UserRoleVo;
import com.fb.xujimanage.service.UserManageSysService;
import com.fb.xujimanage.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: xujimanage
 * @description: 用户信息接口实现类
 * @author: chengjie
 * @date: Created in 2020/8/24 17:43
 **/
@Service
public class UserManageSysServiceImpl implements UserManageSysService {

    private UserManageSysDao userManageSysDao;
    private UserRoleRelateDao userRoleRelateDao;
    private MenuPrivilegedDao menuPrivilegedDao;
    private RedisUtil redisUtil;

    public UserManageSysServiceImpl(UserManageSysDao userManageSysDao,
                                    UserRoleRelateDao userRoleRelateDao,
                                    MenuPrivilegedDao menuPrivilegedDao,
                                    RedisUtil redisUtil) {
        this.userManageSysDao = userManageSysDao;
        this.userRoleRelateDao = userRoleRelateDao;
        this.menuPrivilegedDao = menuPrivilegedDao;
        this.redisUtil = redisUtil;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSyncUserManageSys(List<UserManageSys> userManageSysList, List<String> retainUserNames) {
        List<UserManageSysVo> userManageSysVos = userManageSysDao.pageUserManageSys(null, Constants.ONE, null, null);
        List<String> usageUserNum = null;
        if (!CollectionUtils.isEmpty(userManageSysVos)) {
            usageUserNum = userManageSysVos.stream().map(UserManageSysVo::getUserNum).collect(Collectors.toList());
        }
        userManageSysDao.deleteUserManageSysAll(retainUserNames);
        userManageSysList.forEach(userManageSys -> {
            userManageSys.setId(IdUtil.getUUID());
            userManageSysDao.addUserManageSys(userManageSys);
        });
        if (!CollectionUtils.isEmpty(usageUserNum)) {
            userManageSysDao.updateUsageStatusByUserNum(Constants.ONE, usageUserNum);
        }
    }

    @Override
    public CommonResult<UserManageSysVo> authLogin(String userNum, String userPassword, HttpServletRequest httpRequest) {
        String compile = AesUtil.aesDecrypt(userPassword);
        if (StringUtils.isEmpty(compile)) {
            return CommonResult.fail("账号或密码错误");
        }
        String password = EncryptUtils.encrypt2Md5(compile);
        UserManageSysVo userManageSysVo = userManageSysDao.queryUserManage(userNum, password);
        if (userManageSysVo != null) {
            if (Constants.ONE.equals(userManageSysVo.getUsageStatus())) {
                return CommonResult.fail("账号被禁用，请联系管理员");
            }
            String token = TokenUtils.createToken(JSONObject.toJSONString(userManageSysVo));
            String tokenKey = Constants.TOKEN_KEY + userNum + "_" + password;
            userManageSysVo.setPassword(null);
            redisUtil.set(tokenKey, JSONObject.toJSONString(userManageSysVo), Constants.TOKEN_EXPIRES_TIME);
            userManageSysVo.setToken(token);
            return CommonResult.ok("登录成功", userManageSysVo);
        }
        return CommonResult.fail("账号或密码错误");
    }

    @Override
    public CommonResult updateUserManageSys(UserManageSys userManageSys) {
        Integer usageStatus = userManageSys.getUsageStatus();
        if (usageStatus != null && (usageStatus < 0 || usageStatus > 1)) {
            return CommonResult.fail("usageStatus不在取值范围内");
        }
        return userManageSysDao.updateUserManageSys(userManageSys) > 0 ?
                CommonResult.ok("更新用户信息成功") : CommonResult.fail("更新用户信息失败");
    }

    @Override
    public CommonResult<PageInfo<List<UserManageSysVo>>> pageUserManageSys(Integer pageNum, Integer pageSize, List<String> departmentList,
                                                                           Integer usageStatus, String title, Long roleId, Boolean our) {
        List<UserManageSysVo> userManageSysVos = new ArrayList<>();
        if (our) {
            List<String> userNums = userRoleRelateDao.listUserNumByRoleId(roleId);
            if (!CollectionUtils.isEmpty(userNums)) {
                PageHelper.startPage(pageNum, pageSize);
                userManageSysVos = userManageSysDao.pageUserManageSys(departmentList, usageStatus, title, userNums);
                userManageSysVos.forEach(userManageSysVo -> {
                    List<Long> roleIds = userRoleRelateDao.listRoleId(userManageSysVo.getUserNum());
                    if (!CollectionUtils.isEmpty(roleIds)) {
                        userManageSysVo.setRoleIds(roleIds);
                    }
                });
            }
            return CommonResult.ok("分页查询用户列表成功", new PageInfo<>(userManageSysVos));
        } else {
            PageHelper.startPage(pageNum, pageSize);
            userManageSysVos = userManageSysDao.pageUserNotRole(departmentList, title);
            return CommonResult.ok("分页查询用户列表成功", new PageInfo<>(userManageSysVos));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addUserRole(List<UserRoleVo> userRoleVos) {
        userRoleVos.forEach(userRoleVo -> {
            String employeeNum = userRoleVo.getEmployeeNum();
            List<UserManageSysVo> userManageSysVos = userManageSysDao.pageUserManageSys(null, null, null, new ArrayList<String>() {{
                add(employeeNum);
            }});
            if (CollectionUtils.isEmpty(userManageSysVos)) {
                CommonResult.throwVerifyException("账号:" + employeeNum + "不存在");
            }
            userRoleVo.getRoleIds().forEach(RoleId -> {
                UserRoleRelate userRoleRelate = new UserRoleRelate() {{
                    setId(IdUtil.getUUID());
                    setEmployeeNum(userRoleVo.getEmployeeNum());
                    setRoleId(RoleId);
                }};
                userRoleRelateDao.addUserRole(userRoleRelate);
            });
        });
        return CommonResult.ok("新增用户角色成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateUserRole(UserRoleVo userRoleVo) {
        String employeeNum = userRoleVo.getEmployeeNum();
        List<Long> roleIdList = userRoleRelateDao.listRoleId(employeeNum);
        List<Long> roleIds = userRoleVo.getRoleIds();
        List<Long> deleteRoleIdList = roleIdList.stream().filter(item -> !roleIds.contains(item)).collect(Collectors.toList());
        List<Long> addRoleIdList = CollectionUtils.isEmpty(roleIds) ? null : roleIds.stream().filter(item -> !roleIdList.contains(item)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteRoleIdList)) {
            userRoleRelateDao.deleteUserRole(employeeNum, deleteRoleIdList);
        }
        if (!CollectionUtils.isEmpty(addRoleIdList)) {
            addRoleIdList.forEach(roleId -> {
                UserRoleRelate userRoleRelate = new UserRoleRelate() {{
                    setId(IdUtil.getUUID());
                    setEmployeeNum(employeeNum);
                    setRoleId(roleId);
                }};
                userRoleRelateDao.addUserRole(userRoleRelate);
            });
        }
        return CommonResult.ok("更新用户角色成功");
    }

    @Override
    public CommonResult<List<MenuPrivilegedVo>> queryMenuPrivileged(String userNum) {
        List<Long> roleIds = userRoleRelateDao.listRoleId(userNum);
        List<MenuPrivilegedVo> menuPrivilegeds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleIds)) {
            menuPrivilegeds = menuPrivilegedDao.queryRolePrivilegedByRoleId(roleIds);
        }
        return CommonResult.ok("查询用户列表成功", menuPrivilegeds);
    }
}
