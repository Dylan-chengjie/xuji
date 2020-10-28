package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/4 10:47
 * @description:城市区域对象
 */
@Data
@ApiModel(value="城市区域对象",description="城市区域对象对象CityAreaVo")
public class CityAreaVo {
    /**
     * 城市
     */
    @ApiModelProperty(value="城市",name="city" ,required = true)
    private String city;
    /**
     * 区域集合
     */
    @ApiModelProperty(value="区域集合",name="areaVos" ,required = true)
    private List<AreaVo> areaVos;
}
