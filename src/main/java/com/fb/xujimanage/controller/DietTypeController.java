package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.dto.DietTypeQueryDto;
import com.fb.xujimanage.entity.dto.DietTypeUpdateDto;
import com.fb.xujimanage.service.DietTypeService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品类别表(DietType)表控制层
 *
 * @author makejava
 * @since 2020-08-28 17:53:50
 */
@Api(description = "菜品分类")
@RestController
@AuthToken
@RequestMapping("/dietType")
public class DietTypeController {
    /**
     * 服务对象
     */
    @Resource
    private DietTypeService dietTypeService;


    /**
     * 更新菜品分类
     *
     * @param dietTypeUpdateDto 菜品分类
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("更新菜品分类")
    public CommonResult update(@RequestBody DietTypeUpdateDto dietTypeUpdateDto) {
        Integer count = dietTypeService.update(dietTypeUpdateDto);
        return count > 0 ? CommonResult.ok("更新成功") : CommonResult.fail("更新失败");
    }


    /**
     * 查找菜品分类
     *
     * @param
     * @return
     */
    @ApiOperation("分页获取菜品分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "city", value = "城市编码", dataType = "String"),
            @ApiImplicitParam(name = "restaurantCode", value = "门店编码", dataType = "String"),
            @ApiImplicitParam(name = "codeList", value = "门店code", dataType = "String")
    })
    @GetMapping("/findAll")
    public CommonResult findAll(@RequestParam Integer pageNum, Integer pageSize, String city, String restaurantCode, String codeList) {
        DietTypeQueryDto queryDto = new DietTypeQueryDto();
        if (StringUtil.isNotBlank(codeList)) {
            List<String> idList = Arrays.asList(codeList.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
            queryDto.setCodeList(idList);
        }
        if (StringUtil.isNotBlank(city)) {
            queryDto.setCity(city);
        }
        if (StringUtil.isNotBlank(restaurantCode)) {
            queryDto.setRestaurantCode(restaurantCode);
        }
        return dietTypeService.findAll(pageNum, pageSize, queryDto);

    }


    /**
     * 获取菜品分类的下拉列表
     *
     * @param restaurantCode 门店编码
     * @return
     */
    @ApiOperation("根据门店code获取门店下面的菜品分类的下拉列表")
    @ApiImplicitParam(name = "restaurantCode", value = "门店编码", required = true, dataType = "string")
    @GetMapping("/findDietTypeByRCode")
    public CommonResult findDietType(@RequestParam(value = "restaurantCode") String restaurantCode) {
        return dietTypeService.findDietType(restaurantCode);
    }




    /* @PostMapping("/add")
    @ApiOperation("添加菜品分类")
    public CommonResult add(@RequestBody @Validated DietTypeDto dietTypeDto) {
        Integer count =dietTypeService.add(dietTypeDto);
        return count>0?CommonResult.ok("添加成功"):CommonResult.fail("添加失败");
    }*/

     /*  @ApiOperation("删除菜品分类")
    @DeleteMapping("/delete")
    public CommonResult delete(Long id) {
        return dietTypeService.deleteById(id)>0?CommonResult.ok():CommonResult.fail();
    }*/


   /* @ApiOperation("通过主键查询单条数据")
    @GetMapping("/selectOne")
    public CommonResult selectOne(Long id) {
        return CommonResult.ok(dietTypeService.queryById(id));

    }*/

}