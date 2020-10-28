package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @auther Sam.yang
 * @date 2020/8/24
 * @description 客服名称dto
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CustomerNameDto")
public class CustomerNameDto extends BaseDto{

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客服名称",name = "customerServerName",required = false)
    private String customerServerName;
}
