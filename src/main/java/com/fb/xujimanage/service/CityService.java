package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.vo.CityAreaVo;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * 城市业务逻辑接口类
 *
 * Created by xw on 07/02/2017.
 */
public interface CityService {

    /**
     * 查询城市及区域信息
     * @return
     */
    CommonResult<List<CityAreaVo>> findCityList();
}
