package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.ServiceVideoRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/19 10:38
 * @description:
 */
@Repository
public interface ServiceVideoRelateDao {
    /**
     * 新增
     */
    public int insert(@Param("serviceVideoRelate") ServiceVideoRelate serviceVideoRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("serviceVideoRelate") ServiceVideoRelate serviceVideoRelate);

    /**
     * Load查询
     */
    public ServiceVideoRelate load(@Param("id") int id);

    /**
     * 根据服务特色id删除视频信息
     *
     * @param serviceId
     * @return
     */
    public int deleteServiceVideoRelate(@Param("serviceId") long serviceId, @Param("videoIds") Long[] videoIds);

}
