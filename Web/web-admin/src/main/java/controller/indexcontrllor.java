package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.adminServerjie;
import com.djk.server.roleServerjie;
import entity.Admin;
import entity.Permission;
import entity.PermissionHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
@Controller
public class indexcontrllor {
    @Reference
    private adminServerjie adminServerjie;
    @Reference
    private roleServerjie roleServerjie;
     @RequestMapping("/")
    public String toindex(Map map){
//         Integer a =1;
//         Admin admin = adminServerjie.slectOne(a);
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         User user = (User)authentication.getPrincipal();
         Admin admin = adminServerjie.selectbyname(user.getUsername());
         map.put("admin",admin);
         List<Permission> findpermissionsbyid = roleServerjie.findpermissionsbyid(admin.getId());
         List<Permission> bulid = PermissionHelper.bulid(findpermissionsbyid);
         map.put("permissionList",bulid);
         return "frame/index";
}
     @RequestMapping("/main")
    public String indexInMain(){
         return "frame/main";
     }
     @RequestMapping("/login")
    public String tologin(){
         return "frame/login";
     }
     @RequestMapping("/auth")
    public String toauth(){
         return "frame/auth";
     }
}
