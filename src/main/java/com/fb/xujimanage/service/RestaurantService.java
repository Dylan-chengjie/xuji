package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.dto.RestaurantImgDto;
import com.fb.xujimanage.entity.dto.RestaurantPageDto;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 徐记电子门店信息表(Restaurant)表服务接口
 *
 * @author sam.yang
 * @since 2020-08-25 15:29:16
 */
public interface RestaurantService {


    /**
     * 查询电子门店下拉列表
     *
     * @return
     */
    List<RestaurantsVo> getRestaurantList(Integer type, String city, String restaurantName);

    /**
     * 批量同步门店信息
     *
     * @return
     */
    void batchSyncRestaurantList(List<RestaurantDetailsVo> restaurantDetailsVos);

    /**
     * 功能描述 查询门店图片分页列表数据
     *
     * @param dto
     * @return com.github.pagehelper.PageInfo<com.fb.xujimanage.entity.vo.RestaurantPageVo>
     * @author false_老默
     * @date 2020/9/14 14:41
     */
    PageInfo<RestaurantPageVo> pageListRestaurantImg(RestaurantPageDto dto);

    /**
     * 功能描述 修改门店图片信息
     *
     * @param dto
     * @return com.fb.xujimanage.util.CommonResult
     * @author false_老默
     * @date 2020/9/14 15:04
     */
    CommonResult updateRestaurantImgById(RestaurantImgDto dto, MultipartFile file);
    /**
     * 功能描述 查询门店城市下拉选择数据
     * @author false_老默
     * @date 2020/9/16 17:52
     * @return java.util.List<com.fb.xujimanage.entity.vo.RestaurantCitySelectVo>
     */
    List<RestaurantCitySelectVo> getRestaurantCitySelect(Integer status);
    List<RestaurantVo> selectRestaurantByStore(String orgType);

    /**
     * 功能描述
     * @author false_老默
     * @param id 门店ID
     * @param status 门店状态 门店是否启用  0 启用  ； 1 禁用
     * @date 2020/9/18 14:26
     * @return com.fb.xujimanage.util.CommonResult
     */
    CommonResult updateStatusById(Long id,Integer status);
}