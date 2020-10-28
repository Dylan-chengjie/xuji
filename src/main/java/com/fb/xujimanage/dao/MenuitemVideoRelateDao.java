package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.MenuitemVideoRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:59
 * @description:
 */
@Repository
public interface MenuitemVideoRelateDao {
    /**
     * 新增
     */
    public int insert(MenuitemVideoRelate menuitemVideoRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("menuitemVideoRelate") MenuitemVideoRelate menuitemVideoRelate);

    /**
     * Load查询
     */
    public MenuitemVideoRelate load(@Param("id") int id);

    /**
     * 根据menuitemId查询
     * @param menuitemId
     * @return
     */
    public int deleteMenuitemVideoRelateById(String menuitemId);


    public int deleteMenuitemVideoRelate(@Param("menuitemId") String menuitemId, @Param("videoIds") Long[] videoIds);

}
