package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.*;
import com.fb.xujimanage.entity.DietType;
import com.fb.xujimanage.entity.RestaurantMenuItemRelate;
import com.fb.xujimanage.entity.copy.MenuItemsCopy;
import com.fb.xujimanage.entity.copy.MenultemCopy;
import com.fb.xujimanage.entity.copy.SetMealCopy;
import com.fb.xujimanage.entity.copy.SetMealDetailCopy;
import com.fb.xujimanage.entity.dto.TcConsume;
import com.fb.xujimanage.entity.dto.TcInfo;
import com.fb.xujimanage.entity.dto.XFBMInfo;
import com.fb.xujimanage.entity.vo.MenuItemVo;
import com.fb.xujimanage.entity.vo.RestaurantVo;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.IMenuitemCopyService;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 11:13
 * @description:就餐信息维护模块->菜品维护接口实现类
 */
@Log4j
@Service
public class MenuitemCopyServiceImpl implements IMenuitemCopyService {

    @Value("${img.server.path}")
    private String imgServerPath;
    @Value("${video.server.path}")
    private String videoServerPath;
    @Autowired
    private MenuItemDao menuItemDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private RestaurantMenuItemRelateDao restaurantMenuItemRelateDao;


    @Autowired
    private SetMealDao setMealDao;
    @Autowired
    private SetMealDetailDao setMealDetailDao;

    @Autowired
    private DietTypeDao dietTypeDao;


    /**
     * 更新菜品估清数据
     *
     * @param menuItemVos
     * @return
     */
    @Override
    @Transactional
    public int updatMenuItem(List<MenuItemVo> menuItemVos) {
        //抽取非备用数据

            List<MenuItemsCopy> update=menuItemVos.parallelStream().map(menuItemVo -> {
                MenuItemsCopy copy=new MenuItemsCopy();
                // BeanUtils.copyProperties(menuItemVo,copy);
                copy.setHaveKind(menuItemVo.getHaveKind());
                copy.setId(menuItemVo.getId());
                copy.setQuantity(menuItemVo.getQuantity());
                copy.setRestaurantCode(menuItemVo.getRestaurantCode());
                return copy;
            }).collect(Collectors.toList());
            return  menuItemDao.updateMenuItem(update);

    }


    /**
     * 拷贝菜品信息
     *
     * @param xfbmInfo 正品要跟新的菜品信息
     */
    @Override
    public void updateOneMenultemCopy(XFBMInfo xfbmInfo,RestaurantVo restaurantVo) {
        MenultemCopy menultemCopy = menuItemDao.selectOneById(xfbmInfo.getEat_XFBMID());
        MenultemCopy copy = CopyMenultem(menultemCopy, xfbmInfo,restaurantVo);
        if (menultemCopy != null) {
            menuItemDao.updateOne(copy);
        } else {
            menuItemDao.insertOne(copy);
        }

    }

    /**
     * 拷贝套餐信息
     *
     * @param tcInfo 正品要更新的套餐信息
     */
    @Override
    @Transactional
    public void updateOneSetMealCopy(TcInfo tcInfo, String rId) {
        SetMealCopy setMealCopy = setMealDao.selectOneById(Long.parseLong(tcInfo.getEat_TcAutoid()));
        SetMealCopy copy = CopyMenuSetMeal(setMealCopy, tcInfo, rId);
        if (setMealCopy != null) {
            setMealDao.updateOne(copy);
        } else {
            setMealDao.insertOne(copy);
        }


    }


    /**
     * 套餐明细表
     *
     * @param tcConsume 正品套餐明细表
     */
    @Override
    @Transactional
    public void updateOneSetMealDetailCopy(TcConsume tcConsume) {
        SetMealDetailCopy setMealDetailCopy = setMealDetailDao.selectOneById(tcConsume.getEat_TcAutoid());
        SetMealDetailCopy copy = CopySetMealDetail(setMealDetailCopy, tcConsume);
        if (setMealDetailCopy != null) {
            setMealDetailDao.updateOne(copy);

        } else {
            setMealDetailDao.insertOne(copy);
        }
    }

