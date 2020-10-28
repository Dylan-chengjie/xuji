package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.IMenuMaintainService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 13:56
 * @description:就餐信息维护模块:菜单信息
 */
@RestController
@AuthToken
@RequestMapping("/repastInfo/menuInfo")
@Api(value = "MenuInfoController", description = "就餐信息维护模块:菜单信息")
public class MenuInfoController {

    private IMenuMaintainService menuMaintainService;

    @Autowired
    public void setMenuMaintainService(IMenuMaintainService menuMaintainService) {
        this.menuMaintainService = menuMaintainService;
    }

    /**
     * 菜单维护:分页查询菜单维护信息
     *
     * @param currentPage
     * @param pageSize
     * @param menuName
     * @return
     */
    @ApiOperation(value = "菜单维护:分页查询菜单维护信息", response = MenuMaintainResVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
            @ApiImplicitParam(name = "menuName", value = "菜单名", required = false, dataType = "String")

    })
    @GetMapping(value = "/getMenuInfoList")
    public CommonResult<List<MenuMaintainResVo>> getMenuInfoList(@RequestParam(value = "currentPage") Integer currentPage,
                                                                 @RequestParam(value = "pageSize") Integer pageSize,
                                                                 @RequestParam(value = "menuName", defaultValue = "") String menuName) {
        CommonResult<List<MenuMaintainResVo>> commonResult = menuMaintainService.queryMenuInfo(currentPage, pageSize, menuName);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 菜单维护:删除菜单信息
     *
     * @param menuId:菜单id
     * @return
     */
    @ApiOperation(value = "菜单维护:删除菜单信息")
    @ApiImplicitParam(name = "menuId", value = "菜单id", required = true, dataType = "int")
    @DeleteMapping(value = "/removeMenuInfo")
    public CommonResult removeMenuInfo(Long menuId) {
        if (menuId == null) {
            CommonResult.fail();
        }
        CommonResult commonResult = menuMaintainService.deleteMenuInfo(menuId);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 菜单维护:添加菜单信息
     *
     * @param addMenuMaintainVo:菜单维护信息vo
     * @return
     */
    @ApiOperation(value = "菜单维护:添加菜单信息")
    @PostMapping(value = "/addMenuInfo", consumes = "application/json")
    public CommonResult addMenuInfo(@RequestBody @ApiParam(name = "添加菜单信息对象", value = "json格式", required = true)
                                            AddMenuMaintainVo addMenuMaintainVo) {
        CommonResult commonResult = menuMaintainService.insertMenuInfo(addMenuMaintainVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 菜单维护:修改菜单信息
     *
     * @param updateMenuMaintainVo:修改菜单信息vo
     * @return
     */
    @ApiOperation(value = "菜单维护:修改菜单信息")
    @PutMapping(value = "/updateMenuInfo", consumes = "application/json")
    public CommonResult updateMenuInfo(@RequestBody @ApiParam(name = "修改菜单信息对象", value = "json格式", required = true)
                                               UpdateMenuMaintainVo updateMenuMaintainVo) {
        CommonResult commonResult = menuMaintainService.updateMenuInfo(updateMenuMaintainVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 菜单维护:查询门店信息
     *
     * @param restaurantInfoVo：门店信息查询对象
     * @return
     */
    @ApiOperation(value = "菜单维护:查询门店信息", response = RestaurantInfoResVo.class)
    @GetMapping(value = "/getRestaurantInfo")
    public CommonResult<List<RestaurantInfoResVo>> getRestaurantInfo(@ApiParam(name = "门店信息查询对象", required = true)
                                                                             RestaurantInfoVo restaurantInfoVo) {
        CommonResult<List<RestaurantInfoResVo>> commonResult = menuMaintainService.queryRestaurantInfo(restaurantInfoVo);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }


}

