package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.DataDictValue;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-19 14:18
 * @description:数据字典数据接口
 * @version:
 */
public interface DataDictValueService {
    /**
     * 新增数据字典数据
     *
     * @param dataDictValue
     * @return CommonResult
     */
    CommonResult addDataDictValue(DataDictValue dataDictValue);


    /**
     * 更新数据字典数据
     *
     * @param dataDictValue
     * @return CommonResult
     */
    CommonResult updateDataDictValue(DataDictValue dataDictValue);

    /**
     * 批量删除数据字典数据
     *
     * @param dataIds
     * @return CommonResult
     */
    CommonResult batchDeleteDataDictValue(List<Long> dataIds);

    /**
     * 根据数据字典id查询字典数据
     *
     * @param dictionaryId
     * @return CommonResult
     */
    CommonResult<List<DataDictValue>> queryValueByDictionaryId(long dictionaryId);


}
