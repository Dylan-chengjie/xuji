package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.DataDictValueDao;
import com.fb.xujimanage.dao.DataDictionaryDao;
import com.fb.xujimanage.entity.DataDictValue;
import com.fb.xujimanage.entity.DataDictionary;
import com.fb.xujimanage.service.DataDictValueService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-19 11:18
 * @description:数据字典数据实现类
 * @version:
 */
@Service
public class DataDictValueServiceImpl implements DataDictValueService {

    private DataDictValueDao dataDictValueDao;
    @Resource
    private DataDictionaryDao dataDictionaryDao;

    public DataDictValueServiceImpl(DataDictValueDao dataDictValueDao) {
        this.dataDictValueDao = dataDictValueDao;
    }

    @Override
    public CommonResult addDataDictValue(DataDictValue dataDictValue) {
        if (null != dataDictionaryDao.getDataDictionaryById(dataDictValue.getDictionaryId())) {
            dataDictValue.setId(IdUtil.getUUID());
            String userNum = TokenUtils.getUserNum();
            dataDictValue.setCreateBy(userNum);
            dataDictValue.setUpdateBy(userNum);
            dataDictValueDao.addDataDictValue(dataDictValue);
            return CommonResult.ok("添加数据字典值成功");
        } else {
            return CommonResult.ok("字典不存在或者字典已被删除");
        }
    }

    @Override
    public CommonResult updateDataDictValue(DataDictValue dataDictValue) {
        DataDictionary dataDictionary = dataDictionaryDao.getDataDictionaryById(dataDictValue.getDictionaryId());
        if (dataDictionary != null) {
            dataDictValue.setUpdateBy(TokenUtils.getUserNum());
            return dataDictValueDao.updateDataDictValue(dataDictValue) > 0 ?
                    CommonResult.ok("更新数据字典值成功") : CommonResult.fail("更新数据字典值失败");
        } else {
            return CommonResult.fail("字典不存在或者字典已被删除");
        }

    }

    @Override
    public CommonResult batchDeleteDataDictValue(List<Long> dataIds) {
        return dataDictValueDao.batchDeleteDataDictValue(dataIds) > 0 ?
                CommonResult.ok("批量删除数据字典值成功") : CommonResult.fail("批量删除数据字典值失败");
    }

    @Override
    public CommonResult<List<DataDictValue>> queryValueByDictionaryId(long dictionaryId) {
        return CommonResult.ok("查询数据字典值成功", dataDictValueDao.queryValueByDictionaryId(dictionaryId));
    }
}
