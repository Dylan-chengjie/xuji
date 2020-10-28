package com.fb.xujimanage.entity.dto;

import lombok.Data;
import sun.dc.pr.PRError;

import java.util.List;
//套餐信息列表
@Data
public class TcInfo {

    private String Eat_TcAutoid; //套餐ID

    private String Eat_XFBMID;//菜品ID

    private String Eat_TcCode;//套餐编码

    private String Eat_TcName;//套餐名称

    private String Eat_Bdate;//套餐名称

    private  String Eat_Edate;// 结束日期
    private  int TcStatus; //1正常 2停用 0删除


    private List<TcConsume> TcConsumeList;//套餐明细



}
