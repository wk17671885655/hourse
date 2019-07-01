package com.k9501.www.until;

public class HouseCondition {
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    //分页
    private Integer page;//页码
    private Integer rows;//页大小
}
