package com.fb.xujimanage.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fb.xujimanage.dao.RestaurantMenuItemRelateDao;
import com.fb.xujimanage.entity.RestaurantMenuItemRelate;
import com.fb.xujimanage.service.RestaurantMenuItemRelateService;
import com.fb.xujimanage.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;



@Service
public class RestaurantMenuItemRelateServiceImpl extends
        ServiceImpl<RestaurantMenuItemRelateDao, RestaurantMenuItemRelate>
        implements RestaurantMenuItemRelateService {


    @Autowired
    private  RestaurantMenuItemRelateDao restaurantMenuItemRelateDao;



    @Override
    @Transactional
    public void updateRestaurantMenuItemRelate(Long restaurantId,Long menuItemid){
        //查询
        /*LambdaQueryWrapper<RestaurantMenuItemRelate> wrapper = new LambdaQueryWrapper<>();
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RestaurantMenuItemRelate::getRestaurantId,restaurantId);
        wrapper.eq(RestaurantMenuItemRelate::getMenuItemId,menuItemid);*/

        RestaurantMenuItemRelate res=restaurantMenuItemRelateDao.selectOne(restaurantId,menuItemid);
        if (res==null){
            res=new RestaurantMenuItemRelate();
            res.setRestaurantId(restaurantId);
            res.setMenuItemId(String.valueOf(menuItemid));
            res.setId(IdUtil.getUUID());
            restaurantMenuItemRelateDao.insert(res);
        }
    }
}
