package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/22 14:27
 * @description:添加菜单对象
 */
@Data
@ApiModel(value = "添加菜单对象", description = "添加菜单对象MenuMaintainVo")
public class AddMenuMaintainVo {
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", name = "name", required = true)
    private String name;

    /**
     * 菜单内容
     */
    @ApiModelProperty(value = "菜单内容", name = "content", required = true)
    private String content;

    /**
     * 适用口味数据字典对应值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "适用口味数据字典对应值id", name = "tasteDicvalueid", required = true)
    private Long tasteDicvalueid;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", name = "price", required = true)
    private BigDecimal price;

    /**
     * 门店id数组
     */
    @ApiModelProperty(value = "门店id数组", name = "restaurantId", required = true)
    private Long[] restaurantId;

    /**
     * 适用人数，数据字典值id
     */
    @ApiModelProperty(value = "适用人数据字典值id", name = "personDicvalueid", required = true)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long personDicvalueid;

    /**
     * 适用人数，数据字典值id
     */
    @ApiModelProperty(value = "预算范围，数据字典值id", name = "budgetId", required = true)
    private String budgetId;
}
