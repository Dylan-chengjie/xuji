package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.service.MenuPrivilegedService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengjie
 * @date 2020-08-21 21:37
 * @description:菜单权限管理
 * @version:v
 */
@Api(description = "菜单权限管理")
@RestController
@AuthToken
@RequestMapping("/menu/privileged")
public class MenuPrivilegedController {

    private MenuPrivilegedService menuPrivilegedService;

    public MenuPrivilegedController(MenuPrivilegedService menuPrivilegedService) {
        this.menuPrivilegedService = menuPrivilegedService;
    }

    @ApiOperation(value = "新增菜单栏")
    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nameCn", value = "中文名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "nameEn", value = "英文名称", dataType = "string"),
            @ApiImplicitParam(name = "privilegedCode", value = "权限编码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "parentCode", value = "父菜单编码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "menuStatus", value = "菜单状态 0 启用；1 禁用", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortNum", value = "排序值", required = true, dataType = "int"),
            @ApiImplicitParam(name = "menuType", value = "菜单类型 0 顶部菜单； 1 页面菜单", required = true, dataType = "int"),
            @ApiImplicitParam(name = "menuPath", value = "菜单路径", required = true, dataType = "string")
    })
    public CommonResult addMenuPrivileged(
            @RequestParam("file") MultipartFile file,
            @RequestParam String nameCn,
            @RequestParam(name = "nameEn", required = false) String nameEn,
            @RequestParam String privilegedCode,
            @RequestParam String parentCode,
            @RequestParam Integer menuStatus,
            @RequestParam Integer sortNum,
            @RequestParam Integer menuType,
            @RequestParam String menuPath) {
        String userNum = TokenUtils.getUserNum();
        return menuPrivilegedService.addMenuPrivileged(file, nameCn, nameEn, privilegedCode, parentCode,
                menuStatus, sortNum, menuType, menuPath, userNum, userNum);
    }

    @ApiOperation(value = "更新更新菜单栏")
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "long"),
            @ApiImplicitParam(name = "iconImgId", value = "更新图片时必填", dataType = "long"),
            @ApiImplicitParam(name = "nameCn", value = "中文名称", dataType = "string"),
            @ApiImplicitParam(name = "nameEn", value = "英文名称", dataType = "string"),
            @ApiImplicitParam(name = "menuStatus", value = "菜单状态 0 启用；1 禁用", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortNum", value = "排序值", dataType = "int")
    })
    public CommonResult updateMenuPrivileged(@RequestParam(name = "file", required = false) MultipartFile file,
                                             @RequestParam Long id,
                                             @RequestParam(name = "iconImgId", required = false) Long iconImgId,
                                             @RequestParam(name = "nameCn", required = false) String nameCn,
                                             @RequestParam(name = "nameEn", required = false) String nameEn,
                                             @RequestParam Integer menuStatus,
                                             @RequestParam(name = "sortNum", required = false) Integer sortNum) {
        String updateUser = TokenUtils.getUserNum();
        return menuPrivilegedService.updateMenuPrivileged(file, id, iconImgId, nameCn, nameEn, menuStatus, sortNum, updateUser);
    }

    @ApiOperation(value = "批量删除菜单栏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "菜单栏id数组字符串，如1,2,3", required = true, dataType = "string")
    })
    @DeleteMapping
    public CommonResult batchDeleteMenuPrivileged(@RequestParam String ids) {
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return menuPrivilegedService.batchDeleteMenuPrivileged(idList);
    }

    @ApiOperation(value = "查询菜单栏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuName", value = "一级菜单栏名称", dataType = "string")
    })
    @GetMapping
    public CommonResult<List<MenuPrivilegedVo>> listMenuPrivileged(@RequestParam(value = "menuName", required = false) String menuName) {
        return menuPrivilegedService.listMenuPrivileged(menuName);
    }

    @ApiOperation(value = "根据角色查询菜单栏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "long")
    })
    @GetMapping("/listMenuByRoleId")
    public CommonResult<List<MenuPrivilegedVo>> listMenuByRoleId(@RequestParam(value = "roleId") Long roleId) {
        return menuPrivilegedService.listMenuByRoleId(roleId);
    }
}
