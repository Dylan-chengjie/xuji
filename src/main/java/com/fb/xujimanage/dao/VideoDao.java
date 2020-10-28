package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @description:视频
 * @date 2020/8/17 14:32
 */
@Repository
public interface VideoDao {
    /**
     * 新增
     */
    public int insert(Video video);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 删除
     */
    public int batchDeleteByIds(@Param("ids") List<Long> ids);

    /**
     * 更新
     */
    public int update(Video video);

    /**
     * Load查询
     */
    public Video load(@Param("id") int id);

}
