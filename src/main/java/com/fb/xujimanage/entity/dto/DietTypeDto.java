package com.fb.xujimanage.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @auther Sam.yang
 * @date 2020/8/31
 * @description DietTypeDto
 */
@Data
public class DietTypeDto {

    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;
    /**
     * 门店编码
     */
    @NotBlank(message = "门店编码不能为空")
    private String restaurantCode;
    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空")
    private String restaurantName;
    /**
     * 菜品分类名称
     */
    @NotBlank(message = "菜品分类名称不能为空")
    private String name;
    /**
     * 菜品分类code
     */
    @NotBlank(message = "菜品分类代码不能为空")
    private String code;
    /**
     * 菜品分类排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sortNum;
    /**
     * 是否展示， 0 不展示；1 展示；
     */
    @NotNull(message = "是否展示， 0 不展示；1 展示；")
    private Integer isShow;
}
