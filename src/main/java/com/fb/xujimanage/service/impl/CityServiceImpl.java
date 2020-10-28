package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.RestaurantDao;
import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.vo.AreaVo;
import com.fb.xujimanage.entity.vo.CityAreaVo;
import com.fb.xujimanage.service.CityService;
import com.fb.xujimanage.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by xw on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    private RestaurantDao restaurantDao;

    @Autowired
    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    /**
     * 查询城市及区域信息
     *
     * @return
     */
    @Override
    public CommonResult<List<CityAreaVo>> findCityList() {
        List<Restaurant> restaurants = restaurantDao.selectCity();
        if (CollectionUtils.isEmpty(restaurants)) {
            return CommonResult.fail("城市和区域信息不存在");
        }
        Set<String> set = new HashSet<>();
        for (Restaurant restaurant : restaurants) {
            set.add(restaurant.getCity());
        }
        List<CityAreaVo> cityAreaVos = new LinkedList<>();
        for (String str : set) {
            CityAreaVo cityAreaVo = new CityAreaVo();
            cityAreaVo.setCity(str);
            List<AreaVo> areaVoList = new LinkedList<>();
            for (Restaurant restaurant : restaurants) {
                if (str.equals(restaurant.getCity())) {
                    AreaVo areaVo = new AreaVo();
                    areaVo.setArea(restaurant.getArea());
                    areaVoList.add(areaVo);
                }
            }
            cityAreaVo.setAreaVos(areaVoList);
            cityAreaVos.add(cityAreaVo);
        }
        return CommonResult.ok(cityAreaVos);
    }

}
