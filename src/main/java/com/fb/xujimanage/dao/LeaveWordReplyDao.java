package com.fb.xujimanage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chengjie
 * @date 2020-08-25 13:59
 * @description:留言相关
 * @version:
 */
@Repository
public interface LeaveWordReplyDao {

    /**
     * 删除回复
     *
     * @param id
     * @return
     */
    Integer deleteLeaveWordReply(@Param("id") Long id);

    /**
     * 根据留言id删除回复
     *
     * @param leaveWordId
     * @return
     */
    Integer deleteReplyByWordId(@Param("leaveWordId") Long leaveWordId);
}
