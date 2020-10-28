package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.LeaveWordReplyDao;
import com.fb.xujimanage.service.LeaveWordReplyService;
import com.fb.xujimanage.util.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @program: xujimanage
 * @description: 回复接口实现类
 * @author: chengjie
 * @date: Created in 2020/8/27 10:34
 **/
@Service
public class LeaveWordReplyServiceImpl implements LeaveWordReplyService {

    private LeaveWordReplyDao leaveWordReplyDao;

    public LeaveWordReplyServiceImpl(LeaveWordReplyDao leaveWordReplyDao) {
        this.leaveWordReplyDao = leaveWordReplyDao;
    }

    @Override
    public CommonResult deleteLeaveWordReply(Long id) {
        return leaveWordReplyDao.deleteLeaveWordReply(id) > 0 ?
                CommonResult.ok("删除回复成功") : CommonResult.fail("删除回复失败");
    }
}
