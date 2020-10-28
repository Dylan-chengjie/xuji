package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:39
 * @description:环境布置信息与视频关联实体类
 */
@Data
public class EnvironmentVideoRelate {
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
     * 视频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long videoId;
}
