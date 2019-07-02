package com.k9501.www.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.District;
import com.k9501.www.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")//与jsp所在页面文件保持一致
public class abcController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("getAllBypage")//返回json数据  获取区域分页信息
    @ResponseBody
    public Map<String,Object>  getAll(Integer page,Integer rows){
        PageInfo<District> pageInfo = districtService.getAllDistrictByPage(page, rows);
        Map<String,Object> map=new HashMap<>();
    map.put("rows",pageInfo.getList());
    map.put("total",pageInfo.getTotal());
        return map;
    }

    @RequestMapping("getAll1")
    @ResponseBody
    public List<District>  getAll1(){
        List<District> list = districtService.getAllDistrict();
        return list;
    }

    @RequestMapping("addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int result = districtService.insertSelective(district);
        return "{\"result\":"+result+"}";
    }
    //返回json数据
    @RequestMapping("updateDistrict")
    @ResponseBody
    public  String updateDistrict(District district){
        int result = districtService.updateByPrimaryKey(district);
        return "{\"result\":"+result+"}";//修改数据1 代表成功，2代表失败
    }

    @RequestMapping("delete")
    @ResponseBody
     //给删除区域添加了事务
    public String delete(String ids){
        String[] arys = ids.split(",");
        Integer [] id=new Integer[arys.length];
        //删除多条区域下所有的街道
        for (int i = 0; i < arys.length; i++) {
             id[i]=Integer.parseInt(arys[i]);
        }
        for (int i = 0; i <id.length ; i++) {
            System.out.println(id[i]);
        }
        int result = districtService.delete(id);
        return "{\"result\":"+result+"}";
    }


//  删除单条
    @RequestMapping("deleteSingle")
    @ResponseBody
    public String deleteSingle(Integer id){
        int result = districtService.deleteByPrimaryKey(id);
        return "{\"result\":"+result+"}";
    }

}
