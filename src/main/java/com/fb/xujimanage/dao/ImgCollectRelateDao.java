package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.ImgCollectRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chengjie
 * @date 2020-08-14 13:59
 * @description:图片月图集关联
 * @version:
 */
@Repository
public interface ImgCollectRelateDao {
    Integer addImgCollectRelate(@Param("id") long id, @Param("imgId") long imgId, @Param("collectId") long collectId);

    /**
     * 根据图集或图片id删除关联（至少填一个）
     *
     * @param collectId
     * @param imgId
     * @return
     */
    Integer deleteImgCollectRelate(@Param("collectId") Long collectId, @Param("imgId") Long imgId);

    ImgCollectRelate queryImgCollectRelate(@Param("collectId") long collectId);
}
