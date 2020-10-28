package com.fb.xujimanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chengjie
 * @date 2020-08-13 18:20
 * @description:公共实体类
 * @version:
 */
@Data
@ApiModel(value = "BaseEntity", description = "通用实体类型")
public class BaseEntity implements Serializable {
    @ApiModelProperty(value = "是否删除：0 不删，1删除", hidden = true)
    private Integer delFlag = 0;

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
