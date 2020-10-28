package com.fb.xujimanage.util.zhongtaiapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiClient implements Serializable {

    public ApiClient(){}

    public ApiClient(String appKeyId, String appSecretKey){
        this.appKeyId = appKeyId;
        this.appSecretKey = appSecretKey;
    }

    private String appKeyId ;

    private String appSecretKey;

}
