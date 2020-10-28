package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.ImageDao;
import com.fb.xujimanage.dao.MenuPrivilegedDao;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.MenuPrivileged;
import com.fb.xujimanage.entity.vo.MenuPrivilegedVo;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.MenuPrivilegedService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: xujimanage
 * @description: 菜单权限相关
 * @author: chengjie
 * @date: Created in 2020/8/21 18:54
 **/
@Service
public class MenuPrivilegedServiceImpl implements MenuPrivilegedService {
    @Value("${img.server.path}")
    private String imgServerPath;
    private FileService fileService;
    private MenuPrivilegedDao menuPrivilegedDao;
    private ImageDao imageDao;

    public MenuPrivilegedServiceImpl(FileService fileService, MenuPrivilegedDao menuPrivilegedDao, ImageDao imageDao) {
        this.fileService = fileService;
        this.menuPrivilegedDao = menuPrivilegedDao;
        this.imageDao = imageDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addMenuPrivileged(MultipartFile file, String nameCn, String nameEn, String privilegedCode, String parentCode,
                                          Integer menuStatus, Integer sortNum, Integer menuType, String menuPath, String createBy, String updateBy) {
        if (menuPrivilegedDao.countPrivilegedByCode(privilegedCode) > 0) {
            return CommonResult.fail("privilegedCode重复");
        }
        if (menuStatus != null && (menuStatus < 0 || menuStatus > 1)) {
            return CommonResult.fail("menuType不在取值范围内");
        }
        if (menuType != null && (menuType < 0 || menuType > 1)) {
            return CommonResult.fail("menuType不在取值范围内");
        }
        String fileName = file.getOriginalFilename();
        CommonResult commonResult = fileService.fileUpload(file, imgServerPath);
        if (commonResult.isOk()) {
            String urlPath = (String) commonResult.getData();
            long imageId = IdUtil.getUUID();
            imageDao.addImage(imageId, fileName, urlPath, null, nameCn, createBy, updateBy, sortNum);
            Integer result = menuPrivilegedDao.addMenuPrivileged(IdUtil.getUUID(), nameCn, nameEn, privilegedCode,
                    parentCode, imageId, menuStatus, sortNum, menuType, menuPath, createBy, updateBy);
            if (result < 1) {
                CommonResult.throwVerifyException("添加菜单权限失败");
            }
            return CommonResult.ok("添加菜单权限成功");
        }
        return CommonResult.ok("添加菜单权限失败");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateMenuPrivileged(MultipartFile file, Long id, Long iconImgId, String nameCn, String nameEn,
                                             Integer menuStatus, Integer sortNum, String updateBy) {
        Long imageId = null;
        if (file != null) {
            if (iconImgId == null) {
                return CommonResult.fail("更新图片时iconImgId不能为空");
            }
            if (imageDao.deleteImage(iconImgId) < 1) {
                return CommonResult.fail("更新菜单栏失败:更新图片时，传入的iconImgId有误");
            }
            String fileName = file.getOriginalFilename();
            CommonResult commonResult = fileService.fileUpload(file, imgServerPath);
            if (commonResult.isOk()) {
                String urlPath = (String) commonResult.getData();
                imageId = IdUtil.getUUID();
                Integer addImageResult = imageDao.addImage(imageId, fileName, urlPath, null, nameCn, updateBy, updateBy, sortNum);
                if (addImageResult < 1) {
                    CommonResult.throwVerifyException("更新菜单栏失败");
                }
            }
        }
        Integer privilegedResult = menuPrivilegedDao.updateMenuPrivileged(id, imageId, nameCn, nameEn, menuStatus, sortNum, updateBy);
        if (privilegedResult < 1) {
            CommonResult.throwVerifyException("更新菜单栏失败");
        }
        return CommonResult.ok("更新菜单栏成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult batchDeleteMenuPrivileged(List<Long> ids) {
        List<Long> iconImgIds = menuPrivilegedDao.listMenuIconImgIdById(ids);
        if (CollectionUtils.isEmpty(iconImgIds)) {
            return CommonResult.fail("菜单栏不存在");
        }
        if (menuPrivilegedDao.batchDeleteMenuPrivileged(ids) < 1) {
            return CommonResult.fail("删除菜单权限失败");
        }
        if (imageDao.batchDeleteImage(iconImgIds) < 1) {
            CommonResult.throwVerifyException("删除菜单权限失败");
        }
        return CommonResult.ok("删除菜单权限成功");
    }

    @Override
    public CommonResult<List<MenuPrivilegedVo>> listMenuPrivileged(String menuName) {
//        List<MenuPrivilegedVo> parentMenus = menuPrivilegedDao.listMenuPrivileged(menuName, 0, null);
//        parentMenus.forEach(parentMenu -> {
//            Image image = imageDao.load(parentMenu.getIconImgId());
//            parentMenu.setAddress(image != null ? image.getAddress() : null);
//            List<MenuPrivilegedVo> firstMenus = menuPrivilegedDao.listMenuPrivileged(null, 1, parentMenu.getPrivilegedCode());
//            firstMenus.forEach(firstMenu -> {
//                Image img = imageDao.load(firstMenu.getIconImgId());
//                firstMenu.setAddress(img != null ? img.getAddress() : null);
//                List<MenuPrivilegedVo> secondMenus = menuPrivilegedDao.listMenuPrivileged(null, 1, firstMenu.getPrivilegedCode());
//                secondMenus.forEach(secondMenu -> {
//                    Image imge = imageDao.load(secondMenu.getIconImgId());
//                    secondMenu.setAddress(imge != null ? imge.getAddress() : null);
//                });
//                firstMenu.setMenuPrivilegedVos(secondMenus);
//            });
//            parentMenu.setMenuPrivilegedVos(firstMenus);
//        });
//        return CommonResult.ok("查询菜单权限成功", parentMenus);
        List<MenuPrivilegedVo> menuPrivilegedVos = menuPrivilegedDao.listAllMenuPrivileged(menuName);
        List<MenuPrivilegedVo> list = this.buildByRecursive(menuPrivilegedVos);
        return CommonResult.ok("查询菜单权限成功", list);
    }

    @Override
    public CommonResult<List<MenuPrivilegedVo>> listMenuByRoleId(Long roleId) {
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        return CommonResult.ok("根据角色id查询菜单栏列表成功", menuPrivilegedDao.queryRolePrivilegedByRoleId(roleIdList));
    }


    public List<MenuPrivilegedVo> buildByRecursive(List<MenuPrivilegedVo> treeNodes) {
        List<MenuPrivilegedVo> trees = new ArrayList<>();
        for (MenuPrivilegedVo treeNode : treeNodes) {
            if ("".equals(treeNode.getParentCode())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子菜单
     *
     * @param treeNodes
     * @return
     */
    public static MenuPrivilegedVo findChildren(MenuPrivilegedVo treeNode, List<MenuPrivilegedVo> treeNodes) {
        treeNode.setMenuPrivilegedVos(new ArrayList<>());
        for (MenuPrivilegedVo it : treeNodes) {
            if (treeNode.getPrivilegedCode().equals(it.getParentCode())) {
                if (treeNode.getMenuPrivilegedVos() == null) {
                    treeNode.setMenuPrivilegedVos(new ArrayList<>());
                }
                treeNode.getMenuPrivilegedVos().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}
