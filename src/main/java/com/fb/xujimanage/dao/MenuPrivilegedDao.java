package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chengjie
 * @version 1.0
 * @date 2020/8/20 19:33
 * @description: 菜单权限
 */
@Repository
public interface MenuPrivilegedDao {
    /**
     * 新增菜单栏
     *
     * @param id
     * @param nameCn
     * @param nameEn
     * @param privilegedCode
     * @param parentCode
     * @param iconImgId
     * @param menuStatus
     * @param sortNum
     * @param menuType
     * @param menuPath
     * @param createBy
     * @param updateBy
     * @return
     */
    Integer addMenuPrivileged(@Param("id") Long id, @Param("nameCn") String nameCn, @Param("nameEn") String nameEn,
                              @Param("privilegedCode") String privilegedCode, @Param("parentCode") String parentCode, @Param("iconImgId") Long iconImgId,
                              @Param("menuStatus") Integer menuStatus, @Param("sortNum") Integer sortNum, @Param("menuType") Integer menuType,
                              @Param("menuPath") String menuPath, @Param("createBy") String createBy, @Param("updateBy") String updateBy);

    /**
     * 更新菜单栏
     *
     * @param id
     * @param iconImgId
     * @param nameCn
     * @param nameEn
     * @param menuStatus
     * @param sortNum
     * @param updateBy
     * @return
     */
    Integer updateMenuPrivileged(@Param("id") Long id, @Param("iconImgId") Long iconImgId, @Param("nameCn") String nameCn, @Param("nameEn") String nameEn,
                                 @Param("menuStatus") Integer menuStatus, @Param("sortNum") Integer sortNum, @Param("updateBy") String updateBy);

    /**
     * 删除菜单栏
     *
     * @param ids
     * @return
     */
    Integer batchDeleteMenuPrivileged(@Param("ids") List<Long> ids);

    /**
     * 查询菜单栏
     *
     * @return
     */
    List<MenuPrivilegedVo> listMenuPrivileged(@Param("menuName") String menuName, @Param("menuType") Integer menuType, @Param("parentCode") String parentCode);

    /**
     * 根据id查询iconImgId
     *
     * @return
     */
    List<Long> listMenuIconImgIdById(@Param("ids") List<Long> ids);

    /**
     * 根据角色id获取菜单列表
     *
     * @param privilegedCode
     * @return Integer
     */
    Integer countPrivilegedByCode(@Param("privilegedCode") String privilegedCode);

    /**
     * 根据角色id获取菜单列表
     *
     * @param roleIds
     * @return List<MenuPrivileged>
     */
    List<MenuPrivilegedVo> queryRolePrivilegedByRoleId(@Param("roleIds") List<Long> roleIds);

    List<MenuPrivilegedVo> listAllMenuPrivileged(@Param("menuName") String menuName);
}
