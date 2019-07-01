package com.k9501.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.House;
import com.k9501.www.entity.HouseCondition2;
import com.k9501.www.entity.HouseExample;
import com.k9501.www.entity.HouseExt;
import com.k9501.www.mapper.HouseMapper;
import com.k9501.www.service.HouseService;

import com.k9501.www.service.TypeService;
import com.k9501.www.until.HouseCondition;
import com.k9501.www.until.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;


    @Override
    public int deleteByPrimaryKey(String id) {

        return houseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(House record) {
        return 0;
    }

    @Override
    public int insertSelective(House record) {
        return 0;
    }

    @Override
    public List<House> selectByExample(HouseExample example) {
        return null;
    }

    @Override
    public House selectByPrimaryKey(Integer id) {
        return null;
    }

    //修改出租房信息
    @Override
    public int updateByPrimaryKeySelective(House record) {
        return houseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(House record) {
        return 0;
    }

    @Override
    public PageInfo<House> getHouse(HouseCondition houseCondition) {
        return null;
    }


    @Override
    public int checkName(String username) {
        return 0;
    }

    @Override
    public int addHouse(House house) {

        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<HouseExample> selectHouseExtByUserId(Integer uid, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<HouseExample> list = houseMapper.selectHouseExtByUserId(uid);
        return new PageInfo<>(list);
    }

    @Override
    public HouseExt getHouse(String id) {
        return houseMapper.getHouse(id);
    }

    @Override
    public int isdel(String id) {
        House house=new House();
        house.setIsdel(1);
        house.setId(id);
        return   houseMapper.updateByPrimaryKeySelective(house);
    }
//根据状态码查询未审核的租房信息
    @Override
    public PageInfo<HouseExt> selectHouseExtByStatus(Integer status, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<HouseExt> list = houseMapper.selectHouseExtByStatus(status);
        PageInfo<HouseExt> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
//审核房屋
    @Override
    public int PassHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
//根据条件查询所有房屋
    @Override
    public PageInfo<HouseExt> selectHouseExtByBrowser(HouseCondition2 houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),5);
        List<HouseExt> list = houseMapper.selectHouseExtByBrowser(houseCondition);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public HouseExt selectHouseExtById(String id) {
        return houseMapper.selectHouseExtById(id);
    }


}
