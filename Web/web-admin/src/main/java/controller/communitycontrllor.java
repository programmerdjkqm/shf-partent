package controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.DictService;
import com.djk.server.communityserver;
import com.github.pagehelper.PageInfo;
import entity.Community;
import entity.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class communitycontrllor extends BaseController {
    @Reference
    private communityserver communityserver;
    @Reference
    private DictService dictService;
@RequestMapping("/community")
    public String toindex(Map map, HttpServletRequest request){
    Map<String, Object> filters = getFilters(request);
    request.setAttribute("filters",filters);
    PageInfo<Community> pages = communityserver.findPages(filters);
    request.setAttribute("page",pages);
    List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(new Long(110000));
    request.setAttribute("areaList",slectznodesbypartentid1);
    return "community/index";
}
   @RequestMapping("/community/create")
    public String  create(HttpServletRequest request){
       List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(new Long(110000));
       request.setAttribute("areaList",slectznodesbypartentid1);
       return "community/create";
}
@RequestMapping("/community/save")
    public  String hh(Community community){
    communityserver.add(community);
    return "common/successPage";
}
@RequestMapping("/community/edit/{id}")
    public String lk(@PathVariable(value = "id") String id,HttpServletRequest request){
    Community community = communityserver.slectOne(Integer.parseInt(id));
    request.setAttribute("community",community);
    List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(new Long(110000));
    request.setAttribute("areaList",slectznodesbypartentid1);
    return "community/edit";

}
    @RequestMapping("/community/update")
    public String updata(Community community){
    communityserver.uppdata(community);
    return "common/successPage";
    }
    @RequestMapping("/community/delete/{id}")
    public String del(@PathVariable("id") String id){
    communityserver.del(Integer.parseInt(id));
    return "redirect:/community";
    }
}
