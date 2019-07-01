package com.k9501.www.protal.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.Street;
import com.k9501.www.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/")
public class StreetsController {
    @Autowired
    private StreetService streetService;


    @RequestMapping("getStreetByDistrict")
    @ResponseBody
    public List<Street> getStreetByDistrict(Integer districtId){
        return  streetService.getStreetByDistrict(districtId);
    }

}
