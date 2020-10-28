package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.Role;
import com.fb.xujimanage.entity.RoleMenuPriviRelate;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.service.RoleService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengjie
 * @date 2020-08-25 21:37
 * @description:角色管理
 * @version:v
 */
@Api(description = "角色管理")
@RestController
@AuthToken
@RequestMapping("/role/manage")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "新增角色")
    @PostMapping
    public CommonResult addRole(@RequestBody @Valid Role role) {
        return roleService.addRole(role);
    }

    @ApiOperation(value = "角色授权")
    @PostMapping("/addPrivileged")
    public CommonResult addRolePrivileged(@RequestBody @Valid List<RoleMenuPriviRelate> roleMenuPriviRelates) {
        return roleService.addRolePrivileged(roleMenuPriviRelates);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping
    public CommonResult updateRole(@RequestBody @Validated({FirstGroup.class}) Role role) {
        return roleService.updateRole(role);
    }

    @ApiOperation(value = "批量删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "角色id数组字符串，如1,2,3", required = true, dataType = "string")
    })
    @DeleteMapping
    public CommonResult batchDeleteRole(@RequestParam String ids) {
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return roleService.batchDeleteRole(idList);
    }

    @ApiOperation(value = "分页查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int")
    })
    @GetMapping
    public CommonResult<PageInfo<List<Role>>> pageRole(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return roleService.pageRole(pageNum, pageSize);
    }
}
