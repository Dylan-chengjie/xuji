package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.LeaveWordReply;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/25 15:32
 * @description:留言实体类
 */
@Data
@ApiModel(value = "LeaveWordVo", description = "留言实体类")
public class LeaveWordVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "留言类型 0 建议；1 投诉 ； 2 评价")
    private Integer wordType;

    @ApiModelProperty(value = "留言内容")
    private String wordContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户id")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "门店id")
    private String restaurantId;

    @ApiModelProperty(value = "门店城市")
    private String restaurantCity;

    @ApiModelProperty(value = "门店城市区域")
    private String restaurantArea;

    @ApiModelProperty(value = "门店名称")
    private String restaurantName;

    @ApiModelProperty(value = "门店地址")
    private String restaurantAddress;

    @ApiModelProperty(value = "菜品评分")
    private Integer dietScore;

    @ApiModelProperty(value = "服务评分")
    private Integer serviceScore;

    @ApiModelProperty(value = "环境评分")
    private Integer environmentScore;

    @ApiModelProperty(value = "图片集合")
    private List<Image> images;

    @ApiModelProperty(value = "回复列表")
    private List<LeaveWordReply> leaveWordReplies;
}
