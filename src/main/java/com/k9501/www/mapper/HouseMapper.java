package com.k9501.www.mapper;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.House;
import com.k9501.www.entity.HouseCondition2;
import com.k9501.www.entity.HouseExample;
import com.k9501.www.entity.HouseExt;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //根据id查询租房信息
    List<HouseExample> selectHouseExtByUserId(Integer uid);
    //查询出租房信息，带区域id
    HouseExt getHouse(String id);
    //根据状态查询出租房信息
    List<HouseExt> selectHouseExtByStatus(Integer status);
    //条件查询出租房
    List<HouseExt> selectHouseExtByBrowser(HouseCondition2 houseCondition2);
    //根据id 查询单条出租房信息
    HouseExt selectHouseExtById(String id);
}