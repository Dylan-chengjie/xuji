package com.fb.xujimanage.service;

import com.fb.xujimanage.util.CommonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/11 9:43
 * @description:文件校验接口
 */
public interface IFileCheckService {
    /**
     * 文件校验
     * @param imageFile
     * @return
     */
    CommonResult fileCheck(MultipartFile[] imageFile,MultipartFile[] videoFile);

}
