package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 10:08
 * @description:就餐信息维护模块->菜单维护接口
 */
public interface IMenuMaintainService {
    /**
     * 分页查询菜单维护信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    CommonResult<List<MenuMaintainResVo>> queryMenuInfo(Integer currentPage, Integer pageSize, String menuName);

    /**
     * 删除菜单信息
     * @param menuId：菜单id
     * @return
     */
    CommonResult deleteMenuInfo(Long menuId);

    /**
     * 添加菜单信息
     * @param addMenuMaintainVo
     * @return
     */
    CommonResult insertMenuInfo(AddMenuMaintainVo addMenuMaintainVo);

    /**
     * 修改菜单信息
     * @param updateMenuMaintainVo
     * @return
     */
    CommonResult updateMenuInfo(UpdateMenuMaintainVo updateMenuMaintainVo);


    /**
     * 查询门店信息
     * @return
     */
    CommonResult<List<RestaurantInfoResVo>> queryRestaurantInfo(RestaurantInfoVo restaurantInfoVo);

}
