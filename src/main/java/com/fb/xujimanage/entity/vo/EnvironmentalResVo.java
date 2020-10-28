package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 21:07
 * @description:查询环境配置数据返回对象
 */
@Data
@ApiModel(value="查询环境配置数据返回对象",description="查询环境配置数据返回对象EnvironmentalResVo")
public class EnvironmentalResVo {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value="环境id",name="id",required = true)
    private long id;
    /**
     * 环境布置名称
     */
    @ApiModelProperty(value="环境布置名称",name="name",required = true)
    private String name;

    /**
     * 适用场景
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value="适用场景",name="sceneName",required = true)
    private long sceneName;

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
