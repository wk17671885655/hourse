package com.k9501.www.service;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.Users;
import com.k9501.www.entity.UsersExample;
import com.k9501.www.until.UserCondition;

import java.util.List;

public interface UserService {
    /*
    * 删除
    */
    int deleteByPrimaryKey(Integer id);
   /*
    * 添加
    */
    int insert(Users record);
    /*
    * 添加
    */
    int insertSelective(Users record);
    /*
    * 查询
    */
    List<Users> selectByExample(UsersExample example);
    /*
    * 查询
    */
    Users selectByPrimaryKey(Integer id);
    /*
    * 更新
    */
    int updateByPrimaryKeySelective(Users record);
    /*
     * 更新
     */
    int updateByPrimaryKey(Users record);
    /*
    * 条件查询
    */
    public PageInfo<Users> getUsers(UserCondition userCondition);
    /*
    * 检查用户名是否存在
    */
    public int checkName(String username);
    /*
    * 添加前台用户
    */
    int addUser(Users users);
    /*
    * 实现登录，并返回对象保存在域中
    */
    public Users login(String username, String password,Integer isadmin) ;
}
