package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/22 14:48
 * @description:修改菜单对象
 */
@Data
@ApiModel(value = "修改菜单对象", description = "修改菜单对象UpdateMenuMaintainVo")
public class UpdateMenuMaintainVo extends AddMenuMaintainVo {
    /**
     * 用户id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private long id;

}
