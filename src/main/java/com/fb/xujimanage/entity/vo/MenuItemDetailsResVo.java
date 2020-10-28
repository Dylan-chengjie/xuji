package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/23 2:46
 * @description:查询菜品信息
 */
@Data
@ApiModel(value="查询菜品信息",description="查询菜品信息menuItemResVo")
public class MenuItemDetailsResVo {
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
    @ApiModelProperty(value = "会员价开始时间", name = "memPriceStart")
    private Date memPriceStart;
    /**
     * 会员价结束时间
     */
    @ApiModelProperty(value = "会员价结束时间", name = "memPriceEnd")
    private Date memPriceEnd;

    /**
     * 是否展示：0。不展示 1.展示
     */
    @ApiModelProperty(value = "是否展示：0。不展示 1.展示", name = "status", required = true, dataType = "int")
    private int status;

    /**
     * 门店数据信息
     */
    @ApiModelProperty(value="门店数组",name="restaurantInfoResVoList",required = true)
    private List<RestaurantInfoResVo> restaurantInfoResVoList;
    /**
     * 图片集合
     */
    @ApiModelProperty(value="图片数组",name="restaurantInfoResVoList",required = true)
    private List<ImageResVo> imageResVoList;
    /**
     * 视频集合
     */
    @ApiModelProperty(value="视频数组",name="restaurantInfoResVoList",required = true)
    private List<VideoResVo> videoResVoList;
}
