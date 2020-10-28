package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.DietType;
import com.fb.xujimanage.entity.dto.DietTypeDto;
import com.fb.xujimanage.entity.dto.DietTypeQueryDto;
import com.fb.xujimanage.entity.dto.DietTypeUpdateDto;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 菜品类别表(DietType)表服务接口
 *
 * @author makejava
 * @since 2020-08-28 17:53:49
 */
public interface DietTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DietTypeVo queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DietType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dietType 实例对象
     * @return 实例对象
     */
    DietType insert(DietType dietType);

    /**
     * 修改数据
     *
     * @param dietTypeUpdateDto 实例对象
     * @return 实例对象
     */
    Integer update(DietTypeUpdateDto dietTypeUpdateDto);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Integer deleteById(Long id);

    /**
     * 添加菜品分类
     * @param dietType
     * @return
     */
    Integer add(DietTypeDto dietType);

    /**
     * 列表查询
     * @param queryDto
     * @return
     */
    CommonResult<PageInfo<List<DietTypeVo>>> findAll(Integer pageNum, Integer pageSize,DietTypeQueryDto queryDto);


    CommonResult findDietType(String restaurantCode);

    void  update(DietType diet,String bCode,String sCode,String bName, String sName);
}