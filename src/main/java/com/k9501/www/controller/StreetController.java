package com.k9501.www.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.Street;
import com.k9501.www.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("getAllStreet")
    @ResponseBody     //分页查询
    public Map<String,Object> getAllStreet(Integer page,Integer rows){
        PageInfo<Street> pageInfo = streetService.getAllStreetById(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;   //total 总条数 ，rows 当前页行数据
    }

    @RequestMapping("getStreetByDistrict")
    @ResponseBody
    public Map<String,Object> getStreetByDistrict(Integer districtId,Integer page,Integer rows){
        if(page==null){
            page=1;//默认第一页
        }
        PageInfo<Street> pageInfo = streetService.getStreetByDistrict(districtId,page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        System.out.println(pageInfo);
        return map;
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(Street street){
        int result = streetService.insertSelective(street);
        return "{\"result\":"+result+" }";  //添加成功返回1，失败返回0
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(Street street){
        int result = streetService.updateByPrimaryKeySelective(street);
        return "{\"result\":"+result+" }";
    }

    @RequestMapping("getStreetById")
    @ResponseBody
    public Street getStreetById(Integer id){
        Street street = streetService.selectByPrimaryKey(id);
        return street;
    }

    @RequestMapping("deleteStreetById")
    @ResponseBody
    public String  deleteStreetById(Integer id){
        int result = streetService.deleteByPrimaryKey(id);
        return "{\"result\":"+result+" }";
    }



}
