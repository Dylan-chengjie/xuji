package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/16 8:27
 * @description: 套餐对象
 */
@ApiModel(value = "SetMealVo", description = "套餐对象")
@Data
public class SetMealVo {

    /**
     * 套餐id
     */
    @ApiModelProperty(value = "套餐id",name = "setId")
    private String setId;


    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称",name = "setName")
    private String setName;
    /**
     * 套餐详情
     */
    @ApiModelProperty(value = "套餐详情",name = "detailVos")
    private List<SetMealDetailVo> detailVos;




}
