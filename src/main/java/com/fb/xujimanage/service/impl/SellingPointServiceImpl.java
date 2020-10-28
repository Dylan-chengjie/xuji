package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.*;
import com.fb.xujimanage.entity.*;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.ISellingPointService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 11:05
 * @description:就餐信息维护模块->服务特色维护接口实现类
 */
@Service
@Log4j
public class SellingPointServiceImpl implements ISellingPointService {

    @Value("${img.server.path}")
    private String imgServerPath;
    @Value("${video.server.path}")
    private String videoServerPath;

    private DataDictValueDao dataDictValueDao;

    private FileService fileService;

    private ImageDao imageDao;

    private VideoDao videoDao;

    private ServiceDao serviceDao;

    private ServiceImgRelateDao serviceImgRelateDao;

    private ServiceVideoRelateDao serviceVideoRelateDao;

    private RestaurantServiceRelateDao restaurantServiceRelateDao;

    @Autowired
    private void setRestaurantServiceRelateDao(RestaurantServiceRelateDao restaurantServiceRelateDao) {
        this.restaurantServiceRelateDao = restaurantServiceRelateDao;
    }

    @Autowired
    private void setServiceImgRelateDao(ServiceImgRelateDao serviceImgRelateDao) {
        this.serviceImgRelateDao = serviceImgRelateDao;
    }

    @Autowired
    private void setServiceVideoRelateDao(ServiceVideoRelateDao serviceVideoRelateDao) {
        this.serviceVideoRelateDao = serviceVideoRelateDao;
    }

    @Autowired
    private void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    private void setVideoDao(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setDataDictValueService(DataDictValueDao dataDictValueDao) {
        this.dataDictValueDao = dataDictValueDao;
    }

    @Autowired
    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }


    /**
     * 分页查询服务特色信息
     *
     * @param currentPage：当前页
     * @param pageSize：每页显示的数量
     * @return
     */
    @Override
    public CommonResult<List<SellingPointResVo>> querySellingPointList(Integer currentPage, Integer pageSize, String sellingPointName) {
        List<SellingPointResVo> list = new LinkedList<>();
        List<FeatureService> serviceList = serviceDao.loadServiceInfo(sellingPointName, (currentPage - 1) * pageSize, pageSize);
        int serviceTotal = serviceDao.getServiceTotal(sellingPointName);
        if (serviceList == null) {
            log.error("serviceList is null");
            return CommonResult.fail();
        }
        if (serviceList.isEmpty()) {
            return CommonResult.ok(new PageBean(currentPage, pageSize, serviceTotal, list));
        }
        for (FeatureService service : serviceList) {
            SellingPointResVo sellingPointResVo = new SellingPointResVo();
            sellingPointResVo.setSceneName(service.getSceneDicvalueId());
            sellingPointResVo.setId(service.getId());
            sellingPointResVo.setName(service.getName());
            sellingPointResVo.setServiceExplain(service.getServiceExplain());
            List<Restaurant> restaurantList = service.getRestaurantList();
            if (!CollectionUtils.isEmpty(restaurantList)) {
                List<RestaurantInfoResVo> restaurantInfoResVoList = new LinkedList<>();
                for (Restaurant restaurant : restaurantList) {
                    RestaurantInfoResVo restaurantInfoResVo = new RestaurantInfoResVo();
                    restaurantInfoResVo.setRestaurantName(restaurant.getRestaurantName());
                    restaurantInfoResVo.setCity(restaurant.getCity());
                    restaurantInfoResVo.setArea(restaurant.getArea());
                    restaurantInfoResVo.setId(restaurant.getId());
                    restaurantInfoResVo.setAddress(restaurant.getAddress());
                    restaurantInfoResVoList.add(restaurantInfoResVo);
                }
                sellingPointResVo.setRestaurantInfoResVoList(restaurantInfoResVoList);
            }
            List<Video> videoList = service.getVideoList();
            if (!CollectionUtils.isEmpty(videoList)) {
                List<VideoResVo> videoResVoList = new LinkedList<>();
                for (Video video : videoList) {
                    VideoResVo videoResVo = new VideoResVo();
                    videoResVo.setAddress(video.getAddress());
                    videoResVo.setId(video.getId());
                    videoResVoList.add(videoResVo);
                }
                sellingPointResVo.setVideoResVoList(videoResVoList);
            }
            List<Image> imageList = service.getImageList();
            if (!CollectionUtils.isEmpty(imageList)) {
                List<ImageResVo> imageResVoList = new LinkedList<>();
                for (Image image : imageList) {
                    ImageResVo imageResVo = new ImageResVo();
                    imageResVo.setAddress(image.getAddress());
                    imageResVo.setId(image.getId());
                    imageResVoList.add(imageResVo);
                }
                sellingPointResVo.setImageResVoList(imageResVoList);
            }
            list.add(sellingPointResVo);
        }
        PageBean pageBean = new PageBean(currentPage, pageSize, serviceTotal, list);
        return CommonResult.ok(pageBean);
    }

