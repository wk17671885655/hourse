package com.k9501.www.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.*;
import com.k9501.www.service.DistrictService;
import com.k9501.www.service.HouseService;
import com.k9501.www.service.TypeService;
import com.k9501.www.until.HouseCondition;
import com.k9501.www.until.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/")
public class HousesController {
    @Autowired
    private HouseService houseService;

    //查询未审核信息
    @RequestMapping("getHouseByStatus")
    @ResponseBody
    public Map<String,Object> getAllByPage(String id,Integer page,Integer rows){
        PageInfo<HouseExt> pageInfo = houseService.selectHouseExtByStatus(0, page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    //查询未审核信息
    @RequestMapping("getHouseByStatus2")
    @ResponseBody
    public Map<String,Object> getAllByPage2(String id,Integer page,Integer rows){
        PageInfo<HouseExt> pageInfo = houseService.selectHouseExtByStatus(1, page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
//房屋审核
    @RequestMapping("PassHouse")
    @ResponseBody
    public Map<String,Object> PassHouse(String id){
        int temp = houseService.PassHouse(id);
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }


}
