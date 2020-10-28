package com.fb.xujimanage.entity.dto;


import lombok.Data;

/**
 * @auther Sam.yang
 * @date 2020/8/24
 * @description 分页dto
 */
@Data
public class BaseDto {
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页展示条数
     */
    private Integer pageSize;

    /**
     * 分页参数初始化
     */
    public void initPage(){
        if(pageSize == null){
            pageSize = 10;
        }
        if(currentPage == null){
            currentPage = 1;
        }
        this.currentPage = this.currentPage - 1;
    }

}
