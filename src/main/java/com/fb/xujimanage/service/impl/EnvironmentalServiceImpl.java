package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.*;
import com.fb.xujimanage.entity.*;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.IEnvironmentalService;
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

import java.util.*;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 19:44
 * @description:就餐信息模块->环境布置维护接口实现类
 */
@Log4j
@Service
public class EnvironmentalServiceImpl implements IEnvironmentalService {

    @Value("${img.server.path}")
    private String imgServerPath;
    @Value("${video.server.path}")
    private String videoServerPath;

    private EnvironmentDao environmentDao;

    private DataDictValueDao dataDictValueDao;

    private EnvironmentImgRelateDao environmentImgRelateDao;

    private FileService fileService;

    private ImageDao imageDao;

    private VideoDao videoDao;

    private EnvironmentVideoRelateDao environmentVideoRelateDao;

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
    private void setEnvironmentImgRelateDao(EnvironmentImgRelateDao environmentImgRelateDao) {
        this.environmentImgRelateDao = environmentImgRelateDao;
    }

    @Autowired
    private void setEnvironmentVideoRelateDao(EnvironmentVideoRelateDao environmentVideoRelateDao) {
        this.environmentVideoRelateDao = environmentVideoRelateDao;
    }

    private RestaurantEnvironmentRelateDao restaurantEnvironmentRelateDao;

    @Autowired
    private void setRestaurantEnvironmentRelateDao(RestaurantEnvironmentRelateDao restaurantEnvironmentRelateDao) {
        this.restaurantEnvironmentRelateDao = restaurantEnvironmentRelateDao;
    }

    @Autowired
    public void setDataDictValueService(DataDictValueDao dataDictValueDao) {
        this.dataDictValueDao = dataDictValueDao;
    }

    @Autowired
    public void setEnvironmentDao(EnvironmentDao environmentDao) {
        this.environmentDao = environmentDao;
    }

