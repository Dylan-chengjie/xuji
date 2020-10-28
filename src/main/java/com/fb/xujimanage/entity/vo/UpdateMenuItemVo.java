package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/20 8:54
 * @description:封装更新菜品信息
 */
@Data
@ApiModel(value = "封装更新菜品信息", description = "封装更新菜品信息UpdateMenuItemVo")
public class UpdateMenuItemVo {
    /**
     * id
     */
    @ApiModelProperty(value = "菜单id", name = "itemId", required = true, dataType = "int")
    private String itemId;

    /**
     * 会员价格
     */
    @ApiModelProperty(value = "会员价格", name = "memberPrice", required = true, dataType = "int")
    private BigDecimal memberPrice;

    /**
     * 特色说明
     */
    @ApiModelProperty(value = "特色说明", name = "featureDescribe", required = true, dataType = "String")
    private String featureDescribe;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片数组", name = "imageFile", required = true, dataType = "__file")
    private MultipartFile[] imageFile;
    /**
     * 视频
     */
    @ApiModelProperty(value = "视频数组", name = "videoFile", required = true, dataType = "__file")
    private MultipartFile[] videoFile;

    /**
     * 原上传的图片数组，更新须保留的id
     */
    @ApiModelProperty(value = "原上传的图片数组，更新须删除的id", name = "imageId", required = false)
    private Long[] imageId;
    /**
     * 原上传的视频数组，更新须保留的id
     */
    @ApiModelProperty(value = "原上传的视频数组，更新须删除的id", name = "imageId", required = false)
    private Long[] videoId;
    /**
     * 是否展示：0。不展示 1.展示
     */
    @ApiModelProperty(value = "是否展示：0。不展示 1.展示", name = "status", required = true, dataType = "int")
    private int status;

    /**
     * 会员价开始时间
     */
    private Date memPriceStart;
    /**
     * 会员价结束时间
     */
    private Date memPriceEnd;


    /**
     * 是否会员产品  1 是 ； 0 否；
     */
    private int memberProType;
    /**
     * 门店code
     */
    private String restaurantCode;
}
