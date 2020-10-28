package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author chengjie
 * @date 2020-08-24 17:59
 * @description:角色实体
 * @version:
 */
@Data
@ApiModel(value = "Role", description = "角色实体")
public class Role extends BaseEntity {
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotEmpty(message = "nameCn不能为空")
    @ApiModelProperty(value = "中文名称")
    private String roleName;

    @ApiModelProperty(value = "英文名称")
    private String roleRemark;
}
