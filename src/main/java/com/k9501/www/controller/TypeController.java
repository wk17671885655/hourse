package com.k9501.www.controller;

        import com.github.pagehelper.PageInfo;
        import com.k9501.www.entity.Street;
        import com.k9501.www.entity.Type;
        import com.k9501.www.service.StreetService;
        import com.k9501.www.service.TypeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    /*分页查询*/
    @RequestMapping("getAllByPage")
    @ResponseBody
    public Map<String,Object> getAllByPage(Integer page,Integer rows){
        PageInfo<Type> pageInfo = typeService.getAllByPage(page,rows);
        Map<String,Object> map=new HashMap<>();
        System.out.println(pageInfo.getList());
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    /*
    * 处理添加请求
    */
    @RequestMapping("addtype")
    @ResponseBody
    public String addType(Type type){
        int r = typeService.insertSelective(type);
        return "{\"result\":"+r+"}";
    }
    //删除
    @RequestMapping("deltetById")
    @ResponseBody
    public String deltetById(Integer id){
        int r = typeService.deleteByPrimaryKey(id);
        return "{\"result\":"+r+"}";
    }
    //修改
    @RequestMapping("updateType")
    @ResponseBody
    public String updateType(Type type){
        int r = typeService.updateByPrimaryKeySelective(type);
        return "{\"result\":"+r+"}";
    }

}
