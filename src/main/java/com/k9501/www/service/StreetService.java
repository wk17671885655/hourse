package com.k9501.www.service;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.entity.Street;

import java.util.List;
import java.util.Map;

public interface StreetService {
    List<Street> getAllStreet();

    PageInfo<Street> getAllStreetById(Integer page,Integer rows);

    /*
     *删除街道,根据区域id；
     */
    int deleteByPrimaryKey(Integer id);

   /*
   *添加街道
   */
   int insertSelective(Street record);

   /*
   * 更新
   */
   int updateByPrimaryKeySelective(Street record);

   /*
   * 查看街道
   */
   public PageInfo<Street> getStreetByDistrict(Integer districtId, Integer page,Integer rows);
   /*
   * 根据id查街道
   */
   Street selectByPrimaryKey(Integer id);
   //根据区域id查询所有街道
    public List<Street> getStreetByDistrict(Integer districtId);
}
