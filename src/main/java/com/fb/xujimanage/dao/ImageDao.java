package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-14 13:59
 * @description:图片相关
 * @version:
 */
@Repository
public interface ImageDao {

    /**
     * 新增图片
     *
     * @param id
     * @param name
     * @param address
     * @param url
     * @param remarks
     * @param createBy
     * @param updateBy
     * @param sortNum
     * @return
     */
    Integer addImage(@Param("id") long id, @Param("name") String name, @Param("address") String address, @Param("url") String url, @Param("remarks") String remarks,
                     @Param("createBy") String createBy, @Param("updateBy") String updateBy, @Param("sortNum") Integer sortNum);


    /**
     * 根据留言id查询图片
     *
     * @param leavewordId
     * @return
     */
    List<Image> listImageByLeavewordId(@Param("leavewordId") Long leavewordId);

    /**
     * 删除图片
     *
     * @param imgId
     * @return Integer
     */
    Integer deleteImage(@Param("imgId") long imgId);

    /**
     * 批量删除图片
     *
     * @param imgIds
     * @return Integer
     */
    Integer batchDeleteImage(@Param("imgIds") List<Long> imgIds);

    /**
     * 新增
     */
    public int insert(Image image);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(Image image);

    /**
     * Load查询
     */
    public Image load(@Param("id") Long id);
}
