package com.k9501.www.protal.controller;

import com.k9501.www.entity.Users;
import com.k9501.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("checkName")
    @ResponseBody
    public String checkName(String username){
        int r = userService.checkName(username);
        return "{\"result\":"+r+"}";
    }
    @RequestMapping("addUser")
    public String addUser(Users user){
        int r = userService.addUser(user);
        if(r==1){
            return "login";
        }else {
            return "error";
        }
    }
//登录用户
    @RequestMapping("login")
    public String login(String name, String password,Integer isadmin, Model model, HttpSession session){
       //判断是否为管理员
        boolean isAdmin=false;
        if(isadmin==null){//该用户为管理员
            isadmin=0;
        }else {
            isAdmin=true;
        }
        Users user = userService.login(name, password,isadmin);
        if(user==null){
            model.addAttribute("info","用户名密码错误");
          // 继续登录
            return "login";
        }else {
          // 登录保存登入信息
            session.setAttribute("user",user);
//            用户保存时间45秒
//            session.setMaxInactiveInterval(45);
          // 进入登录页面
            if (!isAdmin) {
                return "guanli";
            }else {
                return "redirect:/admin/admin.html";
            }
        }
    }
}
