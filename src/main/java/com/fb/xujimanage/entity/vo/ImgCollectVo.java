package com.fb.xujimanage.entity.vo;

import com.fb.xujimanage.entity.ImgCollect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chengjie
 * @date 2020-08-13 10:59
 * @description:图片展示详情实体
 * @version:
 */
@Data
@ApiModel(value = "ImgCollectVo", description = "首页图集详情")
public class ImgCollectVo extends ImgCollect {
    @ApiModelProperty(value = "图片地址")
    private String address;

    @ApiModelProperty(value = "跳转链接")
    private String url;

    @ApiModelProperty(value = "图片id")
    private String imgId;

    @ApiModelProperty(value = "sortNum")
    private Integer sortNum;
}
