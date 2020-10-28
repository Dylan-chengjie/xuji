package com.fb.xujimanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.common.constant.MenuItemConstants;
import com.fb.xujimanage.dao.*;
import com.fb.xujimanage.entity.*;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.IMenuitemService;
import com.fb.xujimanage.util.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 11:13
 * @description:就餐信息维护模块->菜品维护接口实现类
 */
@Log4j
@Service
public class MenuitemServiceImpl implements IMenuitemService {

    @Value("${img.server.path}")
    private String imgServerPath;
    @Value("${video.server.path}")
    private String videoServerPath;

    private MenuItemDao menuItemDao;

    private FileService fileService;

    private RestaurantMenuItemRelateDao restaurantMenuItemRelateDao;

    private MenuitemImgRelateDao menuitemImgRelateDao;

    private ImageDao imageDao;

    private VideoDao videoDao;

    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Autowired
    private void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    private void setVideoDao(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    private MenuitemVideoRelateDao menuitemVideoRelateDao;

    @Autowired
    private void setMenuitemImgRelateDao(MenuitemImgRelateDao menuitemImgRelateDao) {
        this.menuitemImgRelateDao = menuitemImgRelateDao;
    }

    @Autowired
    private void setMenuitemVideoRelateDao(MenuitemVideoRelateDao menuitemVideoRelateDao) {
        this.menuitemVideoRelateDao = menuitemVideoRelateDao;
    }

    @Autowired
    public void setRestaurantMenuItemRelateDao(RestaurantMenuItemRelateDao restaurantMenuItemRelateDao) {
        this.restaurantMenuItemRelateDao = restaurantMenuItemRelateDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setMenuItemDao(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    /**
     * 分页查询菜品信息列表
     *
     * @param currentPage：当前页
     * @param pageSize：每页显示的数量
     * @param menuitemReqVo
     * @return
     */
    @Override
    public CommonResult<List<MenuItemResVo>> queryMenuitemInfoList(Integer currentPage, Integer pageSize, MenuitemReqVo menuitemReqVo) {
        List<MenuItem> menuItems = menuItemDao.loadMenUItem(menuitemReqVo, (currentPage - 1) * pageSize, pageSize);
        if (menuItems == null) {
            log.error("查询菜品信息失败:menuItems is null");
            return CommonResult.fail("查询菜品信息失败");
        }
        int menuItemTotal;
        if (isBoolean(menuitemReqVo)) {
            menuItemTotal = new Integer(redisUtil.get(MenuItemConstants.MENU_ITEM_TOTAL).toString());
        } else {
            menuItemTotal = menuItemDao.getMenuItemTotal(menuitemReqVo);
        }
        if (menuItems.isEmpty()) {
            return CommonResult.ok(new PageBean(currentPage, pageSize, menuItemTotal, null));
        }
        List<MenuItemResVo> menuItemResVos = menuItems.stream().map(menuItem -> {
            MenuItemResVo menuItemResVo = new MenuItemResVo();
            menuItemResVo.setName(menuItem.getName());
            menuItemResVo.setUnit(menuItem.getUnit());
            menuItemResVo.setMemberPrice(menuItem.getMemberPriceAlter());
            menuItemResVo.setPrice(menuItem.getPrice());
            menuItemResVo.setId(menuItem.getId());
            menuItemResVo.setFeatureDescribe(menuItem.getFeatureDescribe());
            menuItemResVo.setStatus(menuItem.getIsShow());
            menuItemResVo.setClassifyName(menuItem.getClassifyName());
            menuItemResVo.setRestaurantCode(menuItem.getRestaurantCode());
            menuItemResVo.setDietCode(menuItem.getSmallTypeCode());
            menuItemResVo.setMemPriceStart(menuItem.getMemPriceStart());
            menuItemResVo.setMemPriceEnd(menuItem.getMemPriceEnd());
            return menuItemResVo;
        }).collect(Collectors.toList());
        return CommonResult.ok(new PageBean(currentPage, pageSize, menuItemTotal, menuItemResVos));
    }

    /**
     * 判断参数是否为空
     *
     * @param menuitemReqVo
     * @return
     */
    private Boolean isBoolean(MenuitemReqVo menuitemReqVo) {
        return (null == menuitemReqVo.getMenuItemClassify() || menuitemReqVo.getMenuItemClassify().length == 0) && StringUtil.isBlank(menuitemReqVo.getMenuItemClassifyCode()) &&
                StringUtil.isBlank(menuitemReqVo.getMenuitemName()) && StringUtil.isBlank(menuitemReqVo.getRestaurantCode()) &&
                StringUtil.isBlank(menuitemReqVo.getCityCode()) && StringUtil.isBlank(menuitemReqVo.getMemPriceStart()) &&
                StringUtil.isBlank(menuitemReqVo.getMemPriceEnd());
    }

    /**
     * 通过菜品id查询菜品详情
     *
     * @param menuItemId
     * @return
     */
    @Override
    public CommonResult<ItemDetailsVo> queryMenuitemDetails(String menuItemId, String dietCode, String restaurantCode) {
        MenuItem menuItem = menuItemDao.loadMenUItemById(menuItemId, dietCode, restaurantCode);
        log.info("menuItem"+JSONObject.toJSONString(menuItem));
        if (menuItem == null) {
            log.error("查询菜品详情失败:menuItems is null");
            return CommonResult.fail();
        }
        ItemDetailsVo itemDetailsVo = new ItemDetailsVo();
        itemDetailsVo.setClassifyName(menuItem.getClassifyName());
        itemDetailsVo.setFeatureDescribe(menuItem.getFeatureDescribe());
        itemDetailsVo.setId(menuItem.getId());
        itemDetailsVo.setType(menuItem.getType());
        itemDetailsVo.setUnit(menuItem.getUnit());
        itemDetailsVo.setRestaurantName(menuItem.getRestaurantName());
        itemDetailsVo.setPrice(menuItem.getMemberPriceAlter());
        itemDetailsVo.setMemPriceEnd(menuItem.getMemPriceEnd());
        itemDetailsVo.setMemPriceStart(menuItem.getMemPriceStart());
        itemDetailsVo.setMemberProType(menuItem.getMemberProType());
        itemDetailsVo.setPriceType(menuItem.getPriceType());
        itemDetailsVo.setStatus(menuItem.getIsShow());
        if (!CollectionUtils.isEmpty(menuItem.getImageList())) {
            List<ImageResVo> imageResVos = menuItem.getImageList().stream().map(image -> {
                ImageResVo imageResVo = new ImageResVo();
                imageResVo.setAddress(image.getAddress());
                imageResVo.setId(image.getId());
                return imageResVo;
            }).collect(Collectors.toList());
            itemDetailsVo.setImageResVos(imageResVos);
        }
        if (!CollectionUtils.isEmpty(menuItem.getVideoList())) {
            List<VideoResVo> videoResVos = menuItem.getVideoList().stream().map(video -> {
                VideoResVo videoResVo = new VideoResVo();
                videoResVo.setAddress(video.getAddress());
                videoResVo.setId(video.getId());
                return videoResVo;
            }).collect(Collectors.toList());
            itemDetailsVo.setVideoResVos(videoResVos);
        }
        SetMeal setMeal = menuItem.getSetMeal();
        if (setMeal != null) {
            SetMealVo setMealVo = new SetMealVo();
            setMealVo.setSetId(setMeal.getSetId());
            setMealVo.setSetName(setMeal.getSetName());
            List<SetMealDetail> setMealDetails = setMeal.getSetMealDetails();
            if (!CollectionUtils.isEmpty(setMealDetails)) {
                List<SetMealDetailVo> setMealDetailVos = setMealDetails.stream().map(setMealDetail -> {
                    SetMealDetailVo setMealDetailVo = new SetMealDetailVo();
                    setMealDetailVo.setDefaultSelect(setMealDetail.getDefaultSelect());
                    setMealDetailVo.setSetMealId(setMealDetail.getSetMealId());
                    setMealDetailVo.setDetailQuantity(setMealDetail.getDetailQuantity());
                    setMealDetailVo.setDetailType(setMealDetail.getDetailType());
                    MenuItem detailMenuItem = setMealDetail.getMenuItem();
                    if (detailMenuItem != null) {
                        ItemDetailsVo detailsItemVo = new ItemDetailsVo();
                        detailsItemVo.setName(detailMenuItem.getName());
                        detailsItemVo.setQuantity(detailMenuItem.getQuantity());
                        detailsItemVo.setDtPriceType(detailMenuItem.getDtPriceType());
                        setMealDetailVo.setItemDetailsVo(detailsItemVo);
                    }
                    return setMealDetailVo;
                }).collect(Collectors.toList());
                setMealVo.setDetailVos(setMealDetailVos);
            }
            itemDetailsVo.setSetMealVo(setMealVo);
        }
        return CommonResult.ok(itemDetailsVo);
    }


    /**
     * 更新图片
     *
     * @param imageFile
     * @param menuItemId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateImage(MultipartFile[] imageFile, String menuItemId, String userNum, Long[] imageIds) {
        if (imageIds != null && imageIds.length > 0) {
            if (menuitemImgRelateDao.deleteMenuItemImgRelate(menuItemId, imageIds) < 1 || imageDao.batchDeleteImage(Arrays.asList(imageIds)) < 1) {
                CommonResult.throwVerifyException("更新菜品信息失败:删除图片失败");
            }
        }
        if (imageFile == null || imageFile.length == 0) {
            return true;
        }
        for (MultipartFile multipartFile : imageFile) {
            CommonResult commonResult = fileService.fileUpload(multipartFile, imgServerPath);
            if (commonResult == null) {
                log.error("图片上传失败");
                throw new NullPointerException("图片上传失败");
            }
            if (commonResult.getStatus() == 200) {
                String imageUrl = commonResult.getData().toString();
                Image image = new Image();
                image.setAddress(imageUrl);
                long imageId = IdUtil.getUUID();
                image.setId(imageId);
                image.setCreateBy(userNum);
                image.setCreateTime(new Date());
                image.setDelFlag(0);
                image.setUpdateBy(userNum);
                image.setUpdateTime(new Date());
                image.setName(multipartFile.getName());
                Integer insertImageId = imageDao.insert(image);
                if (insertImageId == null) {
                    log.error("更新菜品信息失败:图片插入失败");
                    throw new NullPointerException("更新菜品信息失败:图片插入失败");
                }
                MenuitemImgRelate menuitemImgRelate = new MenuitemImgRelate();
                menuitemImgRelate.setId(IdUtil.getUUID());
                menuitemImgRelate.setImgId(imageId);
                menuitemImgRelate.setMenuItemId(menuItemId);
                Integer menuitemImgRelateInsertId = menuitemImgRelateDao.insert(menuitemImgRelate);
                log.error("更新菜品信息失败:图片插入失败");
                if (menuitemImgRelateInsertId == null) {
                    throw new NullPointerException("更新菜品信息失败:图片插入失败");
                }
            }
        }
        return true;
    }

    /**
     * 修改菜品信息
     *
     * @param updateMenuItemVo:菜品信息封装
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateMenuItemInfo(UpdateMenuItemVo updateMenuItemVo) {
        String userNum = TokenUtils.getUserNum();
        MenuItem menuItem = menuItemDao.getMenuItemByRestaurantCode(updateMenuItemVo.getItemId(), updateMenuItemVo.getRestaurantCode());
        if (menuItem == null) {
            log.error("修改菜品信息失败：菜品不存在");
            return CommonResult.fail("修改菜品信息失败：菜品不存在");
        }
        menuItem.setUpdateTime(new Date());
        menuItem.setUpdateBy(userNum);
        menuItem.setIsShow(updateMenuItemVo.getStatus());
        menuItem.setFeatureDescribe(updateMenuItemVo.getFeatureDescribe());
        menuItem.setId(updateMenuItemVo.getItemId());
        menuItem.setMemberPriceAlter(updateMenuItemVo.getMemberPrice());
        menuItem.setMemPriceEnd(updateMenuItemVo.getMemPriceEnd());
        menuItem.setMemPriceStart(updateMenuItemVo.getMemPriceStart());
        menuItem.setUpdateTime(new Date());
        menuItem.setUpdateBy(userNum);
        menuItem.setMemberProType(updateMenuItemVo.getMemberProType());
        Integer updateId = menuItemDao.update(menuItem);
        if (updateId == null) {
            log.error("更新菜品信息失败");
            return CommonResult.fail("更新菜品信息失败");
        }

        String menuItemId = menuItem.getId();
        MultipartFile[] imageFile = updateMenuItemVo.getImageFile();
        Long[] imageIds = updateMenuItemVo.getImageId();
        if (!this.updateImage(imageFile, menuItemId, userNum, imageIds)) {
            return CommonResult.fail();
        }
        MultipartFile[] videoFile = updateMenuItemVo.getVideoFile();
        Long[] videoIds = updateMenuItemVo.getVideoId();
        if (!this.updateVideo(videoFile, menuItemId, userNum, videoIds)) {
            return CommonResult.fail();
        }
        return CommonResult.ok();
    }

    /**
     * 更新视频
     *
     * @param videoFile
     * @param menuItemId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateVideo(MultipartFile[] videoFile, String menuItemId, String userNum, Long[] videoIds) {
        if (videoIds != null && videoIds.length > 0) {
            if (menuitemVideoRelateDao.deleteMenuitemVideoRelate(menuItemId, videoIds) < 1 || videoDao.batchDeleteByIds(Arrays.asList(videoIds)) < 1) {
                CommonResult.throwVerifyException("修改服务特色信息失败:删除视频失败");
            }
        }
        if (videoFile == null || videoFile.length == 0) {
            return true;
        }
        for (MultipartFile multipartFile : videoFile) {
            CommonResult commonResult = fileService.fileUpload(multipartFile, videoServerPath);
            if (commonResult == null) {
                log.error("图片上传失败");
                throw new NullPointerException("图片上传失败");
            }
            if (commonResult.getStatus() == 200) {
                String videoUrl = commonResult.getData().toString();
                Video video = new Video();
                video.setAddress(videoUrl);
                long videoId = IdUtil.getUUID();
                video.setId(videoId);
                video.setCreateBy(userNum);
                video.setCreateTime(new Date());
                video.setDelFlag(0);
                video.setName(multipartFile.getName());
                video.setUpdateBy(userNum);
                video.setUpdateTime(new Date());
                Integer insertVideoId = videoDao.insert(video);
                if (insertVideoId == null) {
                    log.error("更新菜品信息失败:视频插入失败");
                    throw new NullPointerException("更新菜品信息失败:视频插入失败");
                }
                MenuitemVideoRelate menuitemVideoRelate = new MenuitemVideoRelate();
                menuitemVideoRelate.setId(IdUtil.getUUID());
                menuitemVideoRelate.setMenuItemId(menuItemId);
                menuitemVideoRelate.setVideoId(videoId);
                Integer insertMenuitemVideoRelateId = menuitemVideoRelateDao.insert(menuitemVideoRelate);
                if (insertMenuitemVideoRelateId == null) {
                    log.error("更新菜品信息失败:视频插入失败");
                    throw new NullPointerException("更新菜品信息失败:视频插入失败");
                }
            }
        }
        return true;
    }


}

