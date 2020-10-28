package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.CustomerServer;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.dto.CustomerNameDto;
import com.fb.xujimanage.entity.dto.CustomerServerDto;
import com.fb.xujimanage.entity.vo.CustomerServerVo;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客服信息 CustomerServer
 *
 * @author Sam.yang
 * @since 2020-08-24 17:08:04
 */
public interface CustomerServerService {


    /**
     * 客户信息分页查询
     * @param customerNameDto
     * @return
     */
    CommonResult<PageInfo<List<DietTypeVo>>> getCustomerServerInfoList(Integer pageNum, Integer pageSize,CustomerNameDto customerNameDto);



    /**
     * 删除客服信息
     * @param id
     * @return
     */
    Integer deleteCustomerServerById(Integer id);

    /**
     * 添加 /删除客服信息
     * @param customerServerDto
     * @return
     */
    Integer editCustomerServer(CustomerServerDto customerServerDto);

    /**
     * 查询客服信息
     * @param id
     * @return
     */
    CustomerServerVo getCustomerServerById(Integer id);


    List<UserManageSysVo>   findAll(UserManageSys userManageSys);
}