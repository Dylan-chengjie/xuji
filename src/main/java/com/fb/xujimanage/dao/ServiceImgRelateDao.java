package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.ServiceImgRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 10:11
 * @description:服务图片关联表
 */
@Repository
public interface ServiceImgRelateDao {
    /**
     * 新增
     */
    public int insert(@Param("serviceImgRelate") ServiceImgRelate serviceImgRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("serviceImgRelate") ServiceImgRelate serviceImgRelate);

    /**
     * Load查询
     */
    public ServiceImgRelate load(@Param("id") int id);

    /**
     * 根据服务特色id删除图片信息
     *
     * @param serviceId
     * @return
     */
    public int deleteServiceImgRelate(@Param("serviceId") long serviceId, @Param("imageIds") Long[] imageIds);
}
