package com.k9501.www.mapper;

import com.k9501.www.entity.District;
import com.k9501.www.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int delete(Integer[] id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    //删除全部
    List<District> getAllDistrict();

}