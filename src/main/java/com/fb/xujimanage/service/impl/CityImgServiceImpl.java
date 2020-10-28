package com.fb.xujimanage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.dao.CityImgDao;
import com.fb.xujimanage.dao.ImageDao;
import com.fb.xujimanage.entity.CityImg;
import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.dto.CityImgDto;
import com.fb.xujimanage.entity.dto.CityImgPageDto;
import com.fb.xujimanage.entity.vo.CityImgPageVo;
import com.fb.xujimanage.service.CityImgService;
import com.fb.xujimanage.service.FileService;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.IdUtil;
import com.fb.xujimanage.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ObjectUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class CityImgServiceImpl implements CityImgService {
    @Resource
    private CityImgDao cityImgDao;
    @Resource
    private FileService fileService;
    @Resource
    private ImageDao imageDao;

    @Value("${img.server.path}")
    private String imgServerPath;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult update(CityImgDto dto,MultipartFile file) {
        CityImg cityImg = cityImgDao.loadByCityCode(dto.getCityCode());
        String imageId=null;
        if (file!=null){
            String fileName = file.getOriginalFilename();

            //2.获取图片的类型
            String fileType = fileName.substring(fileName.lastIndexOf("."));

            //3.判断是否为图片格式  采用正则表达式
            if (!fileType.matches("^.*(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|bmp|BMP)$")) {
                return CommonResult.fail("图片类型有误");
            }
            CommonResult commonResult = fileService.fileUpload(file,imgServerPath);
            if (commonResult.isOk()){
                if(StringUtil.isNotBlank(dto.getImageId()) && !"null".equals(dto.getImageId())){
                    imageId=dto.getImageId();
                    Image image=new Image();
                    image.setAddress((String) commonResult.getData());
                    image.setName(file.getOriginalFilename());
                    image.setId(Long.valueOf(imageId));
                    imageDao.update(image);
                }else{
                    imageId=String.valueOf(IdUtil.getUUID()) ;
                    imageDao.addImage(Long.valueOf(imageId),file.getOriginalFilename(),(String) commonResult.getData(),null,"城市图片","false_老默",null,null);

                }
            }else {
                return CommonResult.fail(commonResult.getMsg());
            }

        }
        if (cityImg!=null){
            cityImg.setImgId(imageId);
            cityImg.setUpdateBy("false_老默");
            cityImg.setUpdateTime(new Date());
            int update = cityImgDao.update(cityImg);
            if (update>0){
                return CommonResult.ok("修改成功！");
            }
        }

        return CommonResult.fail("操作失败！");

    }

    @Override
    public CommonResult deleteByCityCode(String cityCode) {

        int delete = cityImgDao.delete(cityCode);
        if (delete>0){
            return CommonResult.ok("删除成功");
        }
        return CommonResult.fail("删除失败");
    }

    @Override
    public PageInfo<List<CityImgPageVo>> pageList(CityImgPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<CityImgPageVo> cityImgPageVos = cityImgDao.pageList(dto.getCityName());
        return new PageInfo(cityImgPageVos);
    }

    @Override
    public List<CityImg> allList() {
        return cityImgDao.allList();
    }
}
