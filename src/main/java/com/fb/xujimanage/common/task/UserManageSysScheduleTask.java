package com.fb.xujimanage.common.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.service.UserManageSysService;
import com.fb.xujimanage.util.RedisUtil;
import com.fb.xujimanage.util.zhongtaiapi.ApiCallHandler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author chengjie
 * @version 1.0
 * @date 2020/9/3 15:46
 * @description:员工及登录信息获取
 */
@Log4j
@Component
public class UserManageSysScheduleTask {
    @Value("${zhongtai.domain.url}")
    private String domainUrl;
    @Value("${staff.api.url}")
    private String staffUrl;
    @Value("${api.app.key}")
    private String ak;
    @Value("${api.app.secret.key}")
    private String sk;
    @Value("#{'${retain.user.names}'.split(',')}")
    private List<String> retainUserNames;
    private UserManageSysService userManageSysService;
    private RedisUtil redisUtil;
    private RestTemplate restTemplate;

    public UserManageSysScheduleTask(UserManageSysService userManageSysService, RedisUtil redisUtil, RestTemplate restTemplate) {
        this.userManageSysService = userManageSysService;
        this.redisUtil = redisUtil;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void syncUserManageSys() {
        String uuid = UUID.randomUUID().toString();
        try {
            if (redisUtil.lock(Constants.USER_MANAGE_SYS_LOCK, uuid, 5)) {
                HttpHeaders headers = new HttpHeaders();
                System.out.println(retainUserNames);
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                String timestamp = System.currentTimeMillis() + "";
                String authorizeHeader = ApiCallHandler.getAuthorizeHeader(HttpMethod.POST.name(), staffUrl, timestamp, ak, sk);
                headers.set("X-TC-Timestamp", timestamp);
                headers.set("Authorization", authorizeHeader);
                HttpEntity request = new HttpEntity<>(headers);
                ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(domainUrl + staffUrl, request, JSONObject.class);
                JSONObject body = responseEntity.getBody();
                byte[] data = JSON.toJSONBytes(body.get("data"));
                UserManageSys[] parse = JSON.parseObject(data, UserManageSys[].class);
                List<UserManageSys> userManageSysList = new ArrayList<>();
                Collections.addAll(userManageSysList, parse);
                userManageSysService.batchSyncUserManageSys(userManageSysList, retainUserNames);
            } else {
                log.warn("=====【warn】===== execute class:SourceBrowseScheduleTask method:sourceLoginLogPush result:未获取redis锁");
            }
        } finally {
            redisUtil.unlock(Constants.USER_MANAGE_SYS_LOCK, uuid);
        }

    }
}
