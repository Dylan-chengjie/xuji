package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:43
 * @description:菜单
 */
@Repository
public interface MenuDao {
    /**
     * 新增
     */
    public int insert(Menu menu);

    /**
     * 删除
     */
    public Integer delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(Menu menu);

    /**
     * Load查询
     */
    public Menu load(@Param("id") long id);

    /**
     * 级联查询菜单信息
     *
     * @return
     */
    public List<Menu> loadAll(@Param("menuName") String menuName, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 计算分页总数
     *
     * @param menuName
     * @return
     */
    int getMenuTotal(@Param("menuName") String menuName);

    /**
     * 根据id级联查询菜单信息
     *
     * @param id
     * @return
     */
    public List<Menu> loadAllById(int id);

    /**
     * 根据name级联查询菜单信息
     *
     * @param name
     * @return
     */
    List<Menu> loadAllByName(@Param("name") String name, @Param("restaurantIds") Long[] restaurantIds);

    /**
     * 根据门店id、适用口味id、适用人数id、预算范围id查询菜单
     *
     * @param restaurantIds
     * @param personDicvalueid
     * @param tasteDicvalueid
     * @param budgetId
     * @return
     */
    List<Menu> queryMenu(@Param("restaurantIds") Long[] restaurantIds, @Param("personDicvalueid") Long personDicvalueid,
                         @Param("tasteDicvalueid") Long tasteDicvalueid, @Param("budgetId") String budgetId);
}
