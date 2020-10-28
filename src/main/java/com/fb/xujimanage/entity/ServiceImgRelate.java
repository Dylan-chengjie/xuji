package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 10:07
 * @description:服务图片关联实体
 */
@Data
public class ServiceImgRelate implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 环境id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long serviceId;

    /**
     * 图片id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long imgId;

}