    /**
     * 拷贝套餐明细
     *
     * @param setMealDetailCopy 套餐明细
     * @param tcConsume         修改套餐明细
     */
    public SetMealDetailCopy CopySetMealDetail(SetMealDetailCopy setMealDetailCopy, TcConsume tcConsume) {
        if (setMealDetailCopy != null) {
            setMealDetailCopy.setSetMealId(tcConsume.getEat_TcAutoid());//套餐ID
            setMealDetailCopy.setDetailType(tcConsume.getEat_KindName());//类别
            setMealDetailCopy.setDietId(tcConsume.getEat_XFBMID());//菜品ID
            setMealDetailCopy.setDietCode(tcConsume.getEat_XFCode());//菜品编码
            setMealDetailCopy.setDetailQuantity(tcConsume.getEat_Number());//数量
            setMealDetailCopy.setAddPrice(tcConsume.getEat_AddMoney());//加价
            setMealDetailCopy.setDefaultSelect(Integer.parseInt(tcConsume.getSelectYn()));//默认选择
            return setMealDetailCopy;
        } else {
            SetMealDetailCopy copy = new SetMealDetailCopy();
            copy.setSetMealId(tcConsume.getEat_TcAutoid());//套餐ID
            copy.setDetailType(tcConsume.getEat_KindName());//类别
            copy.setDietId(tcConsume.getEat_XFBMID());//菜品ID
            copy.setDietCode(tcConsume.getEat_XFCode());//菜品编码
            copy.setDetailQuantity(tcConsume.getEat_Number());//数量
            copy.setAddPrice(tcConsume.getEat_AddMoney());//加价
            copy.setDefaultSelect(Integer.parseInt(tcConsume.getSelectYn()));//默认选择
            return copy;
        }

    }


    /**
     * 拷贝setMeal
     *
     * @param setMealCopy 套餐信息
     * @param tcInfo      正品的套餐数据
     * @return 套餐信息
     */
    public SetMealCopy CopyMenuSetMeal(SetMealCopy setMealCopy, TcInfo tcInfo, String rId) {

        if (setMealCopy != null) {
            setMealCopy.setSetId(tcInfo.getEat_TcAutoid());//套餐ID
            setMealCopy.setDietId(tcInfo.getEat_XFBMID());//菜品ID
            setMealCopy.setSetCode(tcInfo.getEat_TcCode());//套餐编码
            setMealCopy.setSetName(tcInfo.getEat_TcName());//套餐名称
            setMealCopy.setStartDate(tcInfo.getEat_Bdate());//开始日期
            setMealCopy.setEndDate(tcInfo.getEat_Edate());//结束日期
            setMealCopy.setRestaurantId(rId);//门店
            setMealCopy.setUpdateTime(new Date());
            int status = tcInfo.getTcStatus();
            if (status == 1) {
                setMealCopy.setDelFlag(Integer.valueOf(0));//有效
            } else {
                setMealCopy.setDelFlag(Integer.valueOf(1));//删除 停用
            }
            return setMealCopy;
        } else {
            SetMealCopy copy = new SetMealCopy();
            copy.setSetId(tcInfo.getEat_TcAutoid());//套餐ID
            copy.setDietId(tcInfo.getEat_XFBMID());//菜品ID
            copy.setSetCode(tcInfo.getEat_TcCode());//套餐编码
            copy.setSetName(tcInfo.getEat_TcName());//套餐名称
            copy.setStartDate(tcInfo.getEat_Bdate());//开始日期
            copy.setEndDate(tcInfo.getEat_Edate());//结束日期
            copy.setRestaurantId(rId);//门店
            copy.setUpdateTime(new Date());
            int status = tcInfo.getTcStatus();
            if (status == 1) {
                copy.setDelFlag(Integer.valueOf(0));//有效
            } else {
                copy.setDelFlag(Integer.valueOf(1));//删除 停用
            }
            return copy;
        }

    }


