package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.CityImg;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.dto.CityImgDto;
import com.fb.xujimanage.entity.dto.CityImgPageDto;
import com.fb.xujimanage.entity.vo.CityImgPageVo;
import com.fb.xujimanage.service.CityImgService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Api(description = "查询城市图片")
@AuthToken
@RequestMapping("/city/img")
@Validated
public class CityImgController {
    @Resource
    private CityImgService cityImgService;

    /**
     * 新增
     */

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "修改城市图片信息",notes = "false_老默",response = CommonResult.class)
    public CommonResult insert(@RequestParam(name = "file", required = false) MultipartFile file,
                               @RequestParam("cityCode")String cityCode,
                               @RequestParam("imageId")String imageId,
                               @RequestParam("cityName")String cityName){
        CityImgDto dto =new CityImgDto();
        dto.setCityCode(cityCode);
        dto.setCityName(cityName);
        dto.setImageId(imageId);
        return cityImgService.update(dto,file);
    }


    @GetMapping("/all/list")
    @ApiOperation(value = "查询城市信息",notes = "false_老默",response = CityImg.class)
    public CommonResult allList(){
        return CommonResult.ok("查询成功",cityImgService.allList());
    }

    @GetMapping("/pageList")
    @ApiOperation(value = "查询城市图片分页信息",notes = "false_老默",response = CityImgPageVo.class)
    public CommonResult pageList(@Valid CityImgPageDto dto){
        return CommonResult.ok("查询成功",cityImgService.pageList(dto));
    }



}
