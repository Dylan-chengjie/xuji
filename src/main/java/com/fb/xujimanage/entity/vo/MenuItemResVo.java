package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/23 2:46
 * @description:查询菜品信息
 */
@Data
@ApiModel(value="查询菜品信息",description="查询菜品信息menuItemResVo")
public class MenuItemResVo {
    /**
     * id
     */
    @ApiModelProperty(value="菜单id",name="id",required = true)
    private String id;

    /**
     * 菜品类别
     */
    @ApiModelProperty(value="菜品类别",name="type",required = true)
    private String type;

    /**
     * 菜品名称
     */
    @ApiModelProperty(value="菜品名称",name="name",required = true)
    private String name;

    /**
     * 销售单位
     */
    @ApiModelProperty(value="销售单位",name="unit",required = true)
    private String unit;

    /**
     * 金额
     */
    @ApiModelProperty(value="菜品价格",name="price",required = true)
    private BigDecimal price;

    /**
     * 特色说明
     */
    @ApiModelProperty(value="特色说明",name="featureDescribe",required = true)
    private String featureDescribe;

    /**
     * 会员价开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "会员价开始时间", name = "memPriceStart")
    private Date memPriceStart;
    /**
     * 会员价结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "会员价结束时间", name = "memPriceEnd")
    private Date memPriceEnd;
    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名", name = "classifyName")
    private String classifyName;
    /**
     * 门店名字
     */
    @ApiModelProperty(value = "门店名字", name = "restaurantName")
    private String restaurantName;

    /**
     * 0 非实际称重计价  ； 1 按实际称重计价；
     */
    @ApiModelProperty(value = "0 非实际称重计价  ； 1 按实际称重计价；", name = "priceType")
    private int priceType;

    /**
     * 是否展示：0。不展示 1.展示
     */
    @ApiModelProperty(value = "是否展示：0.展示 1.不展示", name = "status", required = true, dataType = "int")
    private int status;

    /**
     * 门店code
     */
    @ApiModelProperty(value = "门店code", name = "restaurantCode")
    private String restaurantCode;
    /**
     * 分类code
     */
    @ApiModelProperty(value = "分类code", name = "dietCode")
    private String dietCode;

    /**
     * 会员价格
     */
    @ApiModelProperty(value = "会员价格", name = "dietCode")
    private BigDecimal memberPrice;


}
