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
 * @date 2020/8/17 15:32
 * @description:数据字典实体类
 */
@Data
@ApiModel(value = "DataDictionary", description = "数据字典实体")
public class DataDictionary extends BaseEntity {
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotEmpty(message = "name 不能为空")
    @ApiModelProperty(value = "分类名称")
    private String name;

    @NotNull(message = "sortNum 不能为空")
    @ApiModelProperty(value = "排序字段")
    private Integer sortNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Min(0)
    @Max(1)
    @NotNull(message = "status 不能为空")
    @ApiModelProperty(value = "状态： 0 激活 ； 1 禁用")
    private Integer status;

    @NotEmpty(message = "code 不能为空")
    @ApiModelProperty(value = "数据字典编码")
    private String code;
}
