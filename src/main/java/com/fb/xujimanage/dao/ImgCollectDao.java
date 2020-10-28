package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.ImgCollect;
import com.fb.xujimanage.entity.vo.ImgCollectVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-14 13:59
 * @description:促销相关
 * @version:
 */
@Repository
public interface ImgCollectDao {

    /**
     * 新增促销
     *
     * @param id
     * @param name
     * @param type
     * @param remarks
     * @param createBy
     * @param updateBy
     * @return Integer
     */
    Integer addImgCollect(@Param("id") long id, @Param("name") String name, @Param("type") Integer type, @Param("remarks") String remarks,
                          @Param("general") Integer general, @Param("createBy") String createBy, @Param("updateBy") String updateBy);

    /**
     * 更新首页图集
     *
     * @param id
     * @param name
     * @param type
     * @param remarks
     * @param updateBy
     * @return
     */
    Integer updateImgCollect(@Param("id") Long id, @Param("name") String name, @Param("type") Integer type,
                             @Param("general") Integer general, @Param("remarks") String remarks, @Param("updateBy") String updateBy);

    /**
     * 删除首页图集
     *
     * @param collectId
     * @return Integer
     */
    Integer deleteImgCollect(@Param("id") long collectId);

    /**
     * 分页查询首页图集
     *
     * @param type
     * @param name
     * @return List<ImgCollect>
     */
    List<ImgCollectVo> pageImgCollect(@Param("type") Integer type, @Param("name") String name);
}
