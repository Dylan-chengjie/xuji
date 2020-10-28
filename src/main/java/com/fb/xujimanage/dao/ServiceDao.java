package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.FeatureService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 20:37
 * @description:服务特色
 */
@Repository
public interface ServiceDao {
    /**
     * 新增
     */
    public int insert(FeatureService featureService);

    /**
     * 删除
     */
    public Integer delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(FeatureService featureService);

    /**
     * Load查询
     */
    public FeatureService load(@Param("id") long id);


    FeatureService selectFeatureService(@Param("restaurantId") long restaurantId, @Param("sceneId") long sceneId);

    /**
     * 根据服务特色id或者名称查询服务特色信息
     * @param sellingPointName
     * @return
     */
    public List<FeatureService> loadServiceInfo(@Param("sellingPointName") String sellingPointName,@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 分页总数
     * @param sellingPointName
     * @return
     */
    int getServiceTotal(@Param("sellingPointName") String sellingPointName);

}
