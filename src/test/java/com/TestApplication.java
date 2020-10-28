package com;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.XujimanageApplication;
import com.fb.xujimanage.entity.vo.MenuItemVo;
import com.fb.xujimanage.service.IMenuitemCopyService;
import com.fb.xujimanage.service.IMenuitemService;
import com.fb.xujimanage.util.Md5SignUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
/** 指定  @SpringBootApplication  启动类 和 端口  **/
@SpringBootTest(classes  = XujimanageApplication.class)
public class TestApplication {

    @Value("${org.api.key}")
    private String app_key;
    @Autowired
    private IMenuitemCopyService iMenuitemCopyService;

    @Test
    public void Mytest(){
        JSONObject json=new  JSONObject();
        json.put("startTime","qqqqqqqqqqqq");
        String sign=  Md5SignUtils.Md5Sign(app_key,json);
        System.out.println("sign==========>"+sign);
        boolean bool=Md5SignUtils.checkSign(json,app_key,sign);
        System.out.println("=================>"+bool);

        

    }
     
    @Test
    public void checkSign(){
        String sign="CBDE2BD41BA2452A8C5BD4F140073A1D";
        JSONObject json=new  JSONObject();
        json.put("startTime","qqqqqqqqqqqq");
        boolean bool=Md5SignUtils.checkSign(json,app_key,sign);
        System.out.println("=================>"+bool);
    }


    @Test
    public void updatMenuItem(){
        List<MenuItemVo>list=new ArrayList<>();
        MenuItemVo menuItemVo=new MenuItemVo();
        menuItemVo.setQuantity(BigDecimal.valueOf(999));
        menuItemVo.setHaveKind(1);
        menuItemVo.setId("2019091000000012");
        list.add(menuItemVo);
        int count= iMenuitemCopyService.updatMenuItem(list);
        System.out.println("===============>"+count);
    }

}
