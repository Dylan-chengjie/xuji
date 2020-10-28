package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * @author chengjie
 * @date 2020-08-20 17:59
 * @description:预点订单实体
 * @version:
 */
@Data
@ApiModel(value = "OrderDinner", description = "预点订单实体")
public class OrderDinner {
    @NotNull(message = "id 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "就餐人数")
    private String personCount;

    @ApiModelProperty(value = "就餐时间段，开始时间")
    private Time timeStart;

    @ApiModelProperty(value = "就餐时间段  结束时间")
    private Time timeEnd;

    @ApiModelProperty(value = "需求")
    private String customerRequire;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "订单状态： 0 待支付； 1 已支付；2  客户取消；3 系统取消")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal amountMoney;

    @ApiModelProperty(value = "核实：0 未到场，1 确认到场")
    private Integer verify;

    @ApiModelProperty(value = "订单金额")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long restaurantId;

    @ApiModelProperty(value = "消费者id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long customerId;

    @ApiModelProperty(value = "核对状态  0 未读，1已读")
    private Integer checkStatus;
}