    /**
     * 修改服务特色信息
     *
     * @param sellingPointVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateSellingPoint(SellingPointVo sellingPointVo) {
        FeatureService soleFeatureServices = serviceDao.selectFeatureService(sellingPointVo.getRestaurantId(), sellingPointVo.getSceneId());
        if (soleFeatureServices != null) {
            if (sellingPointVo.getId() != soleFeatureServices.getId()) {
                return CommonResult.fail("修改失败，此门店对应的服务特色已存在！");
            }
        }
        String userNum = TokenUtils.getUserNum();
        FeatureService featureService = serviceDao.load(sellingPointVo.getId());
        if (featureService == null) {
            log.error("修改服务特色信息失败:更新数据不存在");
            return CommonResult.fail("修改服务特色信息失败:更新数据不存在");
        }
        featureService.setName(sellingPointVo.getName());
        featureService.setUpdateBy(userNum);
        featureService.setUpdateTime(new Date());
        featureService.setSceneDicvalueId(sellingPointVo.getSceneId());
        featureService.setServiceExplain(sellingPointVo.getServiceExplain());
        Integer update = serviceDao.update(featureService);
        if (update == null) {
            log.error("修改服务特色信息失败:数据更新异常");
            return CommonResult.fail("修改服务特色信息失败:数据更新异常");
        }
        long restaurantId = sellingPointVo.getRestaurantId();

        Integer deleteId = restaurantServiceRelateDao.deleteRestaurantServiceRelate(sellingPointVo.getId());
        if (deleteId == null) {
            log.error("修改服务特色信息失败:删除门店信息异常");
            return CommonResult.fail("修改服务特色信息失败:删除门店信息异常");
        }
        List<RestaurantServiceRelate> restaurantServiceRelates = new LinkedList<>();
        RestaurantServiceRelate restaurantMenuItemRelate = new RestaurantServiceRelate();
        restaurantMenuItemRelate.setId(IdUtil.getUUID());
        restaurantMenuItemRelate.setServiceId(featureService.getId());
        restaurantMenuItemRelate.setRestaurantId(restaurantId);
        restaurantServiceRelates.add(restaurantMenuItemRelate);

        Integer insertId = restaurantServiceRelateDao.insertRestaurantServiceRelate(restaurantServiceRelates);
        if (insertId == null) {
            log.error("修改服务特色信息失败：插入门店信息异常");
            return CommonResult.fail("修改服务特色信息失败：插入门店信息异常");
        }

        Long featureServiceId = featureService.getId();
        MultipartFile[] imageFile = sellingPointVo.getImageFile();
        Long[] imageIds = sellingPointVo.getImageId();

        if (!this.updateServiceImage(imageFile, featureServiceId, userNum, imageIds)) {
            return CommonResult.fail();
        }

        MultipartFile[] videoFile = sellingPointVo.getVideoFile();
        Long[] videoIds = sellingPointVo.getVideoId();

        if (!this.updateServiceVideo(videoFile, featureServiceId, userNum, videoIds)) {
            return CommonResult.fail();
        }

        return CommonResult.ok();
    }


    /**
     * 新增服务特色信息
     *
     * @param sellingPointVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult addSellingPoint(SellingPointVo sellingPointVo) {
        FeatureService soleFeatureServices = serviceDao.selectFeatureService(sellingPointVo.getRestaurantId(), sellingPointVo.getSceneId());
        if (soleFeatureServices != null) {
            return CommonResult.fail("新增服务特色失败,同一门店不能对应多个相同服务特色");
        }
        String userNum = TokenUtils.getUserNum();
        FeatureService featureService = new FeatureService();
        long featureServiceId = IdUtil.getUUID();
        featureService.setId(featureServiceId);
        featureService.setDelFlag(0);
        featureService.setUpdateTime(new Date());
        featureService.setCreateTime(new Date());
        featureService.setUpdateBy(userNum);
        featureService.setCreateBy(userNum);
        featureService.setServiceExplain(sellingPointVo.getServiceExplain());
        featureService.setSceneDicvalueId(sellingPointVo.getSceneId());
        featureService.setName(sellingPointVo.getName());
        Integer featureServiceInsert = serviceDao.insert(featureService);
        if (featureServiceInsert == null) {
            log.error("新增服务特色信息失败：服务特色信息插入失败");
            return CommonResult.fail("新增服务特色信息失败：服务特色信息插入失败");
        }
        long restaurantId = sellingPointVo.getRestaurantId();
        List<RestaurantServiceRelate> restaurantServiceRelates = new LinkedList<>();
        RestaurantServiceRelate restaurantMenuItemRelate = new RestaurantServiceRelate();
        restaurantMenuItemRelate.setId(IdUtil.getUUID());
        restaurantMenuItemRelate.setServiceId(featureService.getId());
        restaurantMenuItemRelate.setRestaurantId(restaurantId);
        restaurantServiceRelates.add(restaurantMenuItemRelate);
        Integer insertId = restaurantServiceRelateDao.insertRestaurantServiceRelate(restaurantServiceRelates);
        if (insertId == null) {
            log.error("修改服务特色信息失败：插入门店信息异常");
            return CommonResult.fail("修改服务特色信息失败：插入门店信息异常");
        }
        MultipartFile[] imageFile = sellingPointVo.getImageFile();
        if (!this.uploadServiceImage(imageFile, featureServiceId, userNum)) {
            return CommonResult.fail();
        }
        MultipartFile[] videoFile = sellingPointVo.getVideoFile();
        if (!this.uploadServiceVideo(videoFile, featureServiceId, userNum)) {
            return CommonResult.fail();
        }

        return CommonResult.ok();

    }


    /**
     * 上传图片
     *
     * @param imageFile
     * @param serviceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadServiceImage(MultipartFile[] imageFile, long serviceId, String userNum) {
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
                    log.error("修改服务特色信息失败:图片插入失败");
                    throw new NullPointerException("修改服务特色信息失败:图片插入失败");
                }
                ServiceImgRelate serviceImgRelate = new ServiceImgRelate();
                serviceImgRelate.setId(IdUtil.getUUID());
                serviceImgRelate.setImgId(imageId);
                serviceImgRelate.setServiceId(serviceId);
                Integer menuitemImgRelateInsertId = serviceImgRelateDao.insert(serviceImgRelate);
                if (menuitemImgRelateInsertId == null) {
                    log.error("修改服务特色信息失败:图片删除失败");
                    throw new NullPointerException();
                }
            }
        }
        return true;
    }

    /**
     * 上传视频
     *
     * @param videoFile
     * @param serviceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadServiceVideo(MultipartFile[] videoFile, long serviceId, String userNum) {
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
                    log.error("修改服务特色信息失败:视频插入失败");
                    throw new NullPointerException("修改服务特色信息失败:视频插入失败");
                }
                ServiceVideoRelate serviceVideoRelate = new ServiceVideoRelate();
                serviceVideoRelate.setId(IdUtil.getUUID());
                serviceVideoRelate.setServiceId(serviceId);
                serviceVideoRelate.setVideoId(videoId);
                Integer insertServiceVideoRelate = serviceVideoRelateDao.insert(serviceVideoRelate);
                if (insertServiceVideoRelate == null) {
                    log.error("修改服务特色信息失败:视频插入失败");
                    throw new NullPointerException("修改服务特色信息失败:视频插入失败");
                }
            }
        }
        return true;
    }

    /**
     * 服务特色图片更新
     *
     * @param imageFile
     * @param serviceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateServiceImage(MultipartFile[] imageFile, Long serviceId, String userNum, Long[] imageIds) {
        if (imageIds != null && imageIds.length > 0) {
            if (serviceImgRelateDao.deleteServiceImgRelate(serviceId, imageIds) < 1 || imageDao.batchDeleteImage(Arrays.asList(imageIds)) < 1) {
                CommonResult.throwVerifyException("修改服务特色信息失败:删除图片失败");
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
                    log.error("修改服务特色信息失败:图片插入失败");
                    throw new NullPointerException("修改服务特色信息失败:图片插入失败");
                }
                ServiceImgRelate serviceImgRelate = new ServiceImgRelate();
                serviceImgRelate.setId(IdUtil.getUUID());
                serviceImgRelate.setImgId(imageId);
                serviceImgRelate.setServiceId(serviceId);
                Integer menuitemImgRelateInsertId = serviceImgRelateDao.insert(serviceImgRelate);
                if (menuitemImgRelateInsertId == null) {
                    log.error("修改服务特色信息失败:图片删除失败");
                    throw new NullPointerException("修改服务特色信息失败:图片删除失败");
                }
            }
        }

        return true;
    }

    /**
     * 服务特色视频上传
     *
     * @param videoFile
     * @param serviceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateServiceVideo(MultipartFile[] videoFile, Long serviceId, String userNum, Long[] videoIds) {
        if (videoIds != null && videoIds.length > 0) {
            if (serviceVideoRelateDao.deleteServiceVideoRelate(serviceId, videoIds) < 1 || videoDao.batchDeleteByIds(Arrays.asList(videoIds)) < 1) {
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
                    log.error("修改服务特色信息失败:视频插入失败");
                    throw new NullPointerException("修改服务特色信息失败:视频插入失败");
                }
                ServiceVideoRelate serviceVideoRelate = new ServiceVideoRelate();
                serviceVideoRelate.setId(IdUtil.getUUID());
                serviceVideoRelate.setServiceId(serviceId);
                serviceVideoRelate.setVideoId(videoId);
                Integer insertServiceVideoRelate = serviceVideoRelateDao.insert(serviceVideoRelate);
                if (insertServiceVideoRelate == null) {
                    log.error("修改服务特色信息失败:视频插入失败");
                    throw new NullPointerException("修改服务特色信息失败:视频插入失败");
                }
            }
        }
        return true;
    }

    /**
     * 根据id删除服务特色信息
     *
     * @param serviceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteSellingPointById(long serviceId) {
        String userNum = TokenUtils.getUserNum();
        FeatureService featureService = serviceDao.load(serviceId);
        if (featureService == null) {
            log.error("删除服务特色信息:数据不存在");
            return CommonResult.fail("删除服务特色信息:数据不存在");
        }
        featureService.setUpdateTime(new Date());
        featureService.setUpdateBy(userNum);
        featureService.setDelFlag(1);
        featureService.setId(serviceId);
        Integer updateId = serviceDao.update(featureService);
        if (updateId == null) {
            return CommonResult.fail();
        }
        return updateId > 0 ? CommonResult.ok() : CommonResult.fail();
    }
}