    /**
     * 分页查询环境布置信息
     *
     * @param currentPage:当前页码
     * @param pageSize:显示数量
     * @return
     */
    @Override
    public CommonResult<List<EnvironmentalResVo>> queryEnvironmentalInfo(Integer currentPage, Integer pageSize, String environmentalName) {
        List<EnvironmentalResVo> list = new ArrayList<>();
        List<Environment> environments = environmentDao.selectEnvironmentInfo(environmentalName, (currentPage - 1) * pageSize, pageSize);
        int total = environmentDao.getEnvironmentTotal(environmentalName);

        if (CollectionUtils.isEmpty(environments)) {
            return CommonResult.ok(new PageBean(currentPage, pageSize, total, list));
        }
        for (Environment environment : environments) {
            EnvironmentalResVo environmentalResVo = new EnvironmentalResVo();
            environmentalResVo.setSceneName(environment.getSceneDicvalueId());
            environmentalResVo.setId(environment.getId());
            environmentalResVo.setName(environment.getName());
            List<Restaurant> restaurantList = environment.getRestaurantList();
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
                environmentalResVo.setRestaurantInfoResVoList(restaurantInfoResVoList);
            }
            List<Video> videoList = environment.getVideoList();
            if (!CollectionUtils.isEmpty(videoList)) {
                List<VideoResVo> videoResVoList = new LinkedList<>();
                for (Video video : videoList) {
                    VideoResVo videoResVo = new VideoResVo();
                    videoResVo.setAddress(video.getAddress());
                    videoResVo.setId(video.getId());
                    videoResVoList.add(videoResVo);
                }
                environmentalResVo.setVideoResVoList(videoResVoList);
            }
            List<Image> imageList = environment.getImageList();
            if (!CollectionUtils.isEmpty(imageList)) {
                List<ImageResVo> imageResVoList = new LinkedList<>();
                for (Image image : imageList) {
                    ImageResVo imageResVo = new ImageResVo();
                    imageResVo.setAddress(image.getAddress());
                    imageResVo.setId(image.getId());
                    imageResVoList.add(imageResVo);
                }
                environmentalResVo.setImageResVoList(imageResVoList);
            }
            list.add(environmentalResVo);
        }
        PageBean pageBean = new PageBean(currentPage, pageSize, total, list);
        return CommonResult.ok(pageBean);
    }

    /**
     * 修改环境布置信息
     *
     * @param environmentalVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateEnvironmentalInfo(EnvironmentalVo environmentalVo) {
        Environment soleEnvironment = environmentDao.selectEnvironment(environmentalVo.getRestaurantId(), environmentalVo.getSceneId());
        if (soleEnvironment != null) {
            if (environmentalVo.getId() != soleEnvironment.getId()) {
                return CommonResult.fail("修改失败，同一门店环境配置不能相同！");
            }
        }
        String userNum = TokenUtils.getUserNum();
        Environment environment = environmentDao.load(environmentalVo.getId());
        if (environment == null) {
            log.error("修改环境布置信息失败：环境配置数据不存在");
            return CommonResult.fail("修改环境布置信息失败：环境配置数据不存在");
        }
        environment.setName(environmentalVo.getName());
        environment.setUpdateBy(userNum);
        environment.setUpdateTime(new Date());
        environment.setSceneDicvalueId(environmentalVo.getSceneId());
        Integer updateId = environmentDao.update(environment);
        if (updateId == null) {
            log.error("修改环境布置信息失败:更新异常");
            return CommonResult.fail("修改环境布置信息失败:更新异常");
        }
        long restaurantId = environmentalVo.getRestaurantId();


        Integer deleteId = restaurantEnvironmentRelateDao.deleteByEnvironmentId(environmentalVo.getId());
        if (deleteId == null) {
            log.error("修改环境布置信息失败:删除门店信息异常");
            return CommonResult.fail("修改环境布置信息失败:删除门店信息异常");
        }
        List<RestaurantEnvironmentRelate> restaurantEnvironmentRelates = new LinkedList<>();

        RestaurantEnvironmentRelate restaurantMenuItemRelate = new RestaurantEnvironmentRelate();
        restaurantMenuItemRelate.setId(IdUtil.getUUID());
        restaurantMenuItemRelate.setEnvironmentId(environment.getId());
        restaurantMenuItemRelate.setRestaurantId(restaurantId);
        restaurantEnvironmentRelates.add(restaurantMenuItemRelate);

        Integer insertId = restaurantEnvironmentRelateDao.insertRestaurantEnvironmentRelateList(restaurantEnvironmentRelates);
        if (insertId == null) {
            log.error("修改环境布置信息失败：插入门店信息异常");
            return CommonResult.fail("修改环境布置信息失败：插入门店信息异常");
        }

        Long environmentId = environment.getId();
        MultipartFile[] imageFile = environmentalVo.getImageFile();
        Long[] imageIds = environmentalVo.getImageId();

        if (!this.updateEnvironmentalImage(imageFile, environmentId, userNum, imageIds)) {
            return CommonResult.fail();
        }

        MultipartFile[] videoFile = environmentalVo.getVideoFile();
        Long[] videoIds = environmentalVo.getVideoId();
        if (!this.updateEnvironmentalVideo(videoFile, environmentId, userNum, videoIds)) {
            return CommonResult.fail();
        }

        return CommonResult.ok("修改环境布置信息成功");
    }

    /**
     * 新增环境布置
     *
     * @param environmentalVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult addEnvironmentalInfo(EnvironmentalVo environmentalVo) {
        Environment soleEnvironment = environmentDao.selectEnvironment(environmentalVo.getRestaurantId(), environmentalVo.getSceneId());
        if (soleEnvironment != null) {
            return CommonResult.fail("新增环境配置失败,同一门店不能对应多个相同的环境配置");
        }
        String userNum = TokenUtils.getUserNum();
        Environment environment = new Environment();
        long environmentId = IdUtil.getUUID();
        environment.setId(environmentId);
        environment.setDelFlag(0);
        environment.setUpdateTime(new Date());
        environment.setCreateTime(new Date());
        environment.setSceneDicvalueId(environmentalVo.getSceneId());
        environment.setUpdateBy(userNum);
        environment.setCreateBy(userNum);
        environment.setName(environmentalVo.getName());
        Integer environmentInsertId = environmentDao.insert(environment);
        if (environmentInsertId == null) {
            log.error("新增环境布置信息失败：插入环境布置信息异常");
            return CommonResult.fail("新增环境布置信息失败：插入环境布置信息异常");
        }
        long restaurantId = environmentalVo.getRestaurantId();
        List<RestaurantEnvironmentRelate> environmentRelates = new ArrayList<>();
        RestaurantEnvironmentRelate environmentRelate = new RestaurantEnvironmentRelate();
        environmentRelate.setRestaurantId(restaurantId);
        environmentRelate.setEnvironmentId(environmentId);
        environmentRelate.setId(IdUtil.getUUID());
        environmentRelates.add(environmentRelate);
        Integer insertId = restaurantEnvironmentRelateDao.insertRestaurantEnvironmentRelateList(environmentRelates);
        if (insertId == null) {
            log.error("新增环境布置信息失败：插入门店信息异常");
            return CommonResult.fail("新增环境布置信息失败：插入门店信息异常");
        }
        MultipartFile[] imageFile = environmentalVo.getImageFile();

        if (!this.uploadEnvironmentalImage(imageFile, environmentId, userNum)) {
            return CommonResult.fail();
        }
        MultipartFile[] videoFile = environmentalVo.getVideoFile();

        if (!this.uploadEnvironmentalVideo(videoFile, environmentId, userNum)) {
            return CommonResult.fail();
        }
        return CommonResult.ok("新增环境布置成功");
    }

    /**
     * 图片上传
     *
     * @param imageFile
     * @param environmentId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadEnvironmentalImage(MultipartFile[] imageFile, long environmentId, String userNum) {
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
                    log.error("新增环境布置信息失败:图片插入失败");
                    throw new NullPointerException("新增环境布置信息失败:图片插入失败");
                }
                EnvironmentImgRelate environmentImgRelate = new EnvironmentImgRelate();
                environmentImgRelate.setId(IdUtil.getUUID());
                environmentImgRelate.setImgId(imageId);
                environmentImgRelate.setEnvironmentId(environmentId);
                Integer environmentImgRelateInsertId = environmentImgRelateDao.insert(environmentImgRelate);
                log.error("新增环境布置信息失败:图片删除失败");
                if (environmentImgRelateInsertId == null) {
                    throw new NullPointerException("新增环境布置信息失败:图片删除失败");
                }
            }
        }
        return true;
    }

    /**
     * 视频上传
     *
     * @param videoFile
     * @param environmentId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadEnvironmentalVideo(MultipartFile[] videoFile, long environmentId, String userNum) {
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
                    log.error("修改环境布置信息失败:视频插入失败");
                    throw new NullPointerException("修改环境布置信息失败:视频插入失败");
                }
                EnvironmentVideoRelate environmentVideoRelate = new EnvironmentVideoRelate();
                environmentVideoRelate.setId(IdUtil.getUUID());
                environmentVideoRelate.setEnvironmentId(environmentId);
                environmentVideoRelate.setVideoId(videoId);
                Integer insertMenuitemVideoRelateId = environmentVideoRelateDao.insert(environmentVideoRelate);
                if (insertMenuitemVideoRelateId == null) {
                    log.error("更新菜品信息失败:视频插入失败");
                    throw new NullPointerException("更新菜品信息失败:视频插入失败");
                }
            }
        }
        return true;
    }

    /**
     * 更新环境配置图片
     *
     * @param imageFile
     * @param environmentId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateEnvironmentalImage(MultipartFile[] imageFile, long environmentId, String userNum, Long[] imageIds) {
        if (imageIds != null && imageIds.length > 0) {
            if (environmentImgRelateDao.deleteEnvironmentImgRelateById(environmentId, imageIds) < 1 || imageDao.batchDeleteImage(Arrays.asList(imageIds)) < 1) {
                CommonResult.throwVerifyException("修改环境配置失败:删除图片失败");
            }
        }
        if (imageFile == null && imageFile.length == 0) {
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
                    log.error("修改环境布置信息失败:图片插入失败");
                    throw new NullPointerException("修改环境布置信息失败:图片插入失败");
                }
                EnvironmentImgRelate environmentImgRelate = new EnvironmentImgRelate();
                environmentImgRelate.setId(IdUtil.getUUID());
                environmentImgRelate.setImgId(imageId);
                environmentImgRelate.setEnvironmentId(environmentId);
                Integer environmentImgRelateInsertId = environmentImgRelateDao.insert(environmentImgRelate);
                log.error("修改环境布置信息失败:图片删除失败");
                if (environmentImgRelateInsertId == null) {
                    throw new NullPointerException("修改环境布置信息失败:图片删除失败");
                }
            }
        }
        return true;
    }

    /**
     * 更新环境配置视频
     *
     * @param videoFile
     * @param environmentId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateEnvironmentalVideo(MultipartFile[] videoFile, long environmentId, String userNum, Long[] videoIds) {
        if (videoIds != null && videoIds.length > 0) {
            if (environmentVideoRelateDao.deleteEnvironmentVideoRelateById(environmentId, videoIds) < 1 || videoDao.batchDeleteByIds(Arrays.asList(videoIds)) < 1) {
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
                    log.error("修改环境布置信息失败:视频插入失败");
                    throw new NullPointerException("修改环境布置信息失败:视频插入失败");
                }
                EnvironmentVideoRelate environmentVideoRelate = new EnvironmentVideoRelate();
                environmentVideoRelate.setId(IdUtil.getUUID());
                environmentVideoRelate.setEnvironmentId(environmentId);
                environmentVideoRelate.setVideoId(videoId);
                Integer insertMenuitemVideoRelateId = environmentVideoRelateDao.insert(environmentVideoRelate);
                if (insertMenuitemVideoRelateId == null) {
                    log.error("更新菜品信息失败:视频插入失败");
                    throw new NullPointerException("更新菜品信息失败:视频插入失败");
                }
            }
        }
        return true;
    }

    /**
     * 根据environmentalId删除环境配置信息
     *
     * @param environmentalId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteEnvironmentalInfoById(long environmentalId) {
        Environment environment = environmentDao.load(environmentalId);
        if (environment == null) {
            log.error("删除环境配置信息:数据不存在");
            return CommonResult.fail("删除环境配置信息:数据不存在");
        }
        environment.setId(environmentalId);
        environment.setUpdateTime(new Date());
        environment.setUpdateBy(TokenUtils.getUserNum());
        environment.setDelFlag(1);
        Integer updateId = environmentDao.update(environment);
        if (updateId == null) {
            return CommonResult.fail();
        }
        return updateId > 0 ? CommonResult.ok() : CommonResult.fail();
    }


}
