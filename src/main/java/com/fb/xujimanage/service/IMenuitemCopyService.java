package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.dto.TcConsume;
import com.fb.xujimanage.entity.dto.TcInfo;
import com.fb.xujimanage.entity.dto.XFBMInfo;
import com.fb.xujimanage.entity.vo.MenuItemVo;
import com.fb.xujimanage.entity.vo.RestaurantVo;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 10:10
 * @description:就餐信息维护模块->菜品维护接口
 */
public interface IMenuitemCopyService {


    /**
     *  获取菜品信息
     * @return
     */
    public int updatMenuItem(List<MenuItemVo> menuItemVos);

    /**
     *  根据ID修改菜品
     * @param xfbmInfo
     */
    void  updateOneMenultemCopy(XFBMInfo xfbmInfo,RestaurantVo restaurantVo);

    /**
     * 根据ID修改套餐信息单条
     * @param tcInfo
     */
    void  updateOneSetMealCopy(TcInfo tcInfo, String rId);



    /**
     * 根据ID修改套餐信息单条
     * @param tcConsume
     */
    void  updateOneSetMealDetailCopy(TcConsume tcConsume);


    //菜品导数优化
    void  sourceMenuItemTask(RestaurantVo restaurantVo, List<XFBMInfo> xfbmInfos);


}
