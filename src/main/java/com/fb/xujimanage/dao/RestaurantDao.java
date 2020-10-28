package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.dto.RestaurantPageDto;
import com.fb.xujimanage.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @description:门店信息维护
 * @date 2020/8/17 14:28
 */
public interface RestaurantDao {
    /**
     * 新增
     */
    public int insert(@Param("restaurant") Restaurant restaurant);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 清空所有组织信息
     */
    int deleteByNotFlag(@Param("modifyFlags") List<Long> modifyFlags);

    /**
     * 更新
     */
    public int update(@Param("restaurant") Restaurant restaurant);

    /**
     * Load查询
     */
    public Restaurant load(@Param("id") Long id);

    /**
     * 查询所有的门店信息
     *
     * @return
     */
    public List<Restaurant> loadAll(@Param("restaurantInfoVo") RestaurantInfoVo restaurantInfoVo);

    /**
     * 门店下拉列表
     *
     * @return
     */
    List<RestaurantsVo> selectRestaurantListBy(@Param("type") Integer type, @Param("city") String city, @Param("restaurantName") String restaurantName);


    List<RestaurantVo> selectRestaurantList(@Param("orgType") String orgType, @Param("delFlag") Integer delFlag);

    /**
     * 查询城市区域信息
     *
     * @return
     */
    List<Restaurant> selectCity();

    int batchAddRestaurant(List<RestaurantDetailsVo> restaurantDetailsVos);


    /**
     * 功能描述 查询门店图片分页数据
     *
     * @param cityCode       城市编码
     * @param restaurantName 门店名称
     * @return java.util.List<com.fb.xujimanage.entity.vo.RestaurantPageVo>
     * @author false_老默
     * @date 2020/9/14 14:43
     */
    List<RestaurantPageVo> pageListRestaurantImg(@Param("cityCode") String cityCode,
                                                 @Param("restaurantName") String restaurantName,
                                                 @Param("status") Integer status);

    /**
     * 功能描述 根据门店ID修改图片ID
     *
     * @param id    门店主键ID
     * @param imgId 图片ID
     * @return void
     * @author false_老默
     * @date 2020/9/14 15:22
     */
    /**
     * 功能描述
     *
     * @param id         门店ID
     * @param vrUrl      vr路径
     * @param imgId      门店主键ID
     * @param city       门店城市编码
     * @param address    门店城市地址
     * @param modifyFlag 是否在后头修改  0 未修改；1 修改；
     * @param contactWay 门店联系电话
     * @return void
     * @author false_老默
     * @date 2020/9/18 14:13
     */
    void updateImgIdById(@Param("id") Long id,
                         @Param("vrUrl") String vrUrl,
                         @Param("imgId") Long imgId,
                         @Param("city") String city,
                         @Param("address") String address,
                         @Param("modifyFlag") Integer modifyFlag,
                         @Param("contactWay") String contactWay);

    /**
     * 功能描述 查询门店城市下拉选择数据
     *
     * @return java.util.List<com.fb.xujimanage.entity.vo.RestaurantCitySelectVo>
     * @author false_老默
     * @date 2020/9/16 17:52
     */
    List<RestaurantCitySelectVo> getRestaurantCitySelect(@Param("status") Integer status);

    /**
     * 功能描述
     *
     * @param id     门店ID
     * @param status 门店状态 门店是否启用  0 启用  ； 1 禁用
     * @return com.fb.xujimanage.util.CommonResult
     * @author false_老默
     * @date 2020/9/18 14:26
     */
    int updateStatusById(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据orgType 查询门店信息
     * @param orgType
     * @return
     */
    List<RestaurantVo> selectRestaurantListOrgType(@Param("orgType") String orgType);

}
