package com.fb.xujimanage.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.fb.xujimanage.dao.RestaurantMenuItemRelateDao;
import com.fb.xujimanage.entity.RestaurantMenuItemRelate;

public  interface RestaurantMenuItemRelateService extends IService<RestaurantMenuItemRelate> {


    void updateRestaurantMenuItemRelate(Long restaurantId,Long menuItemid);

}
