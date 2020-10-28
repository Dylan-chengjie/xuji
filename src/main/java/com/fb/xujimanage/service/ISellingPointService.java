package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.vo.SellingPointResVo;
import com.fb.xujimanage.entity.vo.SellingPointVo;
import com.fb.xujimanage.util.CommonResult;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 20:20
 * @description:就餐信息维护模块->服务特色维护接口
 */
public interface ISellingPointService {

    /**
     * 分页查询服务特色信息
     *
     * @param currentPage：当前页
     * @param pageSize：每页显示的数量
     * @return
     */
    CommonResult<List<SellingPointResVo>> querySellingPointList(Integer currentPage, Integer pageSize, String sellingPointName);

    /**
     * 修改服务特色信息
     *
     * @param sellingPointVo
     * @return
     */
    CommonResult updateSellingPoint(SellingPointVo sellingPointVo);

    /**
     * 新增服务特色信息
     * @param sellingPointVo
     * @return
     */
    CommonResult addSellingPoint(SellingPointVo sellingPointVo);

    /**
     * 根据id删除服务特色信息
     *
     * @param ServiceId
     * @return
     */
    CommonResult deleteSellingPointById(long ServiceId);

}
