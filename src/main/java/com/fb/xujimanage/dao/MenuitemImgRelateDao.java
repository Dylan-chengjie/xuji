package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.MenuitemImgRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 16:05
 * @description:
 */
@Repository
public interface MenuitemImgRelateDao {
    /**
     * 新增
     */
    public int insert(@Param("menuitemImgRelate") MenuitemImgRelate menuitemImgRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("menuitemImgRelate") MenuitemImgRelate menuitemImgRelate);

    /**
     * Load查询
     */
    public MenuitemImgRelate load(@Param("id") int id);

    /**
     * 根据menuItemId删除
     * @param menuItemId
     * @return
     */
    public int deleteMenuitemImgRelateById(String menuItemId);


    public int deleteMenuItemImgRelate(@Param("menuItemId") String menuItemId, @Param("imageIds") Long[] imageIds);
}
