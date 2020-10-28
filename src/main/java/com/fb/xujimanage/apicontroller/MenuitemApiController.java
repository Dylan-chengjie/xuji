package com.fb.xujimanage.apicontroller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.entity.vo.MenuItemVo;
import com.fb.xujimanage.entity.vo.OrderDinnerInfoVo;
import com.fb.xujimanage.service.IMenuitemCopyService;
import com.fb.xujimanage.service.OrderDinnerService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 对外接口以/api 命名
 */
@RestController
@RequestMapping("/api/MenuItem")
@Api(value = "MenuitemApiController", description = "菜品剩余数量信息接口")
public class MenuitemApiController {

    @Value("${org.api.key}")
    private String API_KEY;
    private IMenuitemCopyService iMenuitemCopyService;
    private OrderDinnerService orderDinnerService;

    public MenuitemApiController(IMenuitemCopyService iMenuitemCopyService, OrderDinnerService orderDinnerService) {
        this.iMenuitemCopyService = iMenuitemCopyService;
        this.orderDinnerService = orderDinnerService;
    }

    @ApiOperation(value = "获取订单信息")
    @PostMapping("/listOrderDinner")
    public CommonResult<List<OrderDinnerInfoVo>> listOrderDinner(@RequestBody JSONObject json) {
        Date startTime = json.getDate("startTime");
        Long restaurantId = json.getLong("restaurantId");
        if (null == startTime || null == restaurantId) {
            return CommonResult.fail("startTime或restaurantId 不能为空");
        }
        return orderDinnerService.listOrderDinner(restaurantId, startTime);
    }

    @ApiOperation(value = "菜品剩余数量信息接口")
    @PostMapping("/add")
    public CommonResult getMenuItemList(@RequestBody JSONObject json) {
        JSONArray array = json.getJSONArray("menuItemVos");
        if (array == null) {
            return new CommonResult("menuItemVos菜品集合为空");
        }
        List<MenuItemVo> menuItemVos = JSONObject.parseArray(array.toJSONString(), MenuItemVo.class);
        iMenuitemCopyService.updatMenuItem(menuItemVos);
        return CommonResult.ok("数据更新成功");
    }
}
