package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.EnvironmentalResVo;
import com.fb.xujimanage.entity.vo.EnvironmentalVo;
import com.fb.xujimanage.service.IEnvironmentalService;
import com.fb.xujimanage.service.IFileCheckService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 19:47
 * @description:就餐信息维护模块:环境布置
 */
@RestController
@AuthToken
@RequestMapping("/repastInfo/environmental")
@Api(value = "EnvironmentalController", description = "就餐信息维护模块:环境布置")
public class EnvironmentalController {

    private IEnvironmentalService environmentalService;

    @Autowired
    public void setEnvironmentalService(IEnvironmentalService environmentalService) {
        this.environmentalService = environmentalService;
    }

    private IFileCheckService fileCheckService;

    @Autowired
    public void setFileCheckService(IFileCheckService fileCheckService) {
        this.fileCheckService = fileCheckService;
    }


    /**
     * 分页查询环境布置信息列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询环境布置信息列表", response = EnvironmentalResVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "environmentalName", value = "环境配置名字", required = false, dataType = "String")
    })
    @GetMapping(value = "/getEnvironmentalInfo")
    public CommonResult<List<EnvironmentalResVo>> getEnvironmentalInfo(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                                       @RequestParam(value = "environmentalName", defaultValue = "") String environmentalName) {
        CommonResult<List<EnvironmentalResVo>> commonResult = environmentalService.queryEnvironmentalInfo(currentPage, pageSize, environmentalName);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }


    /**
     * 修改环境配置信息
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务特色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "restaurantId", value = "门店id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "long"),
            @ApiImplicitParam(value = "环境布置视频数组", name = "videoFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "环境布置图片数组", name = "imageFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "原上传的视频数组，更新须删除的id", name = "videoId", required = false),
            @ApiImplicitParam(value = "原上传的图片数组，更新须删除的id", name = "imageId", required = false),
            @ApiImplicitParam(value = "环境配置id", name = "environmentalId", required = true, dataType = "long")
    })
    @ApiOperation(value = "修改环境配置信息", consumes = "multipart/form-data")
    @PutMapping(value = "/updateEnvironmentalInfo")
    public CommonResult updateEnvironmentalInfo(MultipartFile[] imageFile, MultipartFile[] videoFile,
                                                @RequestParam String name, @RequestParam long restaurantId,
                                                @RequestParam Long sceneId, Long[] imageId, Long[] videoId, @RequestParam long environmentalId) {
        CommonResult checkResult = fileCheckService.fileCheck(imageFile, videoFile);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }
        EnvironmentalVo environmentalVo = new EnvironmentalVo();
        environmentalVo.setId(environmentalId);
        environmentalVo.setImageFile(imageFile);
        environmentalVo.setVideoFile(videoFile);
        environmentalVo.setName(name);
        environmentalVo.setRestaurantId(restaurantId);
        environmentalVo.setSceneId(sceneId);
        environmentalVo.setImageId(imageId);
        environmentalVo.setVideoId(videoId);
        CommonResult commonResult = environmentalService.updateEnvironmentalInfo(environmentalVo);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 新增环境配置信息
     *
     * @return
     */
    @ApiOperation(value = "新增环境配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务特色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "serviceExplain", value = "serviceExplain", required = true, dataType = "String"),
            @ApiImplicitParam(name = "restaurantId", value = "门店id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "long"),
            @ApiImplicitParam(value = "环境布置视频数组", name = "videoFile", required = true, dataType = "__file"),
            @ApiImplicitParam(value = "环境布置图片数组", name = "imageFile", required = true, dataType = "__file")
    })
    @PostMapping(value = "/increaseEnvironmentalInfo")
    public CommonResult increaseEnvironmentalInfo(@RequestParam MultipartFile[] imageFile, @RequestParam MultipartFile[] videoFile,
                                                  @RequestParam String name, @RequestParam long restaurantId,
                                                  @RequestParam Long sceneId) {
        CommonResult checkResult = fileCheckService.fileCheck(imageFile, videoFile);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }
        EnvironmentalVo environmentalVo = new EnvironmentalVo();
        environmentalVo.setImageFile(imageFile);
        environmentalVo.setVideoFile(videoFile);
        environmentalVo.setName(name);
        environmentalVo.setRestaurantId(restaurantId);
        environmentalVo.setSceneId(sceneId);
        CommonResult commonResult = environmentalService.addEnvironmentalInfo(environmentalVo);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 根据environmentalId删除环境配置信息
     *
     * @param environmentalId:场景id
     * @return
     */
    @ApiOperation(value = "删除环境配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "environmentalId", value = "环境id", required = true, dataType = "long", paramType = "query")
    })
    @GetMapping(value = "/removeEnvironmentalInfoById")
    public CommonResult removeEnvironmentalInfoById(long environmentalId) {

        CommonResult commonResult = environmentalService.deleteEnvironmentalInfoById(environmentalId);
        if (commonResult == null) {
            return CommonResult.fail();
        }
        return commonResult;
    }
}
