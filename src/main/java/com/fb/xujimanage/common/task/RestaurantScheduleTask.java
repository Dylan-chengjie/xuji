package com.fb.xujimanage.common.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.vo.RestaurantDetailsVo;
import com.fb.xujimanage.service.RestaurantService;
import com.fb.xujimanage.util.RedisUtil;
import com.fb.xujimanage.util.zhongtaiapi.ApiCallHandler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author chengjie
 * @version 1.0
 * @date 2020/9/4 15:46
 * @description:门店组织信息获取
 */
@Log4j
@Component
public class RestaurantScheduleTask {
    @Value("${zhongtai.domain.url}")
    private String domainUrl;
    @Value("${org.api.url}")
    private String orgUrl;
    @Value("${api.app.key}")
    private String ak;
    @Value("${api.app.secret.key}")
    private String sk;
    private RestaurantService restaurantService;
    private RedisUtil redisUtil;
    private RestTemplate restTemplate;

    public RestaurantScheduleTask(RestaurantService restaurantService, RedisUtil redisUtil, RestTemplate restTemplate) {
        this.restaurantService = restaurantService;
        this.redisUtil = redisUtil;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void syncRestaurantList() {
        String uuid = UUID.randomUUID().toString();
        try {
            if (redisUtil.lock(Constants.RESTAURANT_LOCK, uuid, 5)) {
                HttpHeaders headers = new HttpHeaders();
                String timestamp = System.currentTimeMillis() + "";
                String authorizeHeader = ApiCallHandler.getAuthorizeHeader(HttpMethod.POST.name(), orgUrl, timestamp, ak, sk);
                headers.set("X-TC-Timestamp", timestamp);
                headers.set("Authorization", authorizeHeader);
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(domainUrl + orgUrl, new HttpEntity<>(headers), JSONObject.class);
                byte[] data = JSON.toJSONBytes(responseEntity.getBody().get("data"));
                RestaurantDetailsVo[] parse = JSON.parseObject(data, RestaurantDetailsVo[].class);
                List<RestaurantDetailsVo> restaurantDetailsVos = new ArrayList<>();
                Collections.addAll(restaurantDetailsVos, parse);
                restaurantService.batchSyncRestaurantList(restaurantDetailsVos);
            } else {
                log.warn("=====【warn】===== execute class:RestaurantScheduleTask method:sourceLoginLogPush result:未获取redis锁");
            }
        } finally {
            redisUtil.unlock(Constants.RESTAURANT_LOCK, uuid);
        }

    }
}
