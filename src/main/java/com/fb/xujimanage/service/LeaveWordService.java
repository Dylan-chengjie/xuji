package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.vo.LeaveWordVo;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-26 19:18
 * @description:留言接口类
 * @version:
 */
public interface LeaveWordService {

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    CommonResult deleteLeaveWord(Long id);

    /**
     * 分页查询留言列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonResult<PageInfo<List<LeaveWordVo>>> pageLeaveWord(Integer pageNum, Integer pageSize, Date startTime, Date endTime,
                                                            String userOrPhone, String city, Integer restaurantId, Integer wordType);
}
