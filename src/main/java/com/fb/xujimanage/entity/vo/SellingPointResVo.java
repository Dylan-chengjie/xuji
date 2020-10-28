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
 * @date 2020/8/21 0:09
 * @description:查询服务特色数据返回对象
 */
@Data
@ApiModel(value="查询服务特色数据返回对象",description="查询服务特色数据返回对象SellingPointResVo")
public class SellingPointResVo {
    /**
     * 服务特色id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value="服务特色id",name="id",required = true)
    private long id;
    /**
     * 服务特色名称
     */
    @ApiModelProperty(value="服务特色名称",name="name",required = true)
    private String name;

    /**
     * 服务特色说明
     */
    @ApiModelProperty(value="服务特色说明",name="serviceExplain",required = true)
    private String serviceExplain;

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
