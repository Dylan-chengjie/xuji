package com.fb.xujimanage.service;


import com.fb.xujimanage.util.CommonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务逻辑接口类
 * <p>
 * Created by chengjie on 08/013/2020.
 */
public interface FileService {

    /**
     * 上传图片
     *
     * @param file
     * @param imgServerPath
     */
    CommonResult fileUpload(MultipartFile file, String imgServerPath);

    CommonResult fileUpload(MultipartFile file, String imgServerPath,int size);

}
