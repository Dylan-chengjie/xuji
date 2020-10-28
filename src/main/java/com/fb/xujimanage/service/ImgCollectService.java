package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.ImgCollect;
import com.fb.xujimanage.entity.vo.ImgCollectVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-17 14:18
 * @description:图集业务逻辑接口类
 * @version:
 */
public interface ImgCollectService {
    /**
     * 新增首页图集
     *
     * @param file
     * @param name
     * @param type
     * @param url
     * @param remarks
     * @param sortNu
     * @param createBy
     * @param updateBy
     * @return
     */
    CommonResult addImgCollect(MultipartFile file, String name, Integer type, String url, String remarks,
                               Integer general, Integer sortNu, String createBy, String updateBy);


    /**
     * 更新首页图集
     *
     * @param file
     * @param name
     * @param type
     * @param url
     * @param remarks
     * @param updateBy
     * @return
     */
    CommonResult updateImgCollect(MultipartFile file, Long id, Long imgId, String name, Integer general,
                                  Integer type, String url, String remarks, Integer sortNum, String updateBy);

    /**
     * 删除首页图集
     *
     * @param collectId
     * @return
     */
    CommonResult deleteImgCollect(long collectId);

    /**
     * 分页查询首页图集
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @param name
     * @return
     */
    CommonResult<PageInfo<List<ImgCollectVo>>> pageImgCollect(Integer pageNum, Integer pageSize, Integer type, String name);
}
