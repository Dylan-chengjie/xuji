package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CityImgPageDto {
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不能为空")
    private Integer pageNum=1;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市名称")
    private String cityName;
}
