package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/16 8:35
 * @description:套餐详情
 */
@ApiModel(value = "SetMealDetailVo", description = "套餐详情")
@Data
public class SetMealDetailVo {
    /**
     * 套餐id
     */
    @ApiModelProperty(value = "套餐id",name = "setMealId")
    private String setMealId;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别",name = "detailType")
    private String detailType;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量",name = "detailQuantity")
    private BigDecimal detailQuantity;

    /**
     * 1默认选择 0替换菜品
     */
    @ApiModelProperty(value = "1默认选择 0替换菜品",name = "defaultSelect")
    private int defaultSelect;


    /**
     * 菜品对象
     */
    @ApiModelProperty(value = "菜品对象",name = "itemDetailsVo")
    private ItemDetailsVo itemDetailsVo;


}
