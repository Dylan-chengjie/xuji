package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.SellingPointResVo;
import com.fb.xujimanage.entity.vo.SellingPointVo;
import com.fb.xujimanage.service.IFileCheckService;
import com.fb.xujimanage.service.ISellingPointService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 11:08
 * @description:就餐信息维护模块:服务特色
 */
@RestController
@AuthToken
@RequestMapping("/repastInfo/sellingPoint")
@Api(value = "SellingPointController", description = "就餐信息维护模块:服务特色")
public class SellingPointController {

    private ISellingPointService sellingPointService;

    @Autowired
    public void setSellingPointService(ISellingPointService sellingPointService) {
        this.sellingPointService = sellingPointService;
    }

    private IFileCheckService fileCheckService;

    @Autowired
    public void setFileCheckService(IFileCheckService fileCheckService) {
        this.fileCheckService = fileCheckService;
    }

    /**
     * 服务特色维护:分页查询服务特色列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "服务特色维护:分页查询服务特色列表", response = SellingPointResVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = false, dataType = "int"),
            @ApiImplicitParam(name = "sellingPointName", value = "服务特色名字", required = false, dataType = "String")
    })
    @GetMapping(value = "/getSellingPointList")
    public CommonResult<List<SellingPointResVo>> getSellingPointList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                                     @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                                     @RequestParam(value = "sellingPointName", defaultValue = "") String sellingPointName) {
        CommonResult<List<SellingPointResVo>> commonResult = sellingPointService.querySellingPointList(currentPage, pageSize, sellingPointName);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }


    /**
     * 服务特色维护:修改服务特色信息
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务特色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "serviceExplain", value = "serviceExplain", required = true, dataType = "String"),
            @ApiImplicitParam(name = "restaurantId", value = "门店id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "long"),
            @ApiImplicitParam(value = "环境布置视频数组", name = "videoFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "环境布置图片数组", name = "imageFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "原上传的视频数组，更新须删除的id", name = "videoId", required = false),
            @ApiImplicitParam(value = "原上传的图片数组，更新须删除的id", name = "imageId", required = false),
            @ApiImplicitParam(value = "服务特色id", name = "serviceId", required = true, dataType = "String")
    })
    @ApiOperation(value = "服务特色维护:修改服务特色信息")
    @PostMapping(value = "/updateSellingPoint")
    public CommonResult updateSellingPoint(MultipartFile[] imageFile, MultipartFile[] videoFile,
                                           @RequestParam String name, @RequestParam String serviceExplain, @RequestParam long restaurantId,
                                           @RequestParam Long sceneId, Long[] imageId, Long[] videoId, @RequestParam long serviceId) {
        CommonResult checkResult = fileCheckService.fileCheck(imageFile, videoFile);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }
        SellingPointVo sellingPointVo = new SellingPointVo();
        sellingPointVo.setId(serviceId);
        sellingPointVo.setImageFile(imageFile);
        sellingPointVo.setVideoFile(videoFile);
        sellingPointVo.setName(name);
        sellingPointVo.setServiceExplain(serviceExplain);
        sellingPointVo.setRestaurantId(restaurantId);
        sellingPointVo.setSceneId(sceneId);
        sellingPointVo.setImageId(imageId);
        sellingPointVo.setVideoId(videoId);
        CommonResult commonResult = sellingPointService.updateSellingPoint(sellingPointVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 服务特色维护:新增服务特色信息
     *
     * @return
     */
    @ApiOperation(value = "服务特色维护:新增服务特色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务特色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "serviceExplain", value = "serviceExplain", required = true, dataType = "String"),
            @ApiImplicitParam(name = "restaurantId", value = "门店id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "long"),
            @ApiImplicitParam(value = "环境布置视频数组", name = "videoFile", required = true, dataType = "__file"),
            @ApiImplicitParam(value = "环境布置图片数组", name = "imageFile", required = true, dataType = "__file")
    })
    @PostMapping(value = "/increaseSellingPoint")
    public CommonResult increaseSellingPoint(@RequestParam MultipartFile[] imageFile, @RequestParam MultipartFile[] videoFile,
                                             @RequestParam String name, @RequestParam String serviceExplain, @RequestParam long restaurantId,
                                             @RequestParam Long sceneId) {
        CommonResult checkResult = fileCheckService.fileCheck(imageFile, videoFile);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }
        SellingPointVo sellingPointVo = new SellingPointVo();
        sellingPointVo.setImageFile(imageFile);
        sellingPointVo.setVideoFile(videoFile);
        sellingPointVo.setName(name);
        sellingPointVo.setServiceExplain(serviceExplain);
        sellingPointVo.setRestaurantId(restaurantId);
        sellingPointVo.setSceneId(sceneId);
        CommonResult commonResult = sellingPointService.addSellingPoint(sellingPointVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 服务特色维护:删除服务特色信息
     *
     * @param serviceId:服务特色id
     * @return
     */
    @ApiOperation(value = "服务特色维护:删除服务特色信息")
    @ApiImplicitParam(name = "serviceId", value = "服务特色id", required = true, dataType = "int")
    @DeleteMapping(value = "/removeMenuInfo")
    public CommonResult removeSellingPointById(long serviceId) {
        CommonResult commonResult = sellingPointService.deleteSellingPointById(serviceId);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }
}
