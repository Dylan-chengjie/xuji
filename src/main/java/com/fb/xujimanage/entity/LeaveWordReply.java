package com.fb.xujimanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/25 15:32
 * @description:回复实体类
 */
@Data
@ApiModel(value = "LeaveWordReply", description = "回复实体类")
public class LeaveWordReply {
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "留言id")
    private String leaveWordId;

    @ApiModelProperty(value = "员工工号")
    private String userCno;

    @ApiModelProperty(value = "用户名称")
    private String userName;
}
