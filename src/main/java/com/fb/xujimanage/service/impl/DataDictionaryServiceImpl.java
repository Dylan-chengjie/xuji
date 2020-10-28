package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.DataDictValueDao;
import com.fb.xujimanage.dao.DataDictionaryDao;
import com.fb.xujimanage.entity.DataDictionary;
import com.fb.xujimanage.service.DataDictionaryService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-18 14:18
 * @description:数据字典接口实现
 * @version:
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    private DataDictionaryDao dataDictionaryDao;
    private DataDictValueDao dataDictValueDao;

    public DataDictionaryServiceImpl(DataDictionaryDao dataDictionaryDao, DataDictValueDao dataDictValueDao) {
        this.dataDictionaryDao = dataDictionaryDao;
        this.dataDictValueDao = dataDictValueDao;
    }

    @Override
    public CommonResult addDataDictionary(DataDictionary dataDictionary) {
        String userNum = TokenUtils.getUserNum();
        dataDictionary.setId(IdUtil.getUUID());
        dataDictionary.setCreateBy(userNum);
        dataDictionary.setUpdateBy(userNum);
        dataDictionaryDao.addDataDictionary(dataDictionary);
        return CommonResult.ok("添加数据字典成功");
    }

    @Override
    public CommonResult updateDataDictionary(DataDictionary dataDictionary) {
        DataDictionary data = dataDictionaryDao.getDataDictionaryById(dataDictionary.getId());
        if (null != data) {
            dataDictionary.setUpdateBy(TokenUtils.getUserNum());
            return dataDictionaryDao.updateDataDictionary(dataDictionary) > 0 ?
                    CommonResult.ok("更新数据字典成功") : CommonResult.fail("更新数据字典失败");
        }
        return CommonResult.fail("字典不存在或者字典已被删除");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteDataDictionary(Long dataId) {
        if (dataDictionaryDao.deleteDataDictionary(dataId) < 1) {
            return CommonResult.fail("删除数据字典失败");
        }
        dataDictValueDao.deleteDataDictValueByDictionaryId(dataId);
        return CommonResult.ok("删除数据字典成功");
    }

    @Override
    public CommonResult<List<DataDictionary>> queryDataDictionary(String name, String code) {
        return CommonResult.ok("查询数据字典成功", dataDictionaryDao.queryDataDictionary(name, code));
    }
}
