package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 10:44
 * @description:
 */
@Data
@ApiModel(value = "封装服务特色所需的参数信息", description = "封装服务特色所需的参数信息SellingPointVo")
public class SellingPointVo {
    /**
     * 服务特色id
     */

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "服务特色id", name = "id", required = false)
    private Long id;
    /**
     * 服务特色名称
     */
    @ApiModelProperty(value = "服务特色名称", name = "name", required = true)
    private String name;

    /**
     * 服务特色说明
     */
    @ApiModelProperty(value = "服务特色说明", name = "serviceExplain", required = true)
    private String serviceExplain;

    /**
     * 门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "门店id", name = "restaurantId", required = true)
    private long restaurantId;


    /**
     * 适用场景
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "适用场景（数据字典id）", name = "sceneId", required = true)
    private Long sceneId;
    /**
     * 环境布置图片
     */
    @ApiModelProperty(value = "环境布置图片数组", name = "imageFile", required = true, dataType = "__file")
    private MultipartFile[] imageFile;
    /**
     * 环境布置视频
     */
    @ApiModelProperty(value = "环境布置视频数组", name = "videoFile", required = true, dataType = "__file")
    private MultipartFile[] videoFile;

    /**
     * 原上传的图片数组，更新须删除的id
     */
    @ApiModelProperty(value = "原上传的图片数组，更新须删除的id", name = "imageId", required = false)
    private Long[] imageId;
    /**
     * 原上传的视频数组，更新须删除的id
     */
    @ApiModelProperty(value = "原上传的视频数组，更新须删除的id", name = "videoId", required = false)
    private Long[] videoId;
}

