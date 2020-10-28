package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "UserCustomerInfoVo", description = "消费用户信息")
public class UserCustomerInfoVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "消费者主键id")
    private Long id;

    @ApiModelProperty(value = "卡号")
    private String cno;

    @ApiModelProperty(value = "消费者姓名")
    private String customerName;

    @ApiModelProperty(value = "手机")
    private String phone;
}
