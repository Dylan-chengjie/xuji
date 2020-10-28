package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.vo.ItemDetailsVo;
import com.fb.xujimanage.entity.vo.MenuItemResVo;
import com.fb.xujimanage.entity.vo.MenuitemReqVo;
import com.fb.xujimanage.entity.vo.UpdateMenuItemVo;
import com.fb.xujimanage.service.IFileCheckService;
import com.fb.xujimanage.service.IMenuitemService;
import com.fb.xujimanage.util.Arith;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 17:45
 * @description:就餐信息维护模块:菜品信息
 */
@RestController
@AuthToken
@RequestMapping("/repastInfo/menuItem")
@Api(value = "MenuitemController", description = "就餐信息维护模块:菜品信息")
public class MenuitemController {

    private IMenuitemService menuitemService;

    @Autowired
    public void setMenuitemService(IMenuitemService menuitemService) {
        this.menuitemService = menuitemService;
    }

    private IFileCheckService fileCheckService;

    @Autowired
    public void setFileCheckService(IFileCheckService fileCheckService) {
        this.fileCheckService = fileCheckService;
    }

    /**
     * 菜品维护:分页查询菜品信息列表
     *
     * @param currentPage
     * @param pageSize
     * @param menuitemReqVo
     * @return
     */
    @ApiOperation(value = "菜品维护:分页查询产品信息列表", response = MenuItemResVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = false, dataType = "int"),
    })
    @GetMapping(value = "/getMenuitemInfoList")
    public CommonResult<List<MenuItemResVo>> getMenuitemInfoList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, MenuitemReqVo menuitemReqVo) {
        if (menuitemReqVo != null) {
            if (StringUtil.isNotBlank(menuitemReqVo.getMenuItemClassifyCode())) {
                String[] arr = menuitemReqVo.getMenuItemClassifyCode().split(",");
                menuitemReqVo.setMenuItemClassify(arr);
            }
        }
        CommonResult<List<MenuItemResVo>> commonResult = menuitemService.queryMenuitemInfoList(currentPage, pageSize, menuitemReqVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }


    /**
     * 菜品维护:通过id查询菜品详情
     *
     * @param menuItemId
     * @return
     */
    @ApiOperation(value = "菜品维护:通过id查询菜品详情", response = ItemDetailsVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "菜品id", name = "menuItemId", required = true, dataType = "String"),
            @ApiImplicitParam(value = "菜品分类id", name = "dietCode", required = true, dataType = "String"),
            @ApiImplicitParam(value = "门店code", name = "restaurantCode", required = true, dataType = "String")
    })
    @GetMapping(value = "/getMenuitemDetails")
    public CommonResult<ItemDetailsVo> getMenuitemDetails(@RequestParam String menuItemId, @RequestParam String dietCode, @RequestParam String restaurantCode) {
        CommonResult<ItemDetailsVo> commonResult = menuitemService.queryMenuitemDetails(menuItemId, dietCode, restaurantCode);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }

    /**
     * 菜品维护:修改菜品信息
     *
     * @return
     */
    @ApiOperation(value = "菜品维护:修改菜品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "菜单id", name = "itemId", required = true, dataType = "String"),
            @ApiImplicitParam(value = "是否展示：0。不展示 1.展示", name = "status", required = true, dataType = "int"),
            @ApiImplicitParam(value = "菜品视频数组", name = "videoFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "菜品布置图片数组", name = "imageFile", required = false, dataType = "__file"),
            @ApiImplicitParam(value = "原上传的视频数组，更新须删除的id", name = "videoId", required = false),
            @ApiImplicitParam(value = "原上传的图片数组，更新须删除的id", name = "imageId", required = false),
            @ApiImplicitParam(value = "特色说明", name = "featureDescribe", required = true, dataType = "String"),
            @ApiImplicitParam(value = "会员价", name = "memberPrice", required = false, dataType = "String"),
            @ApiImplicitParam(value = "会员价开始时间", name = "memberPriceStart", required = false, dataType = "String"),
            @ApiImplicitParam(value = "会员价结束时间", name = "memberPriceEnd", required = false, dataType = "String"),
            @ApiImplicitParam(value = "是否会员产品  1 是 ； 0 否", name = "memberProType", required = true, dataType = "int"),
            @ApiImplicitParam(value = "门店code", name = "restaurantCode", required = true, dataType = "String")
    })
    @PostMapping(value = "/updateMenuItemInfo")
    public CommonResult updateMenuItemInfo(MultipartFile[] imageFile, MultipartFile[] videoFile,
                                           Long[] imageId, Long[] videoId, @RequestParam String itemId, @RequestParam int status,
                                           @RequestParam String featureDescribe, @RequestParam(required = false) String memberPrice, @RequestParam(required = false) String memberPriceStart,
                                           @RequestParam(required = false) String memberPriceEnd, @RequestParam(required = false) Integer memberProType, @RequestParam String restaurantCode) throws ParseException {
        CommonResult checkResult = fileCheckService.fileCheck(imageFile, videoFile);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UpdateMenuItemVo updateMenuItemVo = new UpdateMenuItemVo();
        if (null != memberProType && Constants.ONE.equals(memberProType)) {
            if (StringUtil.isBlank(memberPriceStart) || StringUtil.isBlank(memberPriceEnd) || StringUtil.isBlank(memberPrice)) {
                return CommonResult.fail("会员专属菜品需要填写会员相关属性");
            } else {
                Date memberPriceStartTime = simpleDateFormat.parse(memberPriceStart);
                Date memberPriceEndTime = simpleDateFormat.parse(memberPriceEnd);
                updateMenuItemVo.setMemPriceEnd(memberPriceEndTime);
                updateMenuItemVo.setMemPriceStart(memberPriceStartTime);
                updateMenuItemVo.setMemberPrice(Arith.toBigDecimal(memberPrice));
            }
        }
        updateMenuItemVo.setFeatureDescribe(featureDescribe);
        updateMenuItemVo.setImageFile(imageFile);
        updateMenuItemVo.setImageId(imageId);
        updateMenuItemVo.setItemId(itemId);
        updateMenuItemVo.setVideoFile(videoFile);
        updateMenuItemVo.setVideoId(videoId);
        updateMenuItemVo.setStatus(status);
        updateMenuItemVo.setMemberProType(memberProType);
        updateMenuItemVo.setRestaurantCode(restaurantCode);
        CommonResult commonResult = menuitemService.updateMenuItemInfo(updateMenuItemVo);
        if (commonResult == null) {
            CommonResult.fail();
        }
        return commonResult;
    }


}
