package com.k9501.www.service;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;

import java.util.List;

public interface DistrictService {
    //查询全部
    List<District> getAllDistrict();
    //查询分页
    public PageInfo<District> getAllDistrictByPage(Integer page,Integer rows);
    //添加
    int insertSelective(District record);
    //修改
    int updateByPrimaryKey(District record);
    /*
    * 删除单条
    */
    int deleteByPrimaryKey(Integer id);
    /*
    * 批量删除
    */
    int delete(Integer[] id);
}
