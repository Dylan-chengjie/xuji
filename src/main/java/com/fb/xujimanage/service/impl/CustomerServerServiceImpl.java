package com.fb.xujimanage.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fb.xujimanage.dao.CustomerServerDao;
import com.fb.xujimanage.dao.UserManageSysDao;
import com.fb.xujimanage.entity.CustomerServer;
import com.fb.xujimanage.entity.UserManageSys;
import com.fb.xujimanage.entity.dto.CustomerNameDto;
import com.fb.xujimanage.entity.dto.CustomerServerDto;
import com.fb.xujimanage.entity.vo.CustomerServerVo;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.service.CustomerServerService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客服信息 CustomerServer
 *
 * @author sam.yang
 * @since 2020-08-24 17:08:04
 */
@Service
public class CustomerServerServiceImpl implements CustomerServerService {

    @Resource
    private CustomerServerDao customerServerDao;

    @Resource
    private UserManageSysDao userManageSysDao;


    @Override
    public CommonResult<PageInfo<List<DietTypeVo>>> getCustomerServerInfoList(Integer pageNum, Integer pageSize,CustomerNameDto customerNameDto) {

        PageHelper.startPage(pageNum, pageSize);
        List<CustomerServerVo> customerServerVos = customerServerDao.getCustomerServerInfoList(customerNameDto.getCustomerServerName());
        return CommonResult.ok("查询客服信息列表成功",new PageInfo<CustomerServerVo>(customerServerVos));
    }

    @Override
    public Integer deleteCustomerServerById(Integer id) {
        return customerServerDao.updateById(id);
    }

    @Override
    public Integer editCustomerServer(CustomerServerDto customerServerDto) {
        CustomerServer customerServer = new CustomerServer();
        BeanUtils.copyProperties(customerServerDto, customerServer);
        Integer count = 0;
        if (ObjectUtils.isEmpty(customerServerDto.getId())) {
            //添加
            customerServer.setId(IdUtil.getUUID());
            count = customerServerDao.insertCustomerServer(customerServer);

        } else {
            //更新
            count = customerServerDao.updateCustomerServer(customerServer);
        }
        return count;
    }

    @Override
    public CustomerServerVo getCustomerServerById(Integer id) {
        return customerServerDao.findById(id);
    }

    @Override
    public List<UserManageSysVo>   findAll(UserManageSys userManageSys){
        return  userManageSysDao.getAllUserUserManageSysInfo(userManageSys);
    }
}