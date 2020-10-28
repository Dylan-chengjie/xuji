package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.vo.LeaveWordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-25 13:59
 * @description:留言相关
 * @version:
 */
@Repository
public interface LeaveWordDao {

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    Integer deleteLeaveWord(@Param("id") Long id);

    /**
     * 删除留言雨图片关系
     *
     * @param leaveWordId
     * @return
     */
    Integer deleteLeaveWordImgRelate(@Param("leaveWordId") Long leaveWordId);

    /**
     * 分页查询留言列表
     *
     * @param startTime
     * @param endTime
     * @param userOrPhone
     * @param city
     * @param restaurantId
     * @return
     */
    List<LeaveWordVo> pageLeaveWord(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("userOrPhone") String userOrPhone,
                                    @Param("city") String city, @Param("restaurantId") Integer restaurantId, @Param("wordType") Integer wordType);
}
