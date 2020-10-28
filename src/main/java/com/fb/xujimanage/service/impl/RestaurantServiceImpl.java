package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.ImageDao;
import com.fb.xujimanage.dao.RestaurantDao;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.dto.RestaurantImgDto;
import com.fb.xujimanage.entity.dto.RestaurantPageDto;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.service.RestaurantService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 徐记电子门店信息表(Restaurant)表服务实现类
 *
 * @author sam.yang
 * @since 2020-08-25 15:29:17
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Resource
    private RestaurantDao restaurantDao;
    @Resource
    private FileService fileService;
    @Resource
    private ImageDao imageDao;

    @Value("${img.server.path}")
    private String imgServerPath;

    @Override
    public List<RestaurantsVo> getRestaurantList(Integer type, String city, String restaurantName) {
        List<RestaurantsVo> resultVoList = restaurantDao.selectRestaurantListBy(type, city, restaurantName);
        //使用递归算法建树型结构集合
        List<RestaurantsVo> treeAddressList = new ArrayList<RestaurantsVo>();
        for (RestaurantsVo restaurantVo : resultVoList) {
            //抽取父类作为主节点
            if ("-1".equals(restaurantVo.getParentId())) {
                treeAddressList.add(findChildren(resultVoList, restaurantVo));
            }
        }
        return treeAddressList;
    }

    @Override
    public List<RestaurantVo> selectRestaurantByStore(String orgType) {
        return restaurantDao.selectRestaurantListOrgType(orgType);
    }

    @Override
    public CommonResult updateStatusById(Long id, Integer status) {
        int i = restaurantDao.updateStatusById(id, status);
        if (i > 0) {
            return CommonResult.ok("修改成功");
        }
        return CommonResult.fail("修改失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSyncRestaurantList(List<RestaurantDetailsVo> restaurantDetailsVos) {
        List<RestaurantVo> restaurantVos = restaurantDao.selectRestaurantList(null, null);
        if (!CollectionUtils.isEmpty(restaurantVos)) {
            List<Long> modifyFlags = restaurantVos.stream().filter(restaurantVo -> Constants.ONE.equals(restaurantVo.getModifyFlag())).
                    map(RestaurantVo::getId).collect(Collectors.toList());
            restaurantDao.deleteByNotFlag(modifyFlags);
            List<RestaurantDetailsVo> restaurantDetails = new ArrayList<>();
            restaurantDetailsVos.forEach(restaurantDetailsVo -> {
                if (modifyFlags.contains(restaurantDetailsVo.getOrgId())) {
                    restaurantDetails.add(restaurantDetailsVo);
                }
            });
            restaurantDetailsVos.removeAll(restaurantDetails);
            List<RestaurantDetailsVo> restaurantVoList = restaurantDetailsVos.parallelStream().map(restaurantDetailsVo -> {
                RestaurantVo restaurant = restaurantVos.stream().filter(restaurantVo ->
                        restaurantVo.getId() == restaurantDetailsVo.getOrgId()).findFirst().orElse(null);
                if (null != restaurant) {
                    restaurantDetailsVo.setDelFlag(restaurant.getDelFlag());
                    restaurantDetailsVo.setImgId(restaurant.getImgId());
                    restaurantDetailsVo.setVrUrl(restaurant.getVrUrl());
                    restaurantDetailsVo.setContactWay(restaurant.getContactWay());
                }
                return restaurantDetailsVo;
            }).collect(Collectors.toList());
            restaurantDao.batchAddRestaurant(restaurantVoList);
        } else {
            restaurantDao.batchAddRestaurant(restaurantDetailsVos);
        }
    }

    @Override
    public PageInfo<RestaurantPageVo> pageListRestaurantImg(RestaurantPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<RestaurantPageVo> list = restaurantDao.pageListRestaurantImg(dto.getCityCode(), dto.getRestaurantName(), dto.getStatus());
        return new PageInfo<>(list);
    }

    @Override
    public CommonResult updateRestaurantImgById(RestaurantImgDto dto, MultipartFile file) {

        if (file != null) {
            CommonResult commonResult = fileService.fileUpload(file, imgServerPath, 1024 * 1024);
            if (commonResult.isOk()) {
                if (StringUtil.isNotBlank(dto.getImgId()) && !"null".equals(dto.getImgId())) {
                    Image image = new Image();
                    image.setAddress((String) commonResult.getData());
                    image.setName(file.getOriginalFilename());
                    image.setId(Long.valueOf(dto.getImgId()));
                    imageDao.update(image);
                } else {
                    long imageId = IdUtil.getUUID();
                    dto.setImgId(String.valueOf(imageId));
                    imageDao.addImage(imageId, file.getOriginalFilename(), (String) commonResult.getData(), null, "城市图片", "false_老默", null, null);
                }

            } else {
                return CommonResult.fail("修改失败");
            }
        }
        Integer modifyFlag = 0;
        Restaurant load = restaurantDao.load(dto.getId());
        if (load == null) {
            CommonResult.ok("修改失败");
        }
        if (load.getModifyFlag() != null && load.getModifyFlag() == 1) {
            modifyFlag = 1;
        }
        if (StringUtil.isNotBlank(dto.getCityCode()) && !dto.getCityCode().equals(load.getCity())) {
            modifyFlag = 1;
        }
        if (StringUtil.isNotBlank(dto.getAddress()) && !load.getAddress().equals(dto.getAddress())) {
            modifyFlag = 1;
        }
        restaurantDao.updateImgIdById(dto.getId(), StringUtil.isBlank(dto.getVrUrl()) ? null : dto.getVrUrl(), Long.valueOf(dto.getImgId()), dto.getCityCode(), dto.getAddress(), modifyFlag, dto.getContactWay());
        return CommonResult.ok("修改成功");
    }

    @Override
    public List<RestaurantCitySelectVo> getRestaurantCitySelect(Integer status) {
        return restaurantDao.getRestaurantCitySelect(status);
    }

    /**
     * 递归查找子节点
     *
     * @param addRestaurantVo 集合
     * @param restaurantVo    父类数据
     * @return
     */
    protected RestaurantsVo findChildren(List<RestaurantsVo> addRestaurantVo, RestaurantsVo restaurantVo) {
        for (RestaurantsVo childRestaurantVo : addRestaurantVo) {
            //父类的ID等于子类的父类ID
            if (restaurantVo.getOrgId().equals(childRestaurantVo.getParentId())) {
                if (restaurantVo.getChildren() == null) {
                    restaurantVo.setChildren(new ArrayList<RestaurantsVo>());
                }
                restaurantVo.getChildren().add(findChildren(addRestaurantVo, childRestaurantVo));
            }
        }
        return restaurantVo;
    }
}