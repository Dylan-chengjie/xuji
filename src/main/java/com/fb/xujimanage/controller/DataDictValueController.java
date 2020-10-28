package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.DataDictValue;
import com.fb.xujimanage.service.DataDictValueService;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengjie
 * @date 2020-08-18 10:37
 * @description:数据字典相关
 * @version:v
 */
@Api(description = "数据字典值管理")
@RestController
@AuthToken
@RequestMapping("/data/value")
public class DataDictValueController {

    private DataDictValueService dataDictValueService;

    public DataDictValueController(DataDictValueService dataDictValueService) {
        this.dataDictValueService = dataDictValueService;
    }

    @ApiOperation(value = "新增数据字典值")
    @PostMapping
    public CommonResult addDataDictionary(@RequestBody @Valid DataDictValue dataDictValue) {
        return dataDictValueService.addDataDictValue(dataDictValue);
    }

    @ApiOperation(value = "更新数据字典值")
    @PutMapping
    public CommonResult updateDataDictValue(@RequestBody @Validated({FirstGroup.class}) DataDictValue dataDictValue) {
        return dataDictValueService.updateDataDictValue(dataDictValue);
    }

    @ApiOperation(value = "批量删除数据字典值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "数据id数组字符串，如1,2,3", required = true, dataType = "string")
    })
    @DeleteMapping
    public CommonResult deleteDataDictValue(@RequestParam String ids) {
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return dataDictValueService.batchDeleteDataDictValue(idList);
    }

    @ApiOperation(value = "根据数据字典id查询数据值列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictionaryId", value = "数据字典id", required = true, dataType = "string")
    })
    @GetMapping
    public CommonResult<List<DataDictValue>> queryValueByDictionaryId(@RequestParam Long dictionaryId) {
        return dataDictValueService.queryValueByDictionaryId(dictionaryId);
    }
}
