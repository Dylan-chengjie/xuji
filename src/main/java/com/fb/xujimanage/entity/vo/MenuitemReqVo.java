package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/22 10:49
 * @description: 菜品分页查询查询参数封装
 */
@Data
@ApiModel(value = "MenuitemReqVo", description = "菜品分页查询查询参数封装")
public class MenuitemReqVo {
    /**
     * 菜品名
     */
    @ApiModelProperty(value = "菜品名", name = "menuitemName", required = false)
    private String menuitemName;
    /**
     * 城市code
     */
    @ApiModelProperty(value = "城市code", name = "cityCode", required = false)
    private String cityCode;
    /**
     * 会员开始时间
     */
    @ApiModelProperty(value = "会员开始时间", name = "memPriceStart", required = false)
    private String memPriceStart;
    /**
     * 会员结束时间
     */
    @ApiModelProperty(value = "会员结束时间", name = "memPriceEnd", required = false)
    private String memPriceEnd;
    /**
     * 菜品分类数组
     */
    @ApiModelProperty(value = "菜品分类code字符串", name = "menuItemClassifyCode", required = false)
    private String menuItemClassifyCode;

    @ApiModelProperty(value = "菜品分类code数组", name = "menuItemClassify", required = false,hidden = true)
    private String[] menuItemClassify;
    /**
     * 门店code
     */
    @ApiModelProperty(value = "门店code", name = "restaurantCode", required = false)
    private String restaurantCode;


}
