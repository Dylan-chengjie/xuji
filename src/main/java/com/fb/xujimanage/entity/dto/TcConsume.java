package com.fb.xujimanage.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TcConsume {

    private  String Eat_TcAutoid;//套餐ID
    private  String Eat_KindName;//类别
    private  String Eat_XFBMID;//菜品ID
    private  String Eat_XFCode;//菜品编码
    private  BigDecimal Eat_Number;//套餐编码
    private  BigDecimal Eat_AddMoney;//加价
    private  String SelectYn;//默认选择


}
