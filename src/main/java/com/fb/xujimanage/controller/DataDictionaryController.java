package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.DataDictionary;
import com.fb.xujimanage.service.DataDictionaryService;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-20 10:37
 * @description:数据字典相关
 * @version:
 */
@Api(description = "数据字典管理")
@RestController
@AuthToken
@RequestMapping("/data/dictionary")
public class DataDictionaryController {

    private DataDictionaryService dataDictionaryService;

    public DataDictionaryController(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @ApiOperation(value = "新增数据字典")
    @PostMapping
    public CommonResult addDataDictionary(@RequestBody @Valid DataDictionary dataDictionary) {
        return dataDictionaryService.addDataDictionary(dataDictionary);
    }

    @ApiOperation(value = "更新数据字典")
    @PutMapping
    public CommonResult updateDataDictionary(@RequestBody @Validated({FirstGroup.class}) DataDictionary dataDictionary) {
        return dataDictionaryService.updateDataDictionary(dataDictionary);
    }

    @ApiOperation(value = "删除数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据字典id", required = true, dataType = "long")
    })
    @DeleteMapping
    public CommonResult deleteDataDictionary(@RequestParam Long id) {
        return dataDictionaryService.deleteDataDictionary(id);
    }

    @ApiOperation(value = "查询数据字典列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据字典名称（支持模糊）", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "数据字典编码", dataType = "string")
    })
    @GetMapping
    public CommonResult<List<DataDictionary>> queryDataDictionary(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "code", required = false) String code
    ) {
        return dataDictionaryService.queryDataDictionary(name, code);
    }
}
