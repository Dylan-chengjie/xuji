package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.copy.SetMealDetailCopy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetMealDetailDao {
    /**
     * 根据ID查询
     * @param setMealId
     * @return
     */
    SetMealDetailCopy selectOneById(@Param("setMealId") String setMealId);

    /**
     * 修改单条
     * @param setMealDetailCopy
     * @return
     */
    int  updateOne(@Param("setMealDetailCopy") SetMealDetailCopy setMealDetailCopy);

    /**
     * 添加单条
     * @param setMealDetailCopy
     * @return
     */
    int  insertOne(@Param("setMealDetailCopy") SetMealDetailCopy setMealDetailCopy);

    /**
     * 删除门店下面的订单明细
     * @param restaurantId
     * @return
     */
    int  delMealDetail(@Param("restaurantId") String restaurantId);

    /**
     * 批量插入数据
     * @param setMealDetails
     * @return
     */
    int insertMealDetail(@Param("setMealDetails") List<SetMealDetailCopy> setMealDetails);

    /**
     * 批量删除
     * @param setMealIds
     * @return
     */
    int delSetMealDetail(@Param("setMealIds") List<String> setMealIds);
}
