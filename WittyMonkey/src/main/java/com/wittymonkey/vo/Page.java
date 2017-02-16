package com.wittymonkey.vo;

/**
 * Created by neilw on 2017/2/16.
 */
public class Page {
    private Integer totalData;
    private Integer pageSize;
    private Integer totalPage;
    private Integer currPage;

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer curPage) {
        this.currPage = curPage;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
