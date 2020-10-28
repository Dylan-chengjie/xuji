package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "OrderDinnerInfoVo", description = "订单信息")
public class OrderDinnerInfoVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "门店编码")
    private String code;

    @ApiModelProperty(value = "门店名称")
    private String restaurantName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "消费者id")
    private Long customerId;

    @ApiModelProperty(value = "桌号编码")
    private String deskNum;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal amountMoney;

    @ApiModelProperty(value = "预点单状态 0 待支付； 1 已支付；2  客户取消；3 系统取消")
    private Integer orderStatus;

    @ApiModelProperty(value = "就餐人数")
    private String personCount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "预订日期")
    private Date scheduledDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "就餐时间段，开始时间")
    private Time timeStart;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "就餐时间段  结束时间")
    private Time timeEnd;

    @ApiModelProperty(value = "需求说明")
    private String customerRequire;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "下单时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "订单类型 0 订单；1 预点订单")
    private Integer orderType;

    @ApiModelProperty(value = "消费者信息")
    private UserCustomerInfoVo customerInfoVo;

    @ApiModelProperty(value = "订单菜品信息列表")
    private List<MenuItemInfoVo> menuItemInfoVos;

    @ApiModelProperty(value = "套餐信息列表")
    private List<OrderMealInfoVo> orderMealInfoVos;
}
