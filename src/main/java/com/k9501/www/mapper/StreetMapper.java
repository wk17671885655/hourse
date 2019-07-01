package com.k9501.www.mapper;

import com.k9501.www.entity.Street;
import com.k9501.www.entity.StreetExample;
import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteStreetByForeignKey(Integer did);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    List<Street> getAllStreet();

    int deleteStreetsByForeignKey(Integer []id);
}