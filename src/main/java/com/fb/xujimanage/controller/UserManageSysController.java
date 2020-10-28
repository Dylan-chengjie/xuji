package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.entity.vo.UserRoleVo;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.service.UserManageSysService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengjie
 * @date 2020-08-25 21:37
 * @description:用户管理
 * @version:v
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserManageSysController {

    private UserManageSysService userManageSysService;

    public UserManageSysController(UserManageSysService userManageSysService) {
        this.userManageSysService = userManageSysService;
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    public CommonResult<UserManageSysVo> authLogin(@RequestParam String userName,
                                                   @RequestParam String password,
                                                   HttpServletRequest httpServletRequest) {
        return userManageSysService.authLogin(userName, password, httpServletRequest);
    }

    @ApiOperation(value = "更新用户，是否禁用")
    @AuthToken
    @PutMapping("/updateUser")
    public CommonResult updateUserManageSys(@RequestBody @Validated({FirstGroup.class}) UserManageSys userManageSys) {
        return userManageSysService.updateUserManageSys(userManageSys);
    }

    @ApiOperation(value = "分页查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int"),
            @ApiImplicitParam(name = "departments", value = "部门id集合，如1,2,3", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "long"),
            @ApiImplicitParam(name = "usageStatus", value = "使用状态： 0 激活 ； 1 禁用", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "真实姓名/用户账号/手机号码（模糊查询）", dataType = "string"),
            @ApiImplicitParam(name = "our", value = "是否是我们（徐记）用户", required = true, dataType = "boolean")
    })
    @AuthToken
    @GetMapping("/pageUser")
    public CommonResult<PageInfo<List<UserManageSysVo>>> pageUserManageSys(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "departments", required = false) String departments,
            @RequestParam(name = "usageStatus", required = false) Integer usageStatus,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "roleId", required = false) Long roleId,
            @RequestParam Boolean our
    ) {
        List<String> departmentList = new ArrayList<>();
        if (StringUtils.isNotEmpty(departments)) {
            departmentList = Arrays.asList(departments.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
        }
        return userManageSysService.pageUserManageSys(pageNum, pageSize, departmentList, usageStatus, title, roleId, our);
    }

    @ApiOperation(value = "添加用户角色")
    @AuthToken
    @PostMapping("/addUserRole")
    public CommonResult addUserRole(@RequestBody List<UserRoleVo> userRoleVos) {
        return userManageSysService.addUserRole(userRoleVos);
    }

    @ApiOperation(value = "更新用户角色")
    @AuthToken
    @PostMapping("/updateUserRole")
    public CommonResult updateUserRole(@RequestBody @Validated({FirstGroup.class}) UserRoleVo userRoleVo) {
        return userManageSysService.updateUserRole(userRoleVo);
    }

    @ApiOperation(value = "查询用户菜单栏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNum", value = "员工工号", required = true, dataType = "string")
    })
    @AuthToken
    @GetMapping("/queryMenuPrivileged")
    public CommonResult<List<MenuPrivilegedVo>> queryMenuPrivileged(
            @RequestParam String userNum) {
        return userManageSysService.queryMenuPrivileged(userNum);
    }
}
