package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.CityAreaVo;
import com.fb.xujimanage.service.CityService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 13:56
 * @description:查询门店城市区域列表
 */
@RestController
@Api(description = "查询门店城市区域列表")
@AuthToken
@RequestMapping("/restaurant/city")
public class CityRestController {

    private CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 查询门店城市区域列表
     * @return
     */
    @ApiOperation(value = "查询门店城市区域列表")
    @GetMapping("/findCityArea")
    public CommonResult findCityArea() {
        CommonResult<List<CityAreaVo>> cityList = cityService.findCityList();
        if (cityList == null) {
            return CommonResult.fail("城市信息查询异常");
        }
        return cityList;
    }

}
