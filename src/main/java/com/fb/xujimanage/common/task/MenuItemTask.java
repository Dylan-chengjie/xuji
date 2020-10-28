package com.fb.xujimanage.common.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.common.constant.MenuItemConstants;
import com.fb.xujimanage.dao.MenuItemDao;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.dto.XFBMInfo;
import com.fb.xujimanage.entity.vo.RestaurantVo;
import com.fb.xujimanage.enums.OrgTypeEnum;
import com.fb.xujimanage.service.DietTypeService;
import com.fb.xujimanage.service.IMenuitemCopyService;
import com.fb.xujimanage.service.RestaurantMenuItemRelateService;
import com.fb.xujimanage.service.RestaurantService;
import com.fb.xujimanage.util.RedisUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author summer.chou
 * @version 1.0
 * @date 2020/9/20
 * @description:菜品信息表、门店菜品关联表、菜品分类表、套餐表、套餐详情表 数据导入
 */
@Log4j
@Component
public class MenuItemTask {


    @Value("${org.api.zp.url}")
    private String orgUrl;
    private IMenuitemCopyService iMenuitemCopyService;

    private RestaurantService restaurantService;
    private RedisUtil redisUtil;
    private RestTemplate restTemplate;
    private MenuItemDao menuItemDao;


    public MenuItemTask(RestaurantService restaurantService, IMenuitemCopyService iMenuitemCopyService, DietTypeService dietTypeService,
                        RestaurantMenuItemRelateService restaurantMenuItemRelateService, RedisUtil redisUtil, RestTemplate restTemplate,MenuItemDao menuItemDao) {

        this.restaurantService=restaurantService;
        this.iMenuitemCopyService = iMenuitemCopyService;
        this.redisUtil = redisUtil;
        this.restTemplate = restTemplate;
        this.menuItemDao = menuItemDao;
    }


    @Scheduled(cron = "0 0 3 * * ?")//0 0/59 * * * ?
    public void sourceMenuItemTask() {
        String uuid = UUID.randomUUID().toString();
        try {
            if (redisUtil.lock(Constants.RESTAURANT_LOCK, uuid, 50)) {
                //获取门店的信息
                List<RestaurantVo> restaurantVos = restaurantService.selectRestaurantByStore(OrgTypeEnum.STORE.getType());
                if (!restaurantVos.isEmpty()) {
                    for (RestaurantVo restaurantVo : restaurantVos) {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                        //请求参数
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("method", "GetXFBMInfoByDoor");//请求方法
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("Door_Code", restaurantVo.getCode());//门店编码
                        map.put("data", map1);
                        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);
                        //调用请求
                        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(orgUrl, request, JSONObject.class);
                        //然后把str转换成JSON再通过getJSONObject()方法获取到里面的result对象，因为我想要的数据都在result里面
                        JSONObject json = responseEntity.getBody();
                        if (json.getBoolean("success")) {
                            log.info("返回结果：json：{}" + json);
                            //执行业务逻辑
                            //菜品信息
                            JSONArray menultem = json.getJSONObject("data").getJSONArray("XFBMInfoList");
                            List<XFBMInfo> xfbmInfos = JSONArray.parseArray(menultem.toJSONString(), XFBMInfo.class);
                            if (!xfbmInfos.isEmpty()) {
                                iMenuitemCopyService.sourceMenuItemTask(restaurantVo, xfbmInfos);

                            }

                        }
                    }
                }

                } else {
                    log.warn("=====【warn】===== execute class:MenuItemTask method:sourceLoginLogPush result:未获取redis锁");
                }
            startTask();
            } finally{
                redisUtil.unlock(Constants.RESTAURANT_LOCK, uuid);
            }


    }

    /**
     * 启动汇总菜品数量，解决分页查询菜品信息count(*)过慢问题
     */
    @PostConstruct
    public void startTask() {
        int menuItemTotal = menuItemDao.menuItemTotalCount();
        redisUtil.set(MenuItemConstants.MENU_ITEM_TOTAL,menuItemTotal);
    }

}
