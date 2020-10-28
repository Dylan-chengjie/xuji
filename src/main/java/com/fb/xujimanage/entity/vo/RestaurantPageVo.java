package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RestaurantPageVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "门店名称")
    private String restaurantName;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "门店地址")
    private String address;

    @ApiModelProperty(value = "门店图片ID")
    private String imgId;

    @ApiModelProperty(value = "门店vr路径")
    private String vrUrl;

    @ApiModelProperty(value = "门店图片路径")
    private String imgUrl;

    @ApiModelProperty("是否启用  0 启用  ； 1 禁用  ")
    private Integer  delFlag;

    @ApiModelProperty(value = "城市、地址等是否在后台进行了修改 0 未修改；1 修改")
    private Integer modifyFlag;

    @ApiModelProperty(value = "门店联系电话")
    private String contactWay;

    @ApiModelProperty(value = "门店编码")
    private String code;
}
