package controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;

import com.djk.server.adminServerjie;
import com.github.pagehelper.PageInfo;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import util.QiniuUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Controller
public class admincontrllor extends BaseController {
    @Reference
    private adminServerjie adminServer;
    @Autowired
    private PasswordEncoder PasswordEncoder;
    @RequestMapping("/admin")
    public String role(Map map, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        map.put("filters",filters);
        PageInfo<Admin> pageInfo = adminServer.findPages(filters);
        map.put("page",pageInfo);
        return "admin/admin";
    }
    @RequestMapping("/admin/creat")
    public String addRole(){
        return "admin/adminCreat";
    }
    @RequestMapping("/admin/save")
    public String addRole(Admin a){
        a.setPassword(PasswordEncoder.encode(a.getPassword()));
        adminServer.add(a);
        return "common/successPage";
    }
    @RequestMapping("/admin/edit/{id}")
    public  String uppdata(@PathVariable("id") String id, HttpServletRequest request){
        int i = Integer.parseInt(id);
        Admin admin = adminServer.slectOne(i);
        request.setAttribute("admin",admin);
        return "admin/edit";
    }
    @RequestMapping("/admin/update")
    public String updata(Admin a){
        adminServer.uppdata(a);
        return "common/successPage";
    }
    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") String id){
        adminServer.del(Integer.parseInt(id));
        return "redirect:/admin";
    }
    @RequestMapping("/admin/uploadShow/{id}")
    public String toUploadPhoto(@PathVariable Long id,Map map){
     map.put("id",id);
     return "admin/upload";
    }
    @RequestMapping("/admin/upload/{id}")
    public String upload(@PathVariable Long id, MultipartFile file)throws Exception{
        String originalFilename = file.getOriginalFilename();
        originalFilename += UUID.randomUUID().toString();
        QiniuUtils.upload2Qiniu(file.getBytes(),originalFilename);
        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl("http://rquaa8zgf.hb-bkt.clouddn.com/"+originalFilename);
        adminServer.uppdata(admin);
        return "common/successPage";
    }
    @RequestMapping("/admin/assignShow/{id}")
    public String fenpeiyemian(@PathVariable("id")Long adminid, ModelMap map){
        map.addAttribute("adminId",adminid);
        Map<String,Object> map1= adminServer.fenpeijuese(adminid);
        map.addAllAttributes(map1);
        return "admin/assignShow";
    }
    @RequestMapping("/admin/assignRole")
    public String fenpei(@RequestParam("adminId")Long adminid, @RequestParam("roleIds")Long[] rolesid){
        adminServer.uppdataaandr(adminid,rolesid);
        return "common/successPage";
    }
}
