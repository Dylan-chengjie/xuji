package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.DataDictValueDao;
import com.fb.xujimanage.dao.MenuDao;
import com.fb.xujimanage.dao.RestaurantDao;
import com.fb.xujimanage.dao.RestaurantMenuRelateDao;
import com.fb.xujimanage.entity.Menu;
import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.RestaurantMenuRelate;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.IMenuMaintainService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.TokenUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 13:37
 * @description:就餐信息维护模块->菜单维护接口实现类
 */
@Log4j
@Service
public class MenuMaintainServiceImpl implements IMenuMaintainService {

    private MenuDao menuDao;
    private DataDictValueDao dataDictValueDao;
    private RestaurantDao restaurantDao;
    private RestaurantMenuRelateDao restaurantMenuRelateDao;

    @Autowired
    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Autowired
    public void setRestaurantMenuRelateDao(RestaurantMenuRelateDao restaurantMenuRelateDao) {
        this.restaurantMenuRelateDao = restaurantMenuRelateDao;
    }

    @Autowired
    public void setDataDictValueService(DataDictValueDao dataDictValueDao) {
        this.dataDictValueDao = dataDictValueDao;
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /**
     * 分页查询菜单维护信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public CommonResult<List<MenuMaintainResVo>> queryMenuInfo(Integer currentPage, Integer pageSize, String menuName) {
        List<MenuMaintainResVo> list = new ArrayList<>();
        List<Menu> menus = menuDao.loadAll(menuName, (currentPage - 1) * pageSize, pageSize);
        int total = menuDao.getMenuTotal(menuName);
        if (menus == null) {
            log.error("menus is null");
            return CommonResult.fail("查询菜单维护信息失败:menus is null");
        }
        if (menus.isEmpty()) {
            return CommonResult.ok(new PageBean(currentPage, pageSize, total, list));
        }
        for (Menu menu : menus) {
            MenuMaintainResVo menuMaintainResVo = new MenuMaintainResVo();
            menuMaintainResVo.setId(menu.getId());
            menuMaintainResVo.setContent(menu.getContent());
            menuMaintainResVo.setName(menu.getName());
            menuMaintainResVo.setPrice(menu.getPrice());
            menuMaintainResVo.setPersonDicvalueid(menu.getPersonDicvalueid());
            menuMaintainResVo.setTasteDicvalueid(menu.getTasteDicvalueid());
            menuMaintainResVo.setBudgetId(menu.getBudgetId());
            List<RestaurantInfoResVo> restaurantInfoResVos = new ArrayList<>();
            for (Restaurant restaurant : menu.getRestaurants()) {
                RestaurantInfoResVo restaurantInfoResVo = new RestaurantInfoResVo();
                restaurantInfoResVo.setAddress(restaurant.getAddress());
                restaurantInfoResVo.setId(restaurant.getId());
                restaurantInfoResVo.setArea(restaurant.getArea());
                restaurantInfoResVo.setCity(restaurant.getCity());
                restaurantInfoResVo.setRestaurantName(restaurant.getRestaurantName());
                restaurantInfoResVos.add(restaurantInfoResVo);
            }
            menuMaintainResVo.setRestaurantInfoResVos(restaurantInfoResVos);
            menuMaintainResVo.setTaste(String.valueOf(menu.getTasteDicvalueid()));
            menuMaintainResVo.setPeopleCounting(String.valueOf(menu.getPersonDicvalueid()));
            list.add(menuMaintainResVo);
        }
        PageBean pageBean = new PageBean(currentPage, pageSize, total, list);
        return CommonResult.ok(pageBean);
    }

    /**
     * 删除菜单信息
     *
     * @param menuId：菜单id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteMenuInfo(Long menuId) {
        Menu menu = menuDao.load(menuId);
        if (menu == null) {
            log.error("删除数据失败，数据不存在");
            return CommonResult.fail("删除数据失败，数据不存在");
        }
        menu.setUpdateTime(new Date());
        menu.setUpdateBy(TokenUtils.getUserNum());
        menu.setDelFlag(1);
        Integer updateId = menuDao.update(menu);
        if (updateId == null) {
            return CommonResult.fail();
        }
        return updateId > 0 ? CommonResult.ok() : CommonResult.fail();
    }

    /**
     * 添加菜单信息
     *
     * @param addMenuMaintainVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult insertMenuInfo(AddMenuMaintainVo addMenuMaintainVo) {
        if (!CollectionUtils.isEmpty(menuDao.loadAllByName(addMenuMaintainVo.getName(), addMenuMaintainVo.getRestaurantId()))) {
            return CommonResult.fail("添加菜单信息失败:门店下菜单名称不能重复");
        }
        Long[] restaurantIds = addMenuMaintainVo.getRestaurantId();
        Long personDicvalueid = addMenuMaintainVo.getPersonDicvalueid();
        Long tasteDicvalueid = addMenuMaintainVo.getTasteDicvalueid();
        String budgetId = addMenuMaintainVo.getBudgetId();
        String userNum = TokenUtils.getUserNum();
        Menu menu = new Menu();
        menu.setId(IdUtil.getUUID());
        menu.setName(addMenuMaintainVo.getName());
        menu.setContent(addMenuMaintainVo.getContent());
        menu.setCreateBy(userNum);
        menu.setCreateTime(new Date());
        menu.setDelFlag(0);
        menu.setUpdateBy(userNum);
        menu.setUpdateTime(new Date());
        menu.setName(addMenuMaintainVo.getName());
        menu.setPrice(addMenuMaintainVo.getPrice());
        menu.setPersonDicvalueid(personDicvalueid);
        menu.setTasteDicvalueid(tasteDicvalueid);
        menu.setBudgetId(budgetId);
        int insertId = menuDao.insert(menu);
        if (insertId <= 0) {
            log.error("Failed to insert data");
            return CommonResult.fail("添加菜单信息失败:Failed to insert data");
        }
        if (restaurantIds == null && restaurantIds.length == 0) {
            log.error("门店数组为null");
            return CommonResult.fail("门店数组为null");
        }
        List<RestaurantMenuRelate> restaurantMenuRelateList = new ArrayList<>(restaurantIds.length);
        RestaurantMenuRelate restaurantMenu = new RestaurantMenuRelate();
        restaurantMenu.setMenuId(menu.getId());
        for (Long id : restaurantIds) {
            RestaurantMenuRelate restaurantMenuRelate = new RestaurantMenuRelate();
            restaurantMenuRelate.setMenuId(menu.getId());
            restaurantMenuRelate.setRestaurantId(id);
            restaurantMenuRelate.setId(IdUtil.getUUID());
            restaurantMenuRelateList.add(restaurantMenuRelate);
        }
        int whether = restaurantMenuRelateDao.insertRestaurantMenuRelateList(restaurantMenuRelateList);
        if (whether <= 0) {
            log.error("添加菜单信息失败:Failed to insert data");
            return CommonResult.fail();
        }
        return whether <= 0 ? CommonResult.fail() : CommonResult.ok();
    }

    /**
     * 修改菜单信息
     *
     * @param updateMenuMaintainVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateMenuInfo(UpdateMenuMaintainVo updateMenuMaintainVo) {
        Long[] restaurantId = updateMenuMaintainVo.getRestaurantId();
        String userNum = TokenUtils.getUserNum();
        Menu menu = menuDao.load(updateMenuMaintainVo.getId());
        if (menu == null) {
            log.error("修改数据失败，数据不存在");
            return CommonResult.fail("修改数据失败，数据不存在");
        }
        menu.setPersonDicvalueid(updateMenuMaintainVo.getPersonDicvalueid());
        menu.setContent(updateMenuMaintainVo.getContent());
        menu.setUpdateBy(userNum);
        menu.setUpdateTime(new Date());
        String name = updateMenuMaintainVo.getName();
        if (StringUtils.isNotEmpty(name)) {
            menu.setName(updateMenuMaintainVo.getName());
            if (!CollectionUtils.isEmpty(menuDao.loadAllByName(name, restaurantId))) {
                return CommonResult.fail("更新菜单信息失败:门店下菜单名称不能重复");
            }
        }
        menu.setPrice(updateMenuMaintainVo.getPrice());
        menu.setPersonDicvalueid(updateMenuMaintainVo.getPersonDicvalueid());
        menu.setTasteDicvalueid(updateMenuMaintainVo.getTasteDicvalueid());
        menu.setBudgetId(updateMenuMaintainVo.getBudgetId());
        int updateId = menuDao.update(menu);
        if (updateId <= 0) {
            log.error("菜单信息更新失败");
            return CommonResult.fail("菜单信息更新失败");
        }
        int deleteId = restaurantMenuRelateDao.deleteByMenuId(menu.getId());
        if (deleteId <= 0) {
            log.error("deleteByMenuId删除失败");
            return CommonResult.fail("deleteByMenuId删除失败");
        }
        int whether = 0;
        if (restaurantId != null) {
            if (restaurantId.length > 0) {
                List<RestaurantMenuRelate> restaurantMenuRelateList = new ArrayList<>(restaurantId.length);
                RestaurantMenuRelate restaurantMenu = new RestaurantMenuRelate();
                restaurantMenu.setMenuId(menu.getId());
                for (Long id : restaurantId) {
                    RestaurantMenuRelate restaurantMenuRelate = new RestaurantMenuRelate();
                    restaurantMenuRelate.setMenuId(menu.getId());
                    restaurantMenuRelate.setRestaurantId(id);
                    restaurantMenuRelateList.add(restaurantMenuRelate);
                }
                whether = restaurantMenuRelateDao.insertRestaurantMenuRelateList(restaurantMenuRelateList);
            }
        }

        return whether <= 0 ? CommonResult.fail() : CommonResult.ok();
    }

    /**
     * 查询门店信息
     *
     * @return
     */
    @Override
    public CommonResult<List<RestaurantInfoResVo>> queryRestaurantInfo(RestaurantInfoVo restaurantInfoVo) {
        List<Restaurant> restaurants = restaurantDao.loadAll(restaurantInfoVo);
        if (restaurants == null) {
            log.error("门店信息查询失败");
            return CommonResult.fail("门店信息查询失败");
        }
        List<RestaurantInfoResVo> restaurantInfoResVoList = new LinkedList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantInfoResVo restaurantInfoResVo = new RestaurantInfoResVo();
            restaurantInfoResVo.setCity(restaurant.getCity());
            restaurantInfoResVo.setArea(restaurant.getArea());
            restaurantInfoResVo.setRestaurantName(restaurant.getRestaurantName());
            restaurantInfoResVo.setId(restaurant.getId());
            restaurantInfoResVo.setAddress(restaurant.getAddress());
            restaurantInfoResVoList.add(restaurantInfoResVo);
        }
        return CommonResult.ok(restaurantInfoResVoList);
    }
}
