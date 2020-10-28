package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:37
 * @description: 环境布置信息与图片关联实体类
 */
@Data
public class EnvironmentImgRelate implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 环境id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long environmentId;

    /**
     * 图片id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long imgId;
}
