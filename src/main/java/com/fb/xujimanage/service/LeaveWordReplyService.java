package com.fb.xujimanage.service;


import com.fb.xujimanage.util.CommonResult;

/**
 * @author chengjie
 * @date 2020-08-26 19:18
 * @description:回复接口类
 * @version:
 */
public interface LeaveWordReplyService {

    /**
     * 删除回复
     *
     * @param id
     * @return
     */
    CommonResult deleteLeaveWordReply(Long id);
}
