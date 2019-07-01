package com.k9501.www.service;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.entity.Type;
import com.k9501.www.entity.TypeExample;

import java.util.List;

public interface TypeService {
    /*
    *删除
    */
    int deleteByPrimaryKey(Integer id);
  /*
   * 添加
   */
    int insert(Type record);
   /*
   * 添加
   */
    int insertSelective(Type record);
   /*
   * 条件搜索
   */
    List<Type> selectByExample(TypeExample example);
   /*
   * id搜索
   */
    Type selectByPrimaryKey(Integer id);
    /*
    * 修改
    */
    int updateByPrimaryKeySelective(Type record);
    /*
     * 修改
     */
    int updateByPrimaryKey(Type record);
    /*
    * 分页
    */
    PageInfo<Type> getAllByPage(Integer page,Integer rows);
    /*
     * 查询全部
     */
    public List<Type> getAll();
   /*
   *  批量删除
    */
    int deleteAny(Integer [] ids);
    /*
     * 查询全部
     */
    public List<Type> getTypes();
}
