package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CityImgDto {

    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    /**
     * 城市名称
     */
    @NotBlank(message = "城市名称不能为空")
    @ApiModelProperty(value = "城市名称")
    private String cityName;


    @ApiModelProperty(value = "城市图片id")
    private String imageId;

}
