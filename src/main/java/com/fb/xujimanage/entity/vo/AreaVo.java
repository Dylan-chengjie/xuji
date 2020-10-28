package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/4 10:48
 * @description:区域对象
 */
@Data
@ApiModel(value="区域对象",description="区域对象AreaVo")
public class AreaVo {
    /**
     * 区域
     */
    @ApiModelProperty(value="区域",name="area" ,required = true)
    private String area;
}
