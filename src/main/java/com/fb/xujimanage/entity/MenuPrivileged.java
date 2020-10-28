package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author chengjie
 * @date 2020-08-21 17:59
 * @description:菜单权限实体
 * @version:
 */
@Data
@ApiModel(value = "MenuPrivileged", description = "预点订单实体")
public class MenuPrivileged extends BaseEntity {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotEmpty(message = "nameCn不能为空")
    @ApiModelProperty(value = "中文名称")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    private String nameEn;

    @ApiModelProperty(value = "权限编码")
    private String privilegedCode;

    @ApiModelProperty(value = "父菜单编码")
    private String parentCode;

    @NotNull(message = "iconImgId不能为空")
    @ApiModelProperty(value = "图片id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long iconImgId;

    @Min(0)
    @Max(1)
    @NotNull(message = "menuStatus不能为空")
    @ApiModelProperty(value = "菜单状态 0 启用；1 禁用")
    private Integer menuStatus;

    @NotNull(message = "sortNum不能为空")
    @ApiModelProperty(value = "排序值")
    private Integer sortNum;

    @Min(0)
    @Max(1)
    @NotNull(message = "menuType不能为空")
    @ApiModelProperty(value = "菜单类型 0 顶部菜单； 1 页面菜单")
    private Integer menuType;

    @NotEmpty(message = "menuPath不能为空")
    @ApiModelProperty(value = "菜单路径")
    private String menuPath;
}
