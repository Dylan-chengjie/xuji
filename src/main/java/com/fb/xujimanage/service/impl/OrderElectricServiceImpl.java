package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.OrderElectricDao;
import com.fb.xujimanage.entity.dto.OrderElectricPageDto;
import com.fb.xujimanage.entity.vo.OrderElectricDetailsVo;
import com.fb.xujimanage.entity.vo.OrderElectricPageVo;
import com.fb.xujimanage.service.OrderElectricService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 电子订单
 *
 */
@Service
public class OrderElectricServiceImpl implements OrderElectricService {

    @Resource
    private OrderElectricDao orderElectricDao;

    @Override
    public PageInfo<OrderElectricPageVo> pageList(OrderElectricPageDto dto)  {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate=  dto.getOrderTimeBegin() != null? format.format( new Date(dto.getOrderTimeBegin()))+ " 00:00:00":null;
        String endDate= dto.getOrderTimeEnd() != null ? format.format(new Date(dto.getOrderTimeEnd()))+" 23:59:59" :null;
        List<OrderElectricPageVo> list = orderElectricDao.pageList(dto.getCityCode(), dto.getNameOrOrderNo(), beginDate,endDate);
        return new PageInfo<>(list);
    }

    @Override
    public List<OrderElectricDetailsVo> getOrderMealDetailsByMealId(Long mealId,Long orderId) {
        return orderElectricDao.getOrderMealDetailsByMealId(mealId, orderId);
    }
}
