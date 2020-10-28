package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.CustomerServer;
import com.fb.xujimanage.entity.vo.CustomerServerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服信息表(CustomerServer)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-24 17:08:03
 */
@Repository
public interface CustomerServerDao {


    /**
     * 客服信息列表
     * @param customerServerName
     * @return
     */
    List<CustomerServerVo> getCustomerServerInfoList(@Param("name") String customerServerName);

    /**
     * 删除客服信息
     * @param id
     * @return
     */
    Integer updateById(Integer id);

    /**
     * 新增客服信息
     * @param customerServer
     * @return
     */
    Integer insertCustomerServer(CustomerServer customerServer);

    /**
     * 更新客服信息
     * @param customerServer
     * @return
     */
    Integer updateCustomerServer(CustomerServer customerServer);

    /**
     * 查询客服信息
     * @param id
     * @return
     */
    CustomerServerVo findById(Integer id);

    List<CustomerServer>   findAll(@Param("restaurantId") String restaurantId, @Param("mobile") String mobile);
}