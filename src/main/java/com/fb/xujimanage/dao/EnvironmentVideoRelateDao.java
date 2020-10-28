package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.EnvironmentVideoRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 16:08
 * @description:
 */
@Repository
public interface EnvironmentVideoRelateDao {
    /**
     * 新增
     */
    public int insert(EnvironmentVideoRelate environmentVideoRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(EnvironmentVideoRelate environmentVideoRelate);

    /**
     * Load查询
     */
    public EnvironmentVideoRelate load(@Param("id") int id);

    /**
     * 根据环境配置id删除视频信息
     *
     * @param environmentId
     * @return
     */
    public int deleteEnvironmentVideoRelateById(@Param("environmentId") long environmentId, @Param("videoIds") Long[] videoIds);

}
