package com.sunbufu.common;

import java.util.ArrayList;

/**
 * 包含分页信息的列表
 * @param <E>
 */
public class PageList<E> extends ArrayList<E> {

    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "PageList{" +
                "pageInfo=" + pageInfo.toString() +
                ", list=" + super.toString() +
                '}';
    }

    //------GETTER/SETTER


    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
