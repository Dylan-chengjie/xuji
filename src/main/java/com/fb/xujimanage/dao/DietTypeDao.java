package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.DietType;
import com.fb.xujimanage.entity.dto.DietTypeQueryDto;
import com.fb.xujimanage.entity.vo.DietTypeResVo;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品类别表(DietType)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-28 17:53:48
 */
@Repository
public interface DietTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DietType queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DietType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dietType 实例对象
     * @return 对象列表
     */
    List<DietType> queryAll(DietType dietType);

    /**
     * 新增数据
     *
     * @param dietType 实例对象
     * @return 影响行数
     */
    int insert(DietType dietType);

    /**
     * 修改数据
     *
     * @param dietType 实例对象
     * @return 影响行数
     */
    int update(DietType dietType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Long id);

    /**
     * 列表查询
     * @param queryDto
     * @return
     */
    List<DietTypeVo> findAll(DietTypeQueryDto queryDto);

    /**
     * 根据门店获取菜品的下拉列表
     * @param restaurantCode
     * @return
     */
    List<DietTypeResVo> findDietTypeByRestaurantCode(@Param("restaurantCode") String restaurantCode);

    /**
     * 根据CODE  big_or_small 查询
     * @param dietType
     * @return
     */
    DietType selectOne(@Param("dietType") DietType dietType);


    /**
     * 添加
     * @param dietType
     * @return
     */
    int  insertOne(@Param("dietType") DietType dietType);

    int  updateOne(@Param("dietType") DietType dietType);


    List<DietType> selectDietTypes(@Param("codes")List<String> codes);

    /**
     *  根据restaurantCode 删除
     * @param restaurantCode
     * @return
     */
    int delByRestaurantCode(@Param("restaurantCode") String restaurantCode);

    /**
     * 批量插入
     * @param dietTypes
     * @return
     */
    int insertDietType(List<DietType> dietTypes);

    /**
     *  批量删除
     * @param ids
     * @return
     */
    int delDietType(@Param("ids") List<Long> ids);

}