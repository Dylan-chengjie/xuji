package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * @author chengjie
 * @date 2020-09-20 10:37
 * @description:文件管理
 * @version:v
 */
@Api(description = "文件管理")
@RestController
@AuthToken
@RequestMapping("/file")
public class FileController {
    @Value("${img.server.path}")
    private String imgServerPath;
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public CommonResult fileUpload(@RequestParam("multipartFile") MultipartFile multipartFile) {
        //1.获取图片名称
        String fileName = multipartFile.getOriginalFilename();

        //2.获取图片的类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        //3.判断是否为图片格式  采用正则表达式
        if (!fileType.matches("^.*(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|bmp|BMP)$")) {
            return CommonResult.fail("图片类型有误");
        }
        try {
            //判断图片大小不能大于1M
            if (multipartFile.getBytes().length > 1024 * 1024) {
                return CommonResult.fail("图片不能超过1M");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileService.fileUpload(multipartFile, imgServerPath);
    }
}
