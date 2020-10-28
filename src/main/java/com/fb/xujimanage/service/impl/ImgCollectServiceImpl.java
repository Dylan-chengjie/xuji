package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.ImageDao;
import com.fb.xujimanage.dao.ImgCollectDao;
import com.fb.xujimanage.dao.ImgCollectRelateDao;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.ImgCollect;
import com.fb.xujimanage.entity.ImgCollectRelate;
import com.fb.xujimanage.entity.vo.ImgCollectVo;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.ImgCollectService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-17 14:24
 * @description:图集业务逻辑实现类
 * @version:
 */
@Service
@Log4j
public class ImgCollectServiceImpl implements ImgCollectService {
    @Value("${img.server.path}")
    private String imgServerPath;
    private FileService fileService;
    private ImgCollectDao imgCollectDao;
    private ImageDao imageDao;
    private ImgCollectRelateDao imgCollectRelateDao;

    public ImgCollectServiceImpl(FileService fileService, ImgCollectDao imgCollectDao, ImageDao imageDao, ImgCollectRelateDao imgCollectRelateDao) {
        this.fileService = fileService;
        this.imgCollectDao = imgCollectDao;
        this.imageDao = imageDao;
        this.imgCollectRelateDao = imgCollectRelateDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addImgCollect(MultipartFile uploadFile, String name, Integer type, String url, String remarks,
                                      Integer general, Integer sortNu, String createBy, String updateBy) {
        if (type != null && (type < 0 || type > 2)) {
            return CommonResult.fail("type不在取值范围内");
        }
        if (general != null && (general < 0 || general > 1)) {
            return CommonResult.fail("general不在取值范围内");
        }
        CommonResult commonResult;
        //1.获取图片名称
        String fileName = uploadFile.getOriginalFilename();

        //2.获取图片的类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        //3.判断是否为图片格式  采用正则表达式
        if (!fileType.matches("^.*(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|bmp|BMP)$")) {
            return CommonResult.fail("图片类型有误");
        }
        try {
            //判断图片大小不能大于1M
            if (uploadFile.getBytes().length > 1024 * 1024) {
                return CommonResult.fail("图片不能超过1M");
            }
            commonResult = fileService.fileUpload(uploadFile, imgServerPath);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("图片有误");
        }
        if (commonResult.isOk()) {
            String urlPath = (String) commonResult.getData();
            long imageId = IdUtil.getUUID();
            imageDao.addImage(imageId, fileName, urlPath, url, remarks, createBy, updateBy, sortNu);
            long imgCollectId = IdUtil.getUUID();
            Integer addImgCollectResult = imgCollectDao.addImgCollect(imgCollectId, name, type, remarks, general, createBy, updateBy);
            if (addImgCollectResult < 1) {
                CommonResult.throwVerifyException("新增首页图集失败");
            }
            long imgCollectRelateId = IdUtil.getUUID();
            Integer imgRelateResult = imgCollectRelateDao.addImgCollectRelate(imgCollectRelateId, imageId, imgCollectId);
            if (imgRelateResult < 1) {
                CommonResult.throwVerifyException("新增首页图集失败");
            }
            return CommonResult.ok("新增首页图集成功");
        } else {
            return commonResult;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateImgCollect(MultipartFile uploadFile, Long id, Long imgId, String name, Integer general, Integer type, String url, String remarks, Integer sortNum, String updateBy) {
        if (type != null && (type < 0 || type > 2)) {
            return CommonResult.fail("type不在取值范围内");
        }
        if (general != null && (general < 0 || general > 1)) {
            return CommonResult.fail("general不在取值范围内");
        }
        Long imageId = IdUtil.getUUID();
        if (uploadFile != null) {
            if (imageDao.deleteImage(imgId) < 1 || imgCollectRelateDao.deleteImgCollectRelate(null, imgId) < 1) {
                CommonResult.throwVerifyException("更新菜单栏失败");
            }
            String fileName = uploadFile.getOriginalFilename();
            CommonResult commonResult = fileService.fileUpload(uploadFile, imgServerPath);
            if (commonResult.isOk()) {
                String urlPath = (String) commonResult.getData();
                imageDao.addImage(imageId, fileName, urlPath, url, name, updateBy, updateBy, sortNum);
                long imgCollectRelateId = IdUtil.getUUID();
                imgCollectRelateDao.addImgCollectRelate(imgCollectRelateId, imageId, id);
            }
        }
        Image image = new Image() {{
            setId(imgId);
            setUrl(url);
            setSortNum(sortNum);
        }};
        if (imageDao.update(image) < 1 || imgCollectDao.updateImgCollect(id, name, type, general, remarks, updateBy) < 1) {
            CommonResult.throwVerifyException("更新首页图集失败");
        }
        return CommonResult.ok("更新首页图集成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteImgCollect(long collectId) {
        if (imgCollectDao.deleteImgCollect(collectId) < 1) {
            return CommonResult.fail("删除首页图集失败");
        }
        ImgCollectRelate imgCollectRelate = imgCollectRelateDao.queryImgCollectRelate(collectId);
        if (imgCollectRelate == null) {
            CommonResult.throwVerifyException("删除首页图集失败");
        }
        if (imgCollectRelateDao.deleteImgCollectRelate(collectId, null) < 1) {
            CommonResult.throwVerifyException("删除首页图集失败");
        }
        if (imageDao.deleteImage(imgCollectRelate.getImgId()) < 1) {
            CommonResult.throwVerifyException("删除首页图集失败");
        }
        return CommonResult.ok("删除首页图集成功");
    }

    @Override
    public CommonResult<PageInfo<List<ImgCollectVo>>> pageImgCollect(Integer pageNum, Integer pageSize, Integer type, String name) {
        if (type != null && (type < 0 || type > 2)) {
            return CommonResult.fail("type不在取值范围内");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ImgCollectVo> imgCollects = imgCollectDao.pageImgCollect(type, name);
        return CommonResult.ok("查询首页图集详情列表成功", new PageInfo<ImgCollectVo>(imgCollects));
    }
}
