package controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.roleServerjie;
import com.github.pagehelper.PageInfo;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class rolecontrllor extends BaseController {
    @Reference
    private roleServerjie roleServer;

    //@RequestMapping("/role")
//public String role(HttpServletRequest request){
//        List<Role> selectall = roleServer.selectall();
//        request.setAttribute("list",selectall);
//        return "role/role";
//    }
    @PreAuthorize("hasAuthority('role.show')")
    @RequestMapping("/role")
    public String role(Map map, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        map.put("filters", filters);
        PageInfo<Role> pageInfo = roleServer.findPages(filters);
        map.put("page", pageInfo);
        return "role/role";
    }

    @RequestMapping("/role/creat")
    public String addRole() {
        return "role/roleCreat";
    }

    @RequestMapping("/role/save")
    public String addRole(Role a) {
        roleServer.add(a);
        return "common/successPage";
    }

    @RequestMapping("/role/edit/{id}")
    public String uppdata(@PathVariable("id") String id, HttpServletRequest request) {
        int i = Integer.parseInt(id);
        Role role = roleServer.slectOne(i);
        request.setAttribute("role", role);
        return "role/edit";
    }

    @RequestMapping("/role/update")
    public String updata(Role a) {
        roleServer.uppdata(a);
        return "common/successPage";
    }

    @RequestMapping("/role/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        roleServer.del(Integer.parseInt(id));
        return "redirect:/role";
    }

    @RequestMapping("/role/assignShow/{id}")
    public String toquanxian(@PathVariable("id") String id, Map map) {
        List<Map<String, Object>> selecttrees = roleServer.selecttrees(Long.valueOf(id));
        map.put("zNodes", selecttrees);
        map.put("roleId", id);
        return "role/assginShow";
    }
    @RequestMapping("/role/assignPermission")
    public String updata(@RequestParam("permissionIds")Long[] permissionIds,@RequestParam("roleId")Long roleid){
        roleServer.updatarandp(roleid,permissionIds);
        return "common/successPage";
    }
}