    /**
     * 拷贝菜品信息
     *
     * @param menultemCopy 菜品信息
     * @param xfbmInfo     正品的菜品信息
     * @return 菜品信息
     */
    public MenultemCopy CopyMenultem(MenultemCopy menultemCopy, XFBMInfo xfbmInfo,RestaurantVo restaurantVo) {

        if (menultemCopy != null) {
            menultemCopy.setId(xfbmInfo.getEat_XFBMID());//菜品ID
            menultemCopy.setCode(xfbmInfo.getEat_XFCode());//菜品编码
            menultemCopy.setName(xfbmInfo.getEat_XFName());//Eat_XFSize
            menultemCopy.setUnit(xfbmInfo.getEat_XFSize());//单位
            menultemCopy.setPrice(new BigDecimal(xfbmInfo.getEat_XFPrice()));//销售单价
            menultemCopy.setMemberPrice(new BigDecimal(xfbmInfo.getEat_ParPrice()));//销售会员单价
            menultemCopy.setBigTypeCode(xfbmInfo.getEat_Code1());//大类编码
            menultemCopy.setBigTypeName(xfbmInfo.getEat_Name1());//大类名称
            menultemCopy.setSmallTypeCode(xfbmInfo.getEat_Code2());//小类编码
            menultemCopy.setSmallTypeName(xfbmInfo.getEat_Name2());//小类名称
            menultemCopy.setType(xfbmInfo.getXFKind());//菜品类型
            menultemCopy.setUpdateTime(new Date());
            menultemCopy.setRestaurantCode(restaurantVo.getCode());
            menultemCopy.setRestaurantName(restaurantVo.getRestaurantName());
            if (xfbmInfo.getXFStatus() == 1) {
                menultemCopy.setDelFlag(0);
            } else {
                menultemCopy.setDelFlag(1);
            }
            return menultemCopy;
        } else {
            MenultemCopy copy = new MenultemCopy();

            copy.setId(xfbmInfo.getEat_XFBMID());//菜品ID
            copy.setCode(xfbmInfo.getEat_XFCode());//菜品编码
            copy.setName(xfbmInfo.getEat_XFName());//Eat_XFSize
            copy.setUnit(xfbmInfo.getEat_XFSize());//单位
            copy.setPrice(new BigDecimal(xfbmInfo.getEat_XFPrice()));//销售单价
            copy.setMemberPrice(new BigDecimal(xfbmInfo.getEat_ParPrice()));//销售会员单价
            copy.setBigTypeCode(xfbmInfo.getEat_Code1());//大类编码
            copy.setBigTypeName(xfbmInfo.getEat_Name1());//大类名称
            copy.setSmallTypeCode(xfbmInfo.getEat_Code2());//小类编码
            copy.setSmallTypeName(xfbmInfo.getEat_Name2());//小类名称
            copy.setType(xfbmInfo.getXFKind());//菜品类型
            copy.setCreateTime(new Date());
            copy.setMemberProType(0);//会员默认为否
            copy.setRestaurantCode(restaurantVo.getCode());
            copy.setRestaurantName(restaurantVo.getRestaurantName());
            copy.setHaveKind(3);//默认库存为3
            if (xfbmInfo.getXFStatus() == 1) {
                copy.setDelFlag(0);
            } else {
                copy.setDelFlag(1);
            }
            return copy;
        }

    }


