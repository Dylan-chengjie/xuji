package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.entity.BaseEntity;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-21 17:59
 * @description:菜单权限视图
 * @version:
 */
@Data
@ApiModel(value = "MenuPrivilegedVo", description = "菜单权限视图")
public class MenuPrivilegedVo extends BaseEntity {
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "中文名称")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    private String nameEn;

    @ApiModelProperty(value = "权限编码")
    private String privilegedCode;

    @ApiModelProperty(value = "父菜单编码")
    private String parentCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "图片id")
    private Long iconImgId;

    @ApiModelProperty(value = "图片地址")
    private String address;

    @ApiModelProperty(value = "菜单状态 0 启用；1 禁用；")
    private Integer menuStatus;

    @ApiModelProperty(value = "排序值")
    private Integer sortNum;

    @ApiModelProperty(value = "菜单类型 0 顶部菜单； 1 页面菜单；")
    private Integer menuType;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "子菜单")
    private List<MenuPrivilegedVo> menuPrivilegedVos;
}
