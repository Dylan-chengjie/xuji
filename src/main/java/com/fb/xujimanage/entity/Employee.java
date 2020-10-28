package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工信息表(Employee)实体类
 *
 * @author sam.yang
 * @since 2020-08-25 15:14:49
 */
@Data
public class Employee extends BaseEntity {

    /**
     * 主键Id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String contactWay;


}
