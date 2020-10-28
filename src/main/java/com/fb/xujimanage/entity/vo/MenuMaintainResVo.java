package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 11:17
 * @description:查询菜单信息返回对象
 */
@Data
@ApiModel(value = "查询菜单信息返回对象", description = "查询菜单信息返回对象MenuMaintainVo")
public class MenuMaintainResVo implements Serializable {
    /**
     * 菜单id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "菜单id", name = "id", required = true)
    private long id;
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
     * 适用口味 数据字典对应值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "适用口味 数据字典对应值id", name = "tasteDicvalueid", required = true)
    private long tasteDicvalueid;
    /**
     * 口味
     */
    @ApiModelProperty(value = "菜单内容", name = "taste", required = true)
    private String taste;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", name = "price", required = true)
    private BigDecimal price;
    /**
     * 门店对象
     */
    @ApiModelProperty(value = "门店对象", name = "restaurantInfoVos", required = true)
    private List<RestaurantInfoResVo> restaurantInfoResVos;

    /**
     * 适用人数，数据字典值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "适用人数，数据字典值id", name = "personDicvalueid", required = true)
    private long personDicvalueid;
    /**
     * 人数
     */
    @ApiModelProperty(value = "适用人数", name = "peopleCounting", required = true)
    private String peopleCounting;

    /**
     * 预算范围，数据字典值id
     */
    @ApiModelProperty(value = "预算范围，数据字典值id", name = "budgetId", required = true)
    private String budgetId;
}
