package com.k9501.www.service;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.House;
import com.k9501.www.entity.HouseCondition2;
import com.k9501.www.entity.HouseExample;
import com.k9501.www.entity.HouseExt;
import com.k9501.www.until.HouseCondition;
import com.k9501.www.until.UserCondition;

import java.util.List;

public interface HouseService {
 /*
  * 删除
  */
 int deleteByPrimaryKey(String id);
 /*
  * 添加
  */
 int insert(House record);
 /*
  * 添加
  */
 int insertSelective(House record);
 /*
  * 查询
  */
 List<House> selectByExample(HouseExample example);
 /*
  * 查询
  */
 House selectByPrimaryKey(Integer id);
 /*
  * 更新
  */
 int updateByPrimaryKeySelective(House record);
 /*
  * 更新
  */
 int updateByPrimaryKey(House record);
 /*
  * 条件查询
  */
 public PageInfo<House> getHouse(HouseCondition houseCondition);
 /*
  * 检查用户名是否存在
  */
 public int checkName(String username);
 /*
  * 添加前台用户
  */
 int addHouse(House users);
//根据UserID查询带状态房屋分页
 PageInfo<HouseExample> selectHouseExtByUserId(Integer uid,Integer page,Integer rows);
//通过ID号查询房屋信息，带区域信息
  HouseExt getHouse(String id);
 //逻辑删除  isdelete 改为 1
 int  isdel(String id);
 //通过状态码查询出租房信息  0代表未审核   1代表已审核
 PageInfo<HouseExt> selectHouseExtByStatus(Integer status,Integer page,Integer rows);
 //审核房屋
 int PassHouse(String id);
 //查询出租房+分页
 PageInfo<HouseExt> selectHouseExtByBrowser(HouseCondition2 houseCondition);
 //根据id 查看出租房信息
 HouseExt selectHouseExtById(String id);
}
