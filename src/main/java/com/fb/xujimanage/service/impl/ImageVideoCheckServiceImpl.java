package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.service.IFileCheckService;
import com.fb.xujimanage.util.CommonResult;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/11 9:49
 * @description:图片校验
 */
@Log4j
@Service
public class ImageVideoCheckServiceImpl implements IFileCheckService {
    /**
     * 图片上传最大数量限制
     */
    private static final int IMAGE_FILE_MAX_SIZE = 6;
    /**
     * 视频上传最大数量限制
     */
    private static final int VIDEO_FILE_MAX_SIZE = 3;
    /**
     * 单条视频最大容量限制
     */
    private static final long VIDEO_FILE_MAX_LENGTH = 1024 * 1024 * 700;
    /**
     * 单张图片最大容量限制
     */
    private static final long IMAGE_FILE_MAX_LENGTH = 1024 * 1024;

    /**
     * 图片视频校验
     *
     * @param imageFile
     * @return
     */
    @Override
    public CommonResult fileCheck(MultipartFile[] imageFile, MultipartFile[] videoFile) {
        //图片校验
        if (imageFile != null && imageFile.length > 0) {
            //图片上传数量校验
            if (imageFile.length > IMAGE_FILE_MAX_SIZE) {
                return CommonResult.fail("图片上传不能大于" + IMAGE_FILE_MAX_SIZE + "张");
            }
            try {
                for (MultipartFile multipartFile : imageFile) {
                    //图片上传大小校验
                    if (multipartFile.getBytes().length > IMAGE_FILE_MAX_LENGTH) {
                        return CommonResult.fail("图片大小不能超过1M");
                    }
                    //简单图片后缀名校验
                    String fileName = multipartFile.getOriginalFilename();
                    String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
                    if (!fileSuffix.matches("^.*(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|bmp|BMP)$")) {
                        return CommonResult.fail("图片类型有误");
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("图片校验异常");
                return CommonResult.fail("图片校验异常");
            }
        }
        //视频校验
        if (videoFile != null && videoFile.length > 0) {
            if (videoFile.length > VIDEO_FILE_MAX_SIZE) {
                return CommonResult.fail("视频上传最大不能超过" + VIDEO_FILE_MAX_SIZE + "段");
            }
            try {
                for (MultipartFile multipartFile : videoFile) {
                    //视频上传大小校验
                    if (multipartFile.getBytes().length > VIDEO_FILE_MAX_LENGTH) {
                        return CommonResult.fail("视频大小不能超过700M");
                    }
                    //简单视频后缀名校验
                    String fileName = multipartFile.getOriginalFilename();
                    String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
                    if (!fileSuffix.matches("^.*(avi|AVI|rmvb|RMVB|mp4|MP4|3gp|3GP)$")) {
                        return CommonResult.fail("视频类型有误");
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("视频校验异常");
                return CommonResult.fail("视频校验异常");
            }
        }
        return CommonResult.ok();
    }
}
