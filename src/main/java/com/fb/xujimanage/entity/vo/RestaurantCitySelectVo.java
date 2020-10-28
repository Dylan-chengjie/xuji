package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RestaurantCitySelectVo {
    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;
}
