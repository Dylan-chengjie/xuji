package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.util.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-21 19:18
 * @description:菜单权限接口类
 * @version:
 */
public interface MenuPrivilegedService {

    /**
     * 新增菜单栏
     *
     * @param file
     * @param nameCn
     * @param nameEn
     * @param privilegedCode
     * @param parentCode
     * @param menuStatus
     * @param sortNum
     * @param menuType
     * @param menuPath
     * @param createBy
     * @param updateBy
     * @return
     */
    CommonResult addMenuPrivileged(MultipartFile file, String nameCn, String nameEn, String privilegedCode, String parentCode,
                                   Integer menuStatus, Integer sortNum, Integer menuType, String menuPath, String createBy, String updateBy);

    /**
     * 更新菜单栏
     *
     * @param file
     * @param id
     * @param iconImgId
     * @param nameCn
     * @param nameEn
     * @param menuStatus
     * @param sortNum
     * @param updateBy
     * @return
     */
    CommonResult updateMenuPrivileged(MultipartFile file, Long id, Long iconImgId, String nameCn, String nameEn,
                                      Integer menuStatus, Integer sortNum, String updateBy);

    /**
     * 删除菜单栏
     *
     * @param ids
     * @return
     */
    CommonResult batchDeleteMenuPrivileged(List<Long> ids);

    /**
     * 查询菜单栏
     *
     * @param menuName
     * @return
     */
    CommonResult<List<MenuPrivilegedVo>> listMenuPrivileged(String menuName);

    /**
     * 根据角色查询菜单栏列表
     *
     * @param roleId
     * @return
     */
    CommonResult<List<MenuPrivilegedVo>> listMenuByRoleId(Long roleId);
}
