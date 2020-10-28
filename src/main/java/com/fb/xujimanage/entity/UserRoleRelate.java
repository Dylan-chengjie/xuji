package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chengjie
 * @date 2020-08-28 17:59
 * @description:用户角色关联实体
 * @version:
 */
@Data
@ApiModel(value = "UserRoleRelate", description = "用户角色关联实体")
public class UserRoleRelate {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "工号")
    private String employeeNum;

    @ApiModelProperty(value = "用户名")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long roleId;
}
