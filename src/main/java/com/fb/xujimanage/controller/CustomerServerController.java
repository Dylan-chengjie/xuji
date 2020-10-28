package com.fb.xujimanage.controller;

import com.fb.xujimanage.dao.RestaurantDao;
import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.dto.CustomerNameDto;
import com.fb.xujimanage.entity.dto.CustomerServerDto;
import com.fb.xujimanage.entity.vo.CustomerServerVo;
import com.fb.xujimanage.service.CustomerServerService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客服信息表(CustomerServer)
 *
 * @author Sam.yang
 * @since 2020-08-24 17:08:05
 */
@Api(description = "客服信息管理")
@RestController
@AuthToken
@RequestMapping("/server")
public class CustomerServerController {


    @Autowired
    private CustomerServerService customerServerService;

    @Autowired
    private RestaurantDao restaurantDao;


    /**
     * 客服信息列表
     */
    @ApiOperation(value = "查询客服信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int", defaultValue = "10", required = true)})
    @GetMapping("/getCustomerServerList")
    public CommonResult getCustomerServerList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                              CustomerNameDto customerNameDto) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        return customerServerService.getCustomerServerInfoList(pageNum, pageSize, customerNameDto);
    }

    /**
     * 删除客服信息
     */
    @ApiOperation(value = "删除客服信息")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteCustomerServerById(@PathVariable Integer id) {
        Integer count = customerServerService.deleteCustomerServerById(id);
        return count > 0 ? CommonResult.ok("删除客服信息成功") : CommonResult.fail("删除客服信息失败");
    }

    /**
     * 添加客服信息
     */
    @ApiOperation(value = "添加客服信息")
    @PostMapping(value = "/add")
    public CommonResult addCustomerServer(@RequestBody CustomerServerDto customerServerDto) {
        Integer count = customerServerService.editCustomerServer(customerServerDto);
        return count > 0 ? CommonResult.ok("添加客服信息成功") : CommonResult.fail("添加客服信息失败");
    }


    /**
     * 编辑客服信息
     */
    @ApiOperation(value = "编辑客服信息")
    @PutMapping(value = "/update")
    public CommonResult updateCustomerServer(@RequestBody CustomerServerDto customerServerDto) {
        Integer count = customerServerService.editCustomerServer(customerServerDto);
        return count > 0 ? CommonResult.ok("编辑客服信息成功") : CommonResult.fail("编辑客服信息失败");
    }


    /**
     * 查询客服信息
     */
    @ApiOperation(value = "查询客服信息")
    @GetMapping(value = "/{id}")
    public CommonResult getCustomerServerById(@PathVariable Integer id) {
        CustomerServerVo customerServerVo = customerServerService.getCustomerServerById(id);
        return CommonResult.ok("查询客服信息成功", customerServerVo);
    }

    @ApiOperation("/获取员工信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restaurantCode", value = "门店Id", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "string")})
    @GetMapping("/findAll")
    public CommonResult findAll(@RequestParam(value = "restaurantCode", required = false) String restaurantCodes,
                                @RequestParam(value = "mobile", required = false) String mobile) {

        UserManageSys userManageSys = new UserManageSys();
        if (StringUtil.isNotBlank(restaurantCodes)) {
            List<String> ids = Arrays.asList(restaurantCodes.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
            userManageSys.setRestaurantCodes(ids);
        }
        if (StringUtil.isNotBlank(mobile)) {
            userManageSys.setMobile(mobile);
        }
        return CommonResult.ok("获取客服列表", customerServerService.findAll(userManageSys));
    }


}