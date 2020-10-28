package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.ImageDao;
import com.fb.xujimanage.dao.LeaveWordDao;
import com.fb.xujimanage.dao.LeaveWordReplyDao;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.vo.LeaveWordVo;
import com.fb.xujimanage.service.LeaveWordService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: xujimanage
 * @description: 留言接口实现类
 * @author: chengjie
 * @date: Created in 2020/8/25 17:10
 **/
@Service
public class LeaveWordServiceImpl implements LeaveWordService {

    private LeaveWordDao leaveWordDao;
    private ImageDao imageDao;
    private LeaveWordReplyDao leaveWordReplyDao;

    public LeaveWordServiceImpl(LeaveWordDao leaveWordDao, ImageDao imageDao, LeaveWordReplyDao leaveWordReplyDao) {
        this.leaveWordDao = leaveWordDao;
        this.imageDao = imageDao;
        this.leaveWordReplyDao = leaveWordReplyDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteLeaveWord(Long id) {
        if (leaveWordDao.deleteLeaveWord(id) > 0) {
            leaveWordReplyDao.deleteReplyByWordId(id);
            List<Image> images = imageDao.listImageByLeavewordId(id);
            if (!CollectionUtils.isEmpty(images)) {
                List<Long> imageId = images.stream().map(Image::getId).collect(Collectors.toList());
                if (imageDao.batchDeleteImage(imageId) < 1) {
                    CommonResult.throwVerifyException("删除留言失败");
                }
                if (leaveWordDao.deleteLeaveWordImgRelate(id) < 1) {
                    CommonResult.throwVerifyException("删除留言失败");
                }
            }
        }
        return CommonResult.ok("删除留言成功");
    }

    @Override
    public CommonResult<PageInfo<List<LeaveWordVo>>> pageLeaveWord(Integer pageNum, Integer pageSize, Date startTime, Date endTime,
                                                                   String userOrPhone, String city, Integer restaurantId, Integer wordType) {
        PageHelper.startPage(pageNum, pageSize);
        List<LeaveWordVo> leaveWordVos = leaveWordDao.pageLeaveWord(startTime, endTime, userOrPhone, city, restaurantId, wordType);
        leaveWordVos.forEach(leaveWordVo -> {
            List<Image> images = imageDao.listImageByLeavewordId(leaveWordVo.getId());
            leaveWordVo.setImages(images);
        });
        return CommonResult.ok("查询留言列表成功", new PageInfo<>(leaveWordVos));
    }
}
