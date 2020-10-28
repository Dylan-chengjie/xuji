package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.util.CommonResult;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author chengjie
 * @date 2020-08-13 13:59
 * @description:文件相关
 * @version:
 */
@Service
@Log4j
public class FileServiceImpl implements FileService {
    @Value("${file.server.url}")
    private String fileServerUrl;

    @Override
    public CommonResult fileUpload(MultipartFile uploadFile, String imgServerPath) {
        try {
            //1.获取图片名称
            String fileName = uploadFile.getOriginalFilename();
            //2.准备时间文件夹 并且时间格式 yyyy/MM/dd/HH
            String datePathDir = new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

            //3.准备时间 时间毫秒数+三位随机数
            String randomPath = System.currentTimeMillis() + "" + new Random().nextInt(999);

            //4.创建文件夹
            String LocalPath = imgServerPath + datePathDir;
            File file = new File(LocalPath);

            //5.如果文件夹不存在 则创建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }

            //6.通过工具类实现写盘操作
            String LocalPathFile = LocalPath + "/" + randomPath + fileName;
            //文件写盘
            uploadFile.transferTo(new File(LocalPathFile));

            //代表虚拟的全路径
            String urlPath = fileServerUrl + datePathDir + "/" + randomPath + fileName;
            return CommonResult.ok("上传文件成功", urlPath);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("文件上传失败");
        }
    }

    @Override
    public CommonResult fileUpload(MultipartFile file, String imgServerPath, int size) {
        CommonResult commonResult;
        //1.获取图片名称
        String fileName = file.getOriginalFilename();

        //2.获取图片的类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        //3.判断是否为图片格式  采用正则表达式
        if (!fileType.matches("^.*(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|bmp|BMP)$")) {
            return CommonResult.fail("图片类型有误");
        }
        try {
            //判断图片大小不能大于文件限制
            if (file.getBytes().length > size) {
                return CommonResult.fail("图片不能超过1M");
            }
            commonResult = this.fileUpload(file, imgServerPath);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("图片有误");
        }
        return commonResult;
    }
}
