package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.vo.LeaveWordVo;
import com.fb.xujimanage.service.LeaveWordService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-25 21:37
 * @description:角色管理
 * @version:v
 */
@Api(description = "留言管理")
@RestController
@AuthToken
@RequestMapping("/leave/word")
public class LeaveWordController {

    private LeaveWordService leaveWordService;

    public LeaveWordController(LeaveWordService leaveWordService) {
        this.leaveWordService = leaveWordService;
    }

    @ApiOperation(value = "删除角色留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "long")
    })
    @DeleteMapping
    public CommonResult deleteLeaveWord(@RequestParam Long id) {
        return leaveWordService.deleteLeaveWord(id);
    }

    @ApiOperation(value = "分页查询留言列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间戳", dataType = "long"),
            @ApiImplicitParam(name = "endTime", value = "结束时间戳", dataType = "long"),
            @ApiImplicitParam(name = "userOrPhone", value = "发布人或手机号", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "城市", dataType = "string"),
            @ApiImplicitParam(name = "restaurantId", value = "门店id", dataType = "int"),
            @ApiImplicitParam(name = "wordType", value = "留言类型", dataType = "int")
    })
    @GetMapping
    public CommonResult<PageInfo<List<LeaveWordVo>>> pageLeaveWord(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                   @RequestParam(name = "startTime", required = false) Long startTime,
                                                                   @RequestParam(name = "endTime", required = false) Long endTime,
                                                                   @RequestParam(name = "userOrPhone", required = false) String userOrPhone,
                                                                   @RequestParam(name = "city", required = false) String city,
                                                                   @RequestParam(name = "restaurantId", required = false) Integer restaurantId,
                                                                   @RequestParam(name = "wordType", required = false) Integer wordType) {
        return leaveWordService.pageLeaveWord(pageNum, pageSize, startTime != null ? new Date(startTime) : null,
                endTime != null ? new Date(endTime) : null, userOrPhone, city, restaurantId, wordType);
    }
}
