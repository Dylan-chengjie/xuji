package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:58
 * @description:菜品与视频关联实体类
 */
@Data
public class MenuitemVideoRelate implements Serializable {
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
     * 视频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long videoId;

}
