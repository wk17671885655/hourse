package com.k9501.www.mapper;

import com.k9501.www.entity.Users;
import com.k9501.www.entity.UsersExample;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<Users> getAll();

    int deleteAny(Integer [] ids);
}