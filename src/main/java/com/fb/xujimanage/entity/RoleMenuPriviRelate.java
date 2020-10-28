package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chengjie
 * @date 2020-08-27 17:59
 * @description:角色同菜单权限关联表
 * @version:
 */
@Data
@ApiModel(value = "RoleMenuPriviRelate", description = "角色同菜单权限关联表")
public class RoleMenuPriviRelate {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotNull(message = "roleId不能为空")
    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long roleId;

    @NotNull(message = "privilegedCode不能为空")
    @ApiModelProperty(value = "菜单权限code")
    private String privilegedCode;
}
