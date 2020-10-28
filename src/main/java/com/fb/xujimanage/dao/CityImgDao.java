package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.CityImg;
import com.fb.xujimanage.entity.vo.CityImgPageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityImgDao {
    /**
     * 新增
     */
    int insert(@Param("cityImg") CityImg cityImg);

    /**
     * 删除
     */
    int delete(@Param("cityCode") String cityCode);

    /**
     * 更新
     */
    int update(@Param("cityImg") CityImg cityImg);

    /**
     * Load查询
     */
    CityImg loadByCityCode(@Param("cityCode") String cityCode);

    /**
     * 功能描述 根据城市名称查询数据
     * @author false_老默
     * @param cityName
     * @date 2020/9/14 11:15
     * @return java.util.List<com.fb.xujimanage.entity.vo.CityImgPageVo>
     */
    List<CityImgPageVo> pageList(@Param("cityName")String cityName);
    /**
     * 功能描述
     * @author false_老默
     *
     * @date 2020/9/22 11:33 
     * @return java.util.List<com.fb.xujimanage.entity.CityImg>
     */
    List<CityImg> allList();
}
