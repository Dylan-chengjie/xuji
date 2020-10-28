package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.copy.SetMealCopy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  summer.chou
 * @date 2020/9/10
 * @description:套餐信息表
 */
@Repository
public interface SetMealDao {
    /**
     * 根据ID查询
     * @param setId
     * @return
     */
    SetMealCopy selectOneById(@Param("setId") long setId);

    /**
     * 修改单条
     * @param setMealCopy
     * @return
     */
    int  updateOne(@Param("setMealCopy") SetMealCopy setMealCopy);

    /**
     * 添加单条
     * @param setMealCopy
     * @return
     */
    int  insertOne(@Param("setMealCopy") SetMealCopy setMealCopy);

    /**
     * 删除门店下的套餐
     * @param restaurantId
     * @return
     */
    int delsetMealByRestaurantId(@Param("restaurantId") String restaurantId);

    /**
     * 批量插入套餐信息
     * @param setMealCopies
     * @return
     */
    int insertSetMeal(@Param("setMealCopies") List<SetMealCopy> setMealCopies);

    /**
     * 批量删除
     * @param setIds
     * @return
     */
    int delSetMeal(@Param("setIds") List<String> setIds);

    List<SetMealCopy> selectSetMealCopy(@Param("setIds") List<String> setIds);


}
