package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.dto.OrderElectricPageDto;
import com.fb.xujimanage.entity.vo.OrderElectricDetailsVo;
import com.fb.xujimanage.entity.vo.OrderElectricPageVo;
import com.fb.xujimanage.service.OrderElectricService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Api(description = "查询到店点单数据")
@AuthToken
@RequestMapping("/order/electric")
@Validated
public class OrderElectricController {

    @Resource
    private OrderElectricService orderElectricService;


    @GetMapping("/pageList")
    @ApiOperation(value = "查询到店点单订单分页信息",notes = "false_老默",response = OrderElectricPageVo.class)
    public CommonResult pageList(@Valid OrderElectricPageDto dto){
        return CommonResult.ok("查询成功",orderElectricService.pageList(dto));
    }

    @GetMapping("meal/details")
    @ApiOperation(value = "查询订单菜品中套餐详情信息",notes = "false_老默",response = OrderElectricDetailsVo.class)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "orderId", value = "订单ID", dataType = "Long", required = true),
            @ApiImplicitParam(name = "mealId", value = "套餐ID", dataType = "Long", required = true)
    })
    public CommonResult orderMealDetails( @NotNull(message = "套餐ID不能为空") @RequestParam(value = "mealId")Long mealId,
                                         @NotNull(message = "订单ID不能为空") @RequestParam(value = "orderId")Long orderId){
        return CommonResult.ok("查询成功",orderElectricService.getOrderMealDetailsByMealId(mealId, orderId));
    }
}
