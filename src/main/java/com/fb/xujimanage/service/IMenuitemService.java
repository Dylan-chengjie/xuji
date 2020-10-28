package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 10:10
 * @description:就餐信息维护模块->菜品维护接口
 */
public interface IMenuitemService {
    /**
     * 分页查询产品信息列表
     *
     * @param currentPage：当前页
     * @param pageSize：每页显示的数量
     * @return
     */
    CommonResult<List<MenuItemResVo>> queryMenuitemInfoList(Integer currentPage, Integer pageSize, MenuitemReqVo menuitemReqVo);


    /**
     * 通过菜品id查询菜品详情
     * @param menuItemId
     * @return
     */
    CommonResult<ItemDetailsVo> queryMenuitemDetails(String menuItemId,String dietCode,String restaurantCode);

    /**
     * 修改菜品信息
     *
     * @param updateMenuItemVo:菜品信息封装
     * @return
     */
    CommonResult updateMenuItemInfo(UpdateMenuItemVo updateMenuItemVo);



}
