package com.k9501.www.mapper;

import com.k9501.www.entity.Type;
import com.k9501.www.entity.TypeExample;
import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> getAll();

    int deleteAny(Integer [] ids);
}