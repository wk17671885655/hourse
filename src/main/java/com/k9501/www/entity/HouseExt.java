package com.k9501.www.entity;
//
public class HouseExt extends House {
    private String tname;//房屋类型
    private String sname;//所属街道
    private String dname;//所属区域
//    两种方法，第一种直接在类里面添加字段，第二种方法新建一个类
    private Integer districtId;  //添加字段区域id

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
