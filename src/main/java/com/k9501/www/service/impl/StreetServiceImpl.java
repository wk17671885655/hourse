package com.k9501.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.entity.Street;
import com.k9501.www.entity.StreetExample;
import com.k9501.www.mapper.DistrictMapper;
import com.k9501.www.mapper.StreetMapper;
import com.k9501.www.service.DistrictService;
import com.k9501.www.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper mapper;

    @Override
    public List<Street> getAllStreet() {
        return mapper.getAllStreet();
    }

    @Override
    public PageInfo<Street> getAllStreetById(Integer page, Integer rows) {
            PageHelper.startPage(page,rows);
        List<Street> list = mapper.getAllStreet();
        PageInfo<Street> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insertSelective(Street record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Street record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Street> getStreetByDistrict(Integer districtId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
        List<Street> list = mapper.selectByExample(example);
         return  new PageInfo<>(list);
    }

    @Override
    public Street selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Street> getStreetByDistrict(Integer districtId) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
       return mapper.selectByExample(example);
    }
}
