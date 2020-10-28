package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.CityImg;
import com.fb.xujimanage.entity.dto.CityImgDto;
import com.fb.xujimanage.entity.dto.CityImgPageDto;
import com.fb.xujimanage.entity.vo.CityImgPageVo;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CityImgService {
    /**
     * 功能描述 新增or修改城市图片信息
     * @author false_老默
     * @param dto 参数
     * @date 2020/9/14 9:59
     * @return boolean
     */
    CommonResult update(CityImgDto dto, MultipartFile file);

    /**
     * 功能描述 根据城市编码删除配置信息
     * @author false_老默
     * @param cityCode 城市编码
     * @date 2020/9/14 10:44
     * @return com.fb.xujimanage.util.CommonResult
     */
    CommonResult deleteByCityCode(String cityCode);

    /**
     * 功能描述 查询城市图片分页列表数据
     * @author false_老默
     * @param dto
     * @date 2020/9/14 11:13
     * @return com.github.pagehelper.PageInfo<java.util.List<com.fb.xujimanage.entity.vo.CityImgPageVo>>
     */
    PageInfo<List<CityImgPageVo>> pageList(CityImgPageDto dto);
    /**
     * 功能描述
     * @author false_老默
     *
     * @date 2020/9/22 11:35
     * @return java.util.List<com.fb.xujimanage.entity.CityImg>
     */
    List<CityImg> allList();
}
