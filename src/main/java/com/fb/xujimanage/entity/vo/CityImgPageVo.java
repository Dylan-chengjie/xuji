package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CityImgPageVo {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "城市图片id")
    private String imageId;

    @ApiModelProperty(value = "城市图片url")
    private String imageUrl;
}
