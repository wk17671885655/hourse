package com.k9501.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.entity.Type;
import com.k9501.www.entity.TypeExample;
import com.k9501.www.mapper.DistrictMapper;
import com.k9501.www.mapper.StreetMapper;
import com.k9501.www.mapper.TypeMapper;
import com.k9501.www.service.DistrictService;
import com.k9501.www.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Type record) {
        return 0;
    }

    @Override
    public int insertSelective(Type record) {
        return typeMapper.insertSelective(record);
    }

    @Override
    public List<Type> selectByExample(TypeExample example) {
        return null;
    }

    @Override
    public Type selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Type record) {
        return typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Type record) {
        return 0;
    }

    @Override
    public PageInfo<Type> getAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Type> list = typeMapper.getAll();
        System.out.println(list);
        PageInfo<Type> pageInfo= new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int deleteAny(Integer[] ids) {
        return typeMapper.deleteAny(ids);
    }

    @Override
    public List<Type> getTypes() {
        return typeMapper.selectByExample(new TypeExample());
    }

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

}