    //菜品导数据优化
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sourceMenuItemTask(RestaurantVo restaurantVo, List<XFBMInfo> xfbmInfos) {
        try {
            if (!xfbmInfos.isEmpty()) {
                List<String> ids = xfbmInfos.parallelStream().filter(Objects::nonNull)
                        .map(xfbmInfo -> xfbmInfo.getEat_XFBMID()).filter(Objects::nonNull)
                        .collect(Collectors.toList());

                //菜品关系表
                /* List<RestaurantMenuItemRelate> restaurantMenuItemRelates = xfbmInfos.parallelStream().map(xfbmInfo -> {
                    //封装菜品关系表
                    RestaurantMenuItemRelate restaurantMenuItemRelate = new RestaurantMenuItemRelate();
                    restaurantMenuItemRelate.setId(IdUtil.getUUID());//设置主键
                    restaurantMenuItemRelate.setMenuItemId(xfbmInfo.getEat_XFBMID());//彩品ID
                    restaurantMenuItemRelate.setRestaurantId(restaurantVo.getId());//门店ID
                    return restaurantMenuItemRelate;
                }).collect(Collectors.toList());
                //关系表
               if (!restaurantMenuItemRelates.isEmpty()) {
                    restaurantMenuItemRelateDao.deletByRestaurantId(restaurantVo.getId());
                    restaurantMenuItemRelateDao.insertRestaurantMenuItemRelateList(restaurantMenuItemRelates);

                }*/

                //菜品信息单条更新数据
              /* for (XFBMInfo xfbmInfo : xfbmInfos) {
                    if (StringUtil.isNotBlank(xfbmInfo.getEat_XFBMID())) {
                        //跟新菜品信息
                        updateOneMenultemCopy(xfbmInfo);

                    }
                }*/

                //菜品信息批量更新
                //抽取数据库已有的数据 在原有的数据基础上替换最新的
                List<MenultemCopy> selectMenultemCopy = menuItemDao.selectMenuItemByRestaurantCode(restaurantVo.getCode());
                List<MenultemCopy> updateSetMealCopy = new ArrayList<>();
                for (MenultemCopy menultemCopy : selectMenultemCopy) {
                    for (XFBMInfo xfbmInfo : xfbmInfos) {
                        if (menultemCopy.getId().equals(xfbmInfo.getEat_XFBMID())) {
                            updateSetMealCopy.add(CopyMenultem(menultemCopy, xfbmInfo,restaurantVo));

                        }

                    }
                }
                //抽取数据库没有的一部分数据封装成对象
                List<String> mIds = selectMenultemCopy.parallelStream().filter(Objects::nonNull)
                        .map(menultemCopy -> menultemCopy.getId()).filter(Objects::nonNull)
                        .collect(Collectors.toList());
                List<MenultemCopy> newMenultemCopys = xfbmInfos.parallelStream().map(xfbmInfo -> {
                    MenultemCopy menultemCopy = null;
                    if (!mIds.contains(xfbmInfo.getEat_XFBMID())) {
                        menultemCopy = CopyMenultem(null, xfbmInfo,restaurantVo);
                    }
                    return menultemCopy;
                }).filter(menultemCopy -> Objects.nonNull(menultemCopy)).collect(Collectors.toList());

                //集合取并集
                newMenultemCopys.addAll(updateSetMealCopy);
                //集合去掉空
                newMenultemCopys.parallelStream().filter(newMenultemCopy -> Objects.nonNull(newMenultemCopy));
                if (!newMenultemCopys.isEmpty()) {
                    List<String> mid = newMenultemCopys.parallelStream().map(menultemCopy -> menultemCopy.getId()).collect(Collectors.toList());
                    menuItemDao.deleteMenuItemByRestaurantCode(restaurantVo.getCode());
                    menuItemDao.insertMenuItemCopy(newMenultemCopys);
                }

               for (XFBMInfo xfbmInfo : xfbmInfos) {
                    if (StringUtil.isNotBlank(xfbmInfo.getEat_XFBMID())) {
                        //diet_type 大类小类加标识符 统一备份
                        DietType dietType = new DietType();
                        dietType.setRestaurantCode(restaurantVo.getCode());
                        dietType.setRestaurantName(restaurantVo.getRestaurantName());
                        int status = xfbmInfo.getXFStatus();
                        if (status == 1) {//有效
                            dietType.setIsShow(1);//展示
                            dietType.setDelFlag(0);//有效
                        } else {
                            dietType.setIsShow(0);
                            dietType.setDelFlag(1);
                        }
                        update(dietType, xfbmInfo.getEat_Code1(), xfbmInfo.getEat_Code2(), xfbmInfo.getEat_Name1(), xfbmInfo.getEat_Name2());

                    }
                }


                //SetMealCopy 套餐 套餐性情
                List<SetMealCopy> setMealCopies = new ArrayList<>();
                List<SetMealDetailCopy> setMealDetailCopies = new ArrayList<>();
                for (XFBMInfo xfbmInfo : xfbmInfos) {
                    List<TcInfo> tcInfos = xfbmInfo.getTcInfoList();
                    if (!tcInfos.isEmpty()) {
                        for (TcInfo tcInfo : tcInfos) {
                            setMealCopies.add(CopyMenuSetMeal(null, tcInfo, String.valueOf(restaurantVo.getId())));
                            List<TcConsume> tcConsumes = tcInfo.getTcConsumeList();
                            if (!tcConsumes.isEmpty()) {
                                for (TcConsume tcConsume : tcConsumes) {
                                    setMealDetailCopies.add(CopySetMealDetail1(String.valueOf(restaurantVo.getId()), tcConsume));
                                }
                            }
                        }
                    }
                }

                if (!setMealCopies.isEmpty()) {
                    List<String> setId = setMealCopies.parallelStream().map(n -> n.getSetId()).collect(Collectors.toList());
                    setMealDao.delSetMeal(setId);
                    setMealDao.insertSetMeal(setMealCopies);
                }

                if (!setMealDetailCopies.isEmpty()) {
                    List<String> setMealIds = setMealDetailCopies.parallelStream().map(n -> n.getSetMealId()).collect(Collectors.toList());
                    setMealDetailDao.delSetMealDetail(setMealIds);
                    setMealDetailDao.insertMealDetail(setMealDetailCopies);
                }

                log.info("门店 restaurantVo：数据同步完成" + restaurantVo.getId() + restaurantVo.getRestaurantName());

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BuilderException("数据异常");
        }


    }


    public SetMealDetailCopy CopySetMealDetail1(String id, TcConsume tcConsume) {
        SetMealDetailCopy copy = new SetMealDetailCopy();
        copy.setSetMealId(tcConsume.getEat_TcAutoid());//套餐ID
        copy.setDetailType(tcConsume.getEat_KindName());//类别
        copy.setDietId(tcConsume.getEat_XFBMID());//菜品ID
        copy.setDietCode(tcConsume.getEat_XFCode());//菜品编码
        copy.setDetailQuantity(tcConsume.getEat_Number());//数量
        copy.setAddPrice(tcConsume.getEat_AddMoney());//加价
        copy.setDefaultSelect(Integer.parseInt(tcConsume.getSelectYn()));//默认选择
        copy.setRestaurantId(id);
        copy.setUpdateTime(new Date());
        return copy;
    }


    public void update(DietType diet, String bCode, String sCode, String bName, String sName) {
        //数据拆分
        List<DietType> dietTypes = new ArrayList<>();
        DietType dietType1 = new DietType();
        dietType1.setCode(bCode);
        dietType1.setBigOrSmall(1);
        dietType1.setRestaurantCode(diet.getRestaurantCode());
        dietType1.setName(bName);
        DietType dietType2 = new DietType();
        dietType2.setBigOrSmall(0);
        dietType2.setCode(sCode);
        dietType2.setName(sName);
        dietType2.setRestaurantCode(diet.getRestaurantCode());
        dietTypes.add(dietType1);
        dietTypes.add(dietType2);
        for (DietType dietType : dietTypes) {
            DietType select = dietTypeDao.selectOne(dietType);
            if (select == null) {
                dietType.setId(IdUtil.getUUID());
                dietType.setCreateTime(new Date());
                dietType.setRestaurantCode(diet.getRestaurantCode());
                dietType.setRestaurantName(diet.getRestaurantName());
                dietType.setDelFlag(diet.getDelFlag());
                dietType.setIsShow(diet.getIsShow());
                dietTypeDao.insertOne(dietType);
            } else {
                select.setRestaurantCode(diet.getRestaurantCode());
                select.setRestaurantName(diet.getRestaurantName());
                select.setDelFlag(diet.getDelFlag());
                dietType.setIsShow(diet.getIsShow());
                dietType.setUpdateTime(new Date());
                dietTypeDao.updateOne(select);
            }
        }

    }
}

