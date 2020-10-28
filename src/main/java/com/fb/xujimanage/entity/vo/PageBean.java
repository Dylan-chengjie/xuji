package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/25 14:32
 * @description:
 */
@Data
@ApiModel(value = "PageBean", description = "分页数据")
public class PageBean implements Serializable {
    /**
     * 当前页
     */
    @ApiModelProperty(value="当前页",name="currentPage")
    private int currentPage;
    /**
     * 每页显示多少条
     */
    @ApiModelProperty(value="每页显示多少条",name="pageSize")
    private int pageSize;
    /**
     * 总记录数
     */
    @ApiModelProperty(value="总记录数",name="total")
    private Integer total;
    /**
     * 本页的数据列表
     */
    @ApiModelProperty(value="菜单id",name="id")
    private List list;
    /**
     * 总页数
     */
    @ApiModelProperty(value="总页数",name="pageCount")
    private int pageCount;
    /**
     * 页码列表的开始索引
     */
    @ApiModelProperty(value="页码列表的开始索引",name="beginPageIndex")
    private int beginPageIndex;
    /**
     * 页码列表的结束索引
     */
    @ApiModelProperty(value="页码列表的结束索引",name="endPageIndex")
    private int endPageIndex;

    /**
     * 只需要接受前4个参数的值，会自动的计算出后3个属性的值。
     *
     * @param currentPage
     * @param pageSize
     * @param total
     * @param recordList
     */
    public PageBean(int currentPage, int pageSize, int total, List recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.list = recordList;

        // 计算pageCount
        pageCount = (total + pageSize - 1) / pageSize;

        // 计算beginPageIndex和endPageIndex
        // 当页码数量不大于10个时，显示所有页码。
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        // 当页码数量大于10个时，显示当前页附近的共10个页码。
        else {
            // 一般情况下显示前4个加当前页加后5个（共10个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;

            // 当前面不足4个页码时，显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面不足5个页码时，显示后10个页码
            else if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1; // 显示时会包含两边的边界，所以要减9.
            }
        }
    }

}
