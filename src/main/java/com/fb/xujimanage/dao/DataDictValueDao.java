package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.DataDictValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:28
 * @description:字典数据值
 */
@Repository
public interface DataDictValueDao {
    /**
     * 新增数据字典数据
     *
     * @param dataDictValue
     * @return Integer
     */
    Integer addDataDictValue(DataDictValue dataDictValue);


    /**
     * 更新数据字典数据
     *
     * @param dataDictValue
     * @return Integer
     */
    Integer updateDataDictValue(DataDictValue dataDictValue);

    /**
     * 批量删除数据字典数据
     *
     * @param dataIds
     * @return Integer
     */
    Integer batchDeleteDataDictValue(@Param("dataIds") List<Long> dataIds);

    /**
     * 根据数据字典id删除字典值
     *
     * @param dictionaryId
     * @return Integer
     */
    Integer deleteDataDictValueByDictionaryId(@Param("dictionaryId") Long dictionaryId);

    /**
     * 根据数据字典id查询字典数据
     *
     * @param dictionaryId
     * @return List<DataDictValue>
     */
    List<DataDictValue> queryValueByDictionaryId(@Param("dictionaryId") long dictionaryId);

    /**
     * Load查询
     */
    DataDictValue load(@Param("id") long id);
}
