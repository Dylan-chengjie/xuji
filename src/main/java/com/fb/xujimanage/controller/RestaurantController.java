package com.fb.xujimanage.controller;

import com.fb.xujimanage.dao.RestaurantStatusDto;
import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.dto.RestaurantImgDto;
import com.fb.xujimanage.entity.dto.RestaurantPageDto;
import com.fb.xujimanage.entity.vo.RestaurantCitySelectVo;
import com.fb.xujimanage.entity.vo.RestaurantPageVo;
import com.fb.xujimanage.entity.vo.RestaurantVo;
import com.fb.xujimanage.entity.vo.RestaurantsVo;
import com.fb.xujimanage.service.RestaurantService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 徐记电子门店信息Controller
 *
 * @author sam.yang
 * @since 2020-08-25 15:29:17
 */
@Api(description = "电子门店信息管理")
@RestController
@AuthToken
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @ApiOperation("电子门店下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "城市名称", dataType = "string"),
            @ApiImplicitParam(name = "restaurantName", value = "门店名称模糊搜索", dataType = "string")
    })
    @GetMapping("/list")
    public CommonResult getRestaurantList(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "restaurantName", required = false) String restaurantName) {
        List<RestaurantsVo> resultVoList = restaurantService.getRestaurantList(null, city, restaurantName);
        return CommonResult.ok("查询电子门店下拉列表成功", resultVoList);
    }


    @GetMapping("/pageList/img")
    @ApiOperation(value = "查询门店图片分页数据列表", notes = "false_老默", response = RestaurantPageVo.class)
    public CommonResult pageListRestaurantImg(@Valid RestaurantPageDto dto) {
        return CommonResult.ok("查询成功！", restaurantService.pageListRestaurantImg(dto));
    }

    @PutMapping("/update/status")
    @ApiOperation(value = "修改门店状态（启用/禁用）", notes = "false_老默", response = CommonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "门店ID", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "门店状态（0 启用  ； 1 禁用）", dataType = "Integer"),
    })
    public CommonResult updateStatus(@RequestBody RestaurantStatusDto dto) {
        return restaurantService.updateStatusById(dto.getId(), dto.getStatus());
    }

    @GetMapping("/city/select")
    @ApiOperation(value = "查询门店城市下拉框数据列表", notes = "false_老默", response = RestaurantCitySelectVo.class)
    @ApiImplicitParam(name = "status", value = "0-查询启用数据,其他查询所有数据")
    public CommonResult citySelect(@RequestParam(name = "status", required = false) Integer status) {
        return CommonResult.ok("查询成功！", restaurantService.getRestaurantCitySelect(status));
    }

    @PostMapping("update/img")
    @ApiOperation(value = "修改门店图片", notes = "false_老默", response = CommonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "门店ID", dataType = "Long"),
            @ApiImplicitParam(name = "cityCode", value = "门店所属城市编码", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "门店详细地址", dataType = "String"),
            @ApiImplicitParam(name = "imgId", value = "门店图片ID", dataType = "String"),
            @ApiImplicitParam(name = "vrUrl", value = "门店vr路径", dataType = "String"),
            @ApiImplicitParam(name = "contactWay", value = "门店联系电话", dataType = "String")
    })
    public CommonResult updateRestaurantImg(@RequestParam(name = "file", required = false) MultipartFile file,
                                            @RequestParam(name = "id", required = true) Long id,
                                            @RequestParam(name = "cityCode", required = false) String cityCode,
                                            @RequestParam(name = "address", required = false) String address,
                                            @RequestParam(name = "imgId", required = false) String imgId,
                                            @RequestParam(name = "vrUrl", required = false) String vrUrl,
                                            @RequestParam(name = "contactWay", required = false) String contactWay) {
        RestaurantImgDto dto = new RestaurantImgDto();
        dto.setImgId(imgId);
        dto.setId(id);
        dto.setVrUrl(vrUrl);
        dto.setAddress(address);
        dto.setCityCode(cityCode);
        dto.setContactWay(contactWay);
        return restaurantService.updateRestaurantImgById(dto, file);
    }

}