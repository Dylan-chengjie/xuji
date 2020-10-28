package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:01
 * @description: 数据字典值实体类
 */
@Data
@ApiModel(value = "DataDictValue", description = "数据字典数据实体")
public class DataDictValue extends BaseEntity {
    @NotNull(message = "dictionaryId 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotNull(message = "dictionaryId 不能为空")
    @ApiModelProperty(value = "数据字典id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long dictionaryId;

    @NotEmpty(message = "name 不能为空")
    @ApiModelProperty(value = "分类名称")
    private String name;

    @NotEmpty(message = "value 不能为空")
    @ApiModelProperty(value = "数据值")
    private String value;

    @NotNull(message = "sortNum 不能为空")
    @ApiModelProperty(value = "排序字段")
    private Integer sortNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Min(value = 0, groups = {FirstGroup.class})
    @Max(value = 1, groups = {FirstGroup.class})
    @NotNull(message = "status 不能为空")
    @ApiModelProperty(value = "状态： 0 激活 ； 1 禁用")
    private Integer status;
}
