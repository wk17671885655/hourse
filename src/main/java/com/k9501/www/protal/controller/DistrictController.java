package com.k9501.www.protal.controller;

import com.k9501.www.entity.District;
import com.k9501.www.entity.Street;
import com.k9501.www.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;


    @RequestMapping("getdistrictAll")
    @ResponseBody
    public List<District> getStreetByDistrict(){
        return  districtService.getAllDistrict();
    }

}
