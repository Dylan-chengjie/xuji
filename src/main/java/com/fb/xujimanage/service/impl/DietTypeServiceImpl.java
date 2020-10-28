package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.DietTypeDao;
import com.fb.xujimanage.entity.DietType;
import com.fb.xujimanage.entity.dto.DietTypeDto;
import com.fb.xujimanage.entity.dto.DietTypeQueryDto;
import com.fb.xujimanage.entity.dto.DietTypeUpdateDto;
import com.fb.xujimanage.entity.vo.DietTypeResVo;
import com.fb.xujimanage.entity.vo.DietTypeVo;
import com.fb.xujimanage.entity.vo.ImgCollectVo;
import com.fb.xujimanage.service.DietTypeService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜品类别表(DietType)表服务实现类
 *
 * @author makejava
 * @since 2020-08-28 17:53:50
 */
@Service("dietTypeService")
public class DietTypeServiceImpl implements DietTypeService {
    @Resource
    private DietTypeDao dietTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DietTypeVo queryById(Long id) {
        DietType dietType = this.dietTypeDao.queryById(id);
        DietTypeVo dietTypeVo = new DietTypeVo();
        BeanUtils.copyProperties(dietType, dietTypeVo);
        return dietTypeVo;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DietType> queryAllByLimit(int offset, int limit) {
        return this.dietTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dietType 实例对象
     * @return 实例对象
     */
    @Override
    public DietType insert(DietType dietType) {
        this.dietTypeDao.insert(dietType);
        return dietType;
    }

    /**
     * 修改数据
     *
     * @param dietTypeUpdateDto 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(DietTypeUpdateDto dietTypeUpdateDto) {
        DietType dietType = new DietType();
        BeanUtils.copyProperties(dietTypeUpdateDto,dietType);
        return this.dietTypeDao.update(dietType);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Integer deleteById(Long id) {
        return dietTypeDao.deleteById(id);
    }

    /**
     * 添加菜品分类
     *
     * @param dietTypeDto
     * @return
     */
    @Override
    public Integer add(DietTypeDto dietTypeDto) {
        DietType dietType = new DietType();
        BeanUtils.copyProperties(dietTypeDto, dietType);
        dietType.setId(IdUtil.getUUID());
        dietType.setCreateTime(new Date());
        dietType.setUpdateTime(new Date());
        return dietTypeDao.insert(dietType);
    }

    /**
     * 列表查询
     * @param queryDto
     * @return
     */
    @Override
    public CommonResult<PageInfo<List<DietTypeVo>>> findAll(Integer pageNum, Integer pageSize,DietTypeQueryDto queryDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<DietTypeVo> dietTypeVos =dietTypeDao.findAll(queryDto);
        return  CommonResult.ok("查找菜品分类", new PageInfo<DietTypeVo>(dietTypeVos));

    }


    /**
     *  根据门店查询菜品下拉列表
     * @param restaurantCode
     * @return
     */
    @Override
    public CommonResult findDietType(String restaurantCode){
        List<DietTypeResVo> dietTypes=dietTypeDao.findDietTypeByRestaurantCode(restaurantCode);
        return CommonResult.ok(dietTypes);
    }


    @Override
    @Transactional
    public void  update(DietType diet,String bCode,String sCode ,String bName, String sName){
        //数据拆分
        List<DietType> dietTypes=new ArrayList<>();
        DietType dietType1 = new DietType();
        dietType1.setCode(bCode);
        dietType1.setBigOrSmall(1);
        dietType1.setName(bName);
        DietType dietType2 = new DietType();
        dietType2.setBigOrSmall(0);
        dietType2.setCode(sCode);
        dietType2.setName(sName);
        dietTypes.add(dietType1);
        dietTypes.add(dietType2);
        for ( DietType dietType:dietTypes ) {
            DietType select= dietTypeDao.selectOne(dietType);
            if (select==null){
                dietType.setId(IdUtil.getUUID());
                dietType.setCreateTime(new Date());
                dietType.setRestaurantCode(diet.getRestaurantCode());
                dietType.setRestaurantName(diet.getRestaurantName());
                dietType.setDelFlag(diet.getDelFlag());
                dietType.setIsShow(diet.getIsShow());
                dietTypeDao.insertOne(dietType);
            }else{
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