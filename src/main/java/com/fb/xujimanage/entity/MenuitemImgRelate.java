package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 16:04
 * @description:菜品与图片关联实体类
 */
@Data
public class MenuitemImgRelate implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 菜品id
     */
    private String menuItemId;

    /**
     * 图片id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long imgId;
}
