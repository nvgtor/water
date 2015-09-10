package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/9/9.
 */
public class PhoneJson {
    private int pageNumber;
    private int pageSize;
    private int total;
    private int totalPage;
    private List<PhoneBean> rows;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<PhoneBean> getRows() {
        return rows;
    }

    public void setRows(List<PhoneBean> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PhoneJson{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", rows=" + rows +
                '}';
    }
}
