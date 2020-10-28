package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.DataDictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:33
 * @description: 数据字典
 */
@Repository
public interface DataDictionaryDao {
    /**
     * 新增数据字典
     *
     * @param dataDictionary
     * @return Integer
     */
    Integer addDataDictionary(DataDictionary dataDictionary);


    /**
     * 更新数据字典
     *
     * @param dataDictionary
     * @return Integer
     */
    Integer updateDataDictionary(DataDictionary dataDictionary);

    /**
     * 删除数据字典
     *
     * @param dataId
     * @return Integer
     */
    Integer deleteDataDictionary(@Param("id") long dataId);

    /**
     * 查询数据字典
     *
     * @param name
     * @return List<DataDictionary>
     */
    List<DataDictionary> queryDataDictionary(@Param("name") String name, @Param("code") String code);

    /**
     * 功能描述 根据ID查询字段实体信息
     *
     * @param id 字典主键
     * @return com.fb.xujimanage.entity.DataDictionary
     * @author false_老默
     * @date 2020/9/9 14:37
     */
    DataDictionary getDataDictionaryById(@Param("id") Long id);
}
