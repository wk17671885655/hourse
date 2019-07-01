package com.k9501.www.protal.controller;

import com.github.pagehelper.PageInfo;
import com.k9501.www.entity.*;
import com.k9501.www.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;


//添加租房信息
    @RequestMapping("addHouse")
    public String addUser(House house, HttpSession session, @RequestParam(value = "pfile",required = false) MultipartFile file) throws IOException {
        //将文件保存在服务器中  G:\\images
         // 获取文件名  xxx.jpg
        String fname = file.getOriginalFilename();
//        截取文件类型  .jpg
        String expName = fname.substring(fname.lastIndexOf("."));
        String uuid= UUID.randomUUID().toString();
        String afterPath=new SimpleDateFormat("yyyy\\MM\\dd\\HH\\mm\\ss").format(new Date());
//        以年月日为文件隔离，uuid为文件名称
//        最终保存路径
        String saveName=afterPath+"\\"+uuid+expName;
        System.out.println("saveName = " + saveName);
        File fineFile=new File("G:\\images\\",afterPath);
        System.out.println("fineFile = " + fineFile);
        if (!fineFile.exists()){
            fineFile.mkdirs();
        }
//        保存
        file.transferTo(new File("G:\\images\\",saveName));
        //保存数据库的记录  house已经接收部分表单数据

        //保存数据库的记录  house已经接收部分表单数据
        //设置编号
        house.setId(System.currentTimeMillis()+"");
//        设置文件审核状态
        house.setIspass(0);
        house.setIsdel(0);
        Users user = (Users) session.getAttribute("user");
        house.setUserId(user.getId());

//        保存文件路径
        house.setPath(saveName);
        int r = houseService.addHouse(house);
        if(r==1){
//            注册成功跳转管理页面
            return "guanli";
        }else {
            fineFile.delete();
            return "error";
        }
    }

    @RequestMapping("deleteHouse")
    @ResponseBody
    @Transactional
    public String delete(String id){
        int result = houseService.deleteByPrimaryKey(id);
        return "{\"result\":"+result+"}";
    }
//获取所有的户型和区域跳转注册页面
    @RequestMapping("goFaBu")
    public String goFaBu(Model model){
        List<Type> types = typeService.getTypes();
        List<District> districts = districtService.getAllDistrict();
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        return "fabu";
    }
    //查询出租房
    @RequestMapping("selectHouseExtByUserId")
    public String selectHouseExtByUserId(Integer page,Integer rows,HttpSession session,Model model){
//        从session域中取出用户获取id
        Users user = (Users) session.getAttribute("user");
        if(user==null){
            return "guanli";
        }
        PageInfo<HouseExample> pageInfo = houseService.selectHouseExtByUserId(user.getId(), page == null ? 1 : page, 5);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }
//取出单个出租房
    @RequestMapping("getUserHouse")
    public String getUserHouse(String id,Model model){
//        取出房屋信息
        HouseExt houseExt = houseService.getHouse(id);
        //取出所有类型信息
        List<Type> types = typeService.getTypes();
        //取出所有街道信息
        List<Street> streets = streetService.getAllStreet();
        //取出所有区域信息
        List<District> districts = districtService.getAllDistrict();
        model.addAttribute("houseExt",houseExt);
        model.addAttribute("types",types);
        model.addAttribute("streets",streets);
        model.addAttribute("districts",districts);
        return "upfabu";
    }
    //修改租房信息
    @RequestMapping("updateHouse")
    public String updateHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) MultipartFile file) throws IOException {


       String  saveName="";
        String fname = file.getOriginalFilename();
        //是否判断上传文件
        if("".equals(fname)){//没上传
            System.out.println("没上传");
        }else {
            System.out.println("上传了");
//            截取文件类型  .jpg
            String expName = fname.substring(fname.lastIndexOf("."));
            String uuid= UUID.randomUUID().toString();
            String afterPath=new SimpleDateFormat("yyyy\\MM\\dd\\HH\\mm\\ss").format(new Date());
//        以年月日为文件隔离，uuid为文件名称
//        最终保存路径
            saveName=afterPath+"\\"+uuid+expName;

            File fineFile=new File("G:\\images\\",afterPath);

            if (!fineFile.exists()){
                fineFile.mkdirs();
            }
//        保存
            file.transferTo(new File("G:\\images\\",saveName));
            //保存数据库的记录  house已经接收部分表单数据

            //保存数据库的记录  house已经接收部分表单数据

//        保存文件路径
            house.setPath(saveName);
            //删除旧的图片
            File file1 = new File("G:\\images\\", oldPic);
            if (file1!=null)
                file1.delete();
        }
        int r = houseService.updateByPrimaryKeySelective(house);
        if(r==1){
//            更新成功跳转管理页面
            return "redirect:selectHouseExtByUserId";
        }else {
            new File("G:\\images\\",saveName).delete();
            return "error";
        }
    }
    //逻辑删除出租房
    @RequestMapping("isdel")
    public String isdel(String id){
        if(id!=null){
            int result = houseService.isdel(id);
            return "redirect:selectHouseExtByUserId";
        }
      return "error";
    }
    //条件查询全部房屋+分页
    @RequestMapping("goHouse")
    public String goHouse(HouseCondition2 Condition,Model model){
        PageInfo<HouseExt> pageInfo = houseService.selectHouseExtByBrowser(Condition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",Condition);
        return "list";
    }

    @RequestMapping("godetails")
    public String selectHouseExtById(String id,Model model){
        HouseExt houseExt = houseService.selectHouseExtById(id);
        model.addAttribute("houseExt",houseExt);
        return "details";
    }
}
