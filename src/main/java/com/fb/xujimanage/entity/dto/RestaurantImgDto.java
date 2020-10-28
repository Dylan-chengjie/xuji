package com.fb.xujimanage.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class RestaurantImgDto {
    @ApiModelProperty(value = "门店主键ID")
    @NotNull(message = "门店主键ID不能为空")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "门店图片ID")
    private String imgId;

    @ApiModelProperty("3D路径地址")
    private String vrUrl;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty("城市地址")
    private String address;

    @ApiModelProperty(value = "门店联系电话")
    private String contactWay;

}
