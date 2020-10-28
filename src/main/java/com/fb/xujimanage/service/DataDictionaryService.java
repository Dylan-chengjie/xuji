package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.DataDictionary;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-18 14:18
 * @description:数据字典接口
 * @version:
 */
public interface DataDictionaryService {
    /**
     * 新增数据字典
     *
     * @param dataDictionary
     * @return CommonResult
     */
    CommonResult addDataDictionary(DataDictionary dataDictionary);


    /**
     * 更新数据字典
     *
     * @param dataDictionary
     * @return CommonResult
     */
    CommonResult updateDataDictionary(DataDictionary dataDictionary);

    /**
     * 删除数据字典
     *
     * @param dataId
     * @return CommonResult
     */
    CommonResult deleteDataDictionary(Long dataId);

    /**
     * 查询数据字典
     *
     * @param name
     * @return CommonResult
     */
    CommonResult<List<DataDictionary>> queryDataDictionary(String name, String code);
}
