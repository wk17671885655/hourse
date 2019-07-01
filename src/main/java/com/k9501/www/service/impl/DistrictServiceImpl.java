package com.k9501.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.mapper.DistrictMapper;
import com.k9501.www.mapper.StreetMapper;
import com.k9501.www.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper mapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public List<District> getAllDistrict() {
        return mapper.getAllDistrict();
    }

    @Override
    public PageInfo<District> getAllDistrictByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<District> list = mapper.getAllDistrict();
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int insertSelective(District record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(District record) {
        return mapper.updateByPrimaryKey(record);
    }


    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        System.out.println("id = " + id);
        try {
            streetMapper.deleteByPrimaryKey(id);
            mapper.deleteByPrimaryKey(id);
            return 1;
        } catch (Exception e) {
            System.out.println("异常");
            return 0;
        }
    }

    @Override
    @Transactional
    public int delete(Integer[] id) {
        try {
            streetMapper.deleteStreetsByForeignKey(id);
            mapper.delete(id);
            return 1;
        } catch (Exception e) {
           return 0;
        }
    }
}
