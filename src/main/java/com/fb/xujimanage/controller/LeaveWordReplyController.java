package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.service.LeaveWordReplyService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengjie
 * @date 2020-08-27 20:37
 * @description:回复管理
 * @version:v
 */
@Api(description = "回复管理")
@RestController
@AuthToken
@RequestMapping("/leave/reply")
public class LeaveWordReplyController {

    private LeaveWordReplyService leaveWordReplyService;

    public LeaveWordReplyController(LeaveWordReplyService leaveWordReplyService) {
        this.leaveWordReplyService = leaveWordReplyService;
    }

    @ApiOperation(value = "删除回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "回复id", required = true, dataType = "long")
    })
    @DeleteMapping
    public CommonResult DeleteLeaveWordReply(@RequestParam Long id) {
        return leaveWordReplyService.deleteLeaveWordReply(id);
    }

}
