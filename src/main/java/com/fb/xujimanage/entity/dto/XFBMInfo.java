package com.fb.xujimanage.entity.dto;

import lombok.Data;

import java.util.List;
@Data
public class XFBMInfo {


    private   String Eat_XFBMID;//菜品ID
    private   String Eat_XFCode;//菜品编码
    private   String Eat_XFName;//菜品名称
    private   String Eat_XFSize;//单位
    private   String Eat_XFPrice;//销售单价
    private   String Eat_ParPrice;//销售会员价
    private   String Eat_Code1;//大类编码
    private   String Eat_Name1;//大类名称
    private   String Eat_Code2;//小类编码
    private   String Eat_Name2;//小类名称
    private   String XFKind;  //菜品类型 1单菜 2套餐
    private   int    XFStatus; //1正常 2停用 0删除
    private   List<TcInfo> TcInfoList;//套餐信息列表

}
