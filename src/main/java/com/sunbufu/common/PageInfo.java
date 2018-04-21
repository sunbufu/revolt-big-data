package com.sunbufu.common;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author sunbufu
 */
public class PageInfo implements Serializable {

    /**当前页码*/
    private Integer pageNum;
    /**页面大小*/
    private Integer pageSize;
    /**总页数*/
    private Integer pageCount;
    /**记录总条数*/
    private Integer totalCount;

    public PageInfo(){}

    public PageInfo(Integer pageNum, Integer pageSize, Integer totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public PageInfo(Integer pageNum, Integer pageCount) {
        this.pageNum = pageNum;
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", totalCount=" + totalCount +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
