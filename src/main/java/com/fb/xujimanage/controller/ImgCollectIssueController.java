package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.ImgCollectVo;
import com.fb.xujimanage.enums.ImgCollectEnum;
import com.fb.xujimanage.service.ImgCollectService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.TokenUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-13 10:37
 * @description:首页图集相关
 * @version:
 */
@Api(description = "首页图集（Banner/精彩活动/优惠特价）发布管理")
@RestController
@AuthToken
@RequestMapping("/img/collect")
public class ImgCollectIssueController {

    private ImgCollectService imgCollectService;

    public ImgCollectIssueController(ImgCollectService imgCollectService) {
        this.imgCollectService = imgCollectService;
    }

    @ApiOperation(value = "新增首页图集")
    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "图集名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "collectType", value = "图集类型:HOME_BANNER 首页广告，SPLENDID_EVENT 精彩活动，SPECIAL_PRICE 优惠特价", required = true, dataType = "string"),
            @ApiImplicitParam(name = "url", value = "跳转链接", required = true, dataType = "string"),
            @ApiImplicitParam(name = "remarks", value = "备注", dataType = "string"),
            @ApiImplicitParam(name = "general", value = "是否通版（0 非通版，1通版）", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = true, dataType = "string")
    })
    public CommonResult addImgCollect(@RequestParam("file") MultipartFile file,
                                      @RequestParam String name,
                                      @RequestParam ImgCollectEnum collectType,
                                      @RequestParam String url,
                                      @RequestParam(name = "remarks", required = false) String remarks,
                                      @RequestParam Integer general,
                                      @RequestParam Integer sortNum) {
        String userNum = TokenUtils.getUserNum();
        return imgCollectService.addImgCollect(file, name, collectType.getType(), url, remarks, general, sortNum, userNum, userNum);
    }

    @ApiOperation(value = "更新首页图集")
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "long"),
            @ApiImplicitParam(name = "imgId", value = "图片id,更新图片信息时为必填", required = true, dataType = "long"),
            @ApiImplicitParam(name = "name", value = "图集名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "collectType", value = "图集类型:HOME_BANNER 首页广告，SPLENDID_EVENT 精彩活动，SPECIAL_PRICE 优惠特价", required = true, dataType = "string"),
            @ApiImplicitParam(name = "url", value = "跳转链接", required = true, dataType = "string"),
            @ApiImplicitParam(name = "remarks", value = "备注", dataType = "string"),
            @ApiImplicitParam(name = "general", value = "是否通版（0 非通版，1通版）", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = true, dataType = "int")
    })
    public CommonResult updateImgCollect(@RequestParam(name = "file", required = false) MultipartFile file,
                                         @RequestParam Long id,
                                         @RequestParam Long imgId,
                                         @RequestParam String name,
                                         @RequestParam Integer general,
                                         @RequestParam ImgCollectEnum collectType,
                                         @RequestParam String url,
                                         @RequestParam(name = "remarks", required = false) String remarks,
                                         @RequestParam Integer sortNum) {
        return imgCollectService.updateImgCollect(file, id, imgId, name, general, collectType.getType(), url, remarks, sortNum, TokenUtils.getUserNum());
    }

    @ApiOperation(value = "删除首页图集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图集id", required = true, dataType = "string")
    })
    @DeleteMapping
    public CommonResult deleteImgCollect(@RequestParam Long id) {
        return imgCollectService.deleteImgCollect(id);
    }

    @ApiOperation(value = "分页查询首页图集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int"),
            @ApiImplicitParam(name = "collectType", value = "图集类型:HOME_BANNER 首页广告，SPLENDID_EVENT 精彩活动，SPECIAL_PRICE 优惠特价", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "图集名称（支持模糊）", dataType = "string")
    })
    @GetMapping
    public CommonResult<PageInfo<List<ImgCollectVo>>> pageImgCollect(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "collectType", required = false) ImgCollectEnum collectType,
            @RequestParam(name = "name", required = false) String name
    ) {
        return imgCollectService.pageImgCollect(pageNum, pageSize, collectType != null ? collectType.getType() : null, name);
    }
}
