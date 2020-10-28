package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

/**
 * @auther Sam.yang
 * @date 2020/8/25
 * @description
 */
@Data
public class EmployeeVo {

    /**
     * 主键Id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;
    /**
     * 员工姓名
     */
    private String name;


}
