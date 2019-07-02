package com.k9501.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.Users;
import com.k9501.www.entity.UsersExample;
import com.k9501.www.mapper.UsersMapper;
import com.k9501.www.service.UserService;
import com.k9501.www.until.MD5Utils;
import com.k9501.www.until.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Users record) {
        return 0;
    }

    @Override
    public int insertSelective(Users record) {
        return 0;
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Users record) {

        return 0;
    }

    @Override
    public PageInfo<Users> getUsers(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //管理员账号
        criteria.andIsadminEqualTo(1);
        if(userCondition.getTelephone()!=null){
            System.out.println(userCondition.getTelephone());
            criteria.andTelephoneLike("%"+userCondition.getTelephone()+"%");
        }
        if(userCondition.getAgeFrom()!=null){
           criteria.andAgeGreaterThan(userCondition.getAgeFrom());
        }
        if(userCondition.getAgeTo()!=null){
           criteria.andAgeLessThan(userCondition.getAgeTo());
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int checkName(String username) {
        UsersExample example=new UsersExample();
//        添加查询条件
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(0);
            criteria.andNameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(example);
        if(users.size()!=0){
            return 1;
        }
        return 0;
    }

    @Override
    public int addUser(Users users) {
        //  普通用户
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        int r = usersMapper.insertSelective(users);
        return r;
    }

    @Override
    public Users login(String username, String password,Integer isadmin) {
        //添加搜索条件
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(isadmin);
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        //执行搜索
        List<Users> users = usersMapper.selectByExample(usersExample);
        if(users.size()!=0){
            return users.get(0);
        }
        return null;
    }


}
