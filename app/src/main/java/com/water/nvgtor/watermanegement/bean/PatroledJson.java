package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/8/28.
 */
public class PatroledJson {
    private int pageNumber;
    private int pageSize;
    private int total;
    private int totalPage;
    private List<PatroledRows> rows;

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

    public List<PatroledRows> getRows() {
        return rows;
    }

    public void setRows(List<PatroledRows> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PatroledJson{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", rows=" + rows +
                '}';
    }
}
