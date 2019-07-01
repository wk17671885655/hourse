package com.k9501.www.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.Users;
import com.k9501.www.service.UserService;
import com.k9501.www.until.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UserService UserService;
    /*分页查询*/
    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(UserCondition userCondition){
        PageInfo<Users> pageInfo = UserService.getUsers(userCondition);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    /*
    * 处理添加请求
    */
    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(Users User){
        int r = UserService.insertSelective(User);
        return "{\"result\":"+r+"}";
    }

    @RequestMapping("deltetUsers")
    @ResponseBody
    public String deltetUsers(Integer id){
        int r = UserService.deleteByPrimaryKey(id);
        return "{\"result\":"+r+"}";
    }

    @RequestMapping("updateUsers")
    @ResponseBody
    public String updateUsers(Users User){
        int r = UserService.updateByPrimaryKeySelective(User);
        return "{\"result\":"+r+"}";
    }


}
