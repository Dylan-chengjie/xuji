package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-28 17:59
 * @description:用户角色实体
 * @version:
 */
@Data
@ApiModel(value = "UserRoleVo", description = "用户角色实体")
public class UserRoleVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @NotEmpty(message = "employeeNum 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "工号")
    private String employeeNum;

    @ApiModelProperty(value = "用户名")
    private List<Long> roleIds;
}
