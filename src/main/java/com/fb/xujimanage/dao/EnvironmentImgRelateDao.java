package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.EnvironmentImgRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 16:26
 * @description:
 */
@Repository
public interface EnvironmentImgRelateDao {
    /**
     * 新增
     */
    public int insert(EnvironmentImgRelate environmentImgRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(EnvironmentImgRelate environmentImgRelate);

    /**
     * Load查询
     */
    public EnvironmentImgRelate load(@Param("id") int id);

    /**
     * 根据环境id删除图片信息
     *
     * @param environmentId
     * @return
     */
    public int deleteEnvironmentImgRelateById(@Param("environmentId") long environmentId, @Param("imageIds") Long[] imageIds);
}
