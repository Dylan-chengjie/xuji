package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.vo.EnvironmentalResVo;
import com.fb.xujimanage.entity.vo.EnvironmentalVo;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 10:12
 * @description:就餐信息模块->环境布置维护
 */
public interface IEnvironmentalService {
    /**
     * 分页查询环境布置信息
     *
     * @param currentPage:当前页码
     * @param pageSize:显示数量
     * @return
     */
    CommonResult<List<EnvironmentalResVo>> queryEnvironmentalInfo(Integer currentPage, Integer pageSize, String environmentalName);

    /**
     * 修改环境布置信息
     *
     * @param environmentalVo
     * @return
     */
    CommonResult updateEnvironmentalInfo(EnvironmentalVo environmentalVo);

    /**
     * 新增环境布置
     * @param environmentalVo
     * @return
     */
    CommonResult addEnvironmentalInfo(EnvironmentalVo environmentalVo);


    /**
     * 根据environmentalId删除环境配置信息
     *
     * @param environmentalId
     * @return
     */
    CommonResult deleteEnvironmentalInfoById(long environmentalId);



}
