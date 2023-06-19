package controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.DictService;
import com.djk.server.adminServerjie;
import com.djk.server.communityserver;
import com.djk.server.houseServerjie;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class housecontrllor extends BaseController {
    @Reference
    private DictService dictService;
    @Reference
    private houseServerjie houseServerjie;
    @Reference
    private communityserver communityserver;
    @Reference
    private adminServerjie adminServerjie;
    @RequestMapping("/house")
public String toIndex(House house, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        request.setAttribute("filters",filters);
        PageInfo<House> pages = houseServerjie.findPages(filters);
        request.setAttribute("page",pages);
        List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(110000l);
        request.setAttribute("communityList",slectznodesbypartentid);
        List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(10000l);
        request.setAttribute("houseTypeList",slectznodesbypartentid1);
        request.setAttribute("floorList",dictService.slectznodesbypartentid(20000l));
        request.setAttribute("buildStructureList",dictService.slectznodesbypartentid(30000l));
        request.setAttribute("directionList",dictService.slectznodesbypartentid(50000l));
         request.setAttribute("decorationList",dictService.slectznodesbypartentid(40000l));
         request.setAttribute("houseUseList",dictService.slectznodesbypartentid(60000l));
        return "house/index";
}@RequestMapping("/house/create")
public String tocreate(HttpServletRequest request){
        List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(110000l);
        request.setAttribute("communityList",slectznodesbypartentid);
        List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(10000l);
        request.setAttribute("houseTypeList",slectznodesbypartentid1);
        request.setAttribute("floorList",dictService.slectznodesbypartentid(20000l));
        request.setAttribute("buildStructureList",dictService.slectznodesbypartentid(30000l));
        request.setAttribute("directionList",dictService.slectznodesbypartentid(50000l));
        request.setAttribute("decorationList",dictService.slectznodesbypartentid(40000l));
        request.setAttribute("houseUseList",dictService.slectznodesbypartentid(60000l));
   return "house/create";
    }
    @RequestMapping("/house/save")
    public String save(HttpServletRequest request,House house){
        Integer add = houseServerjie.add(house);
        return "common/successPage";
    }
    @RequestMapping("/house/edit/{id}")
    public String toedit(@PathVariable("id") String id,HttpServletRequest request){
        House selectonebyid = houseServerjie.selectonebyid(id);
        request.setAttribute("house",selectonebyid);
        List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(110000l);
        request.setAttribute("communityList",slectznodesbypartentid);
        List<Dict> slectznodesbypartentid1 = dictService.slectznodesbypartentid(10000l);
        request.setAttribute("houseTypeList",slectznodesbypartentid1);
        request.setAttribute("floorList",dictService.slectznodesbypartentid(20000l));
        request.setAttribute("buildStructureList",dictService.slectznodesbypartentid(30000l));
        request.setAttribute("directionList",dictService.slectznodesbypartentid(50000l));
        request.setAttribute("decorationList",dictService.slectznodesbypartentid(40000l));
        request.setAttribute("houseUseList",dictService.slectznodesbypartentid(60000l));
    return "house/edit";
    }
    @RequestMapping("/house/update")
    public String update(House house){
        Integer updata = houseServerjie.updata(house);
        return "common/successPage";
    }
    @RequestMapping("/house/delete/{id}")
    public String del(@PathVariable("id")String id ){
   houseServerjie.del(id);
   return "redirect:/house";
    }
    @RequestMapping("/house/publish/{id}/{status}")
    public String fabu(@PathVariable("id") Integer id,@PathVariable Integer status){
      houseServerjie.fabu(id,status);
   return "redirect:/house";
    }
    @RequestMapping("/house/{id}")
    public String xiangQing(@PathVariable("id") String id,Map map){
        House selectonebyid = houseServerjie.selectonebyid(id);
        map.put("house",selectonebyid);
        Community community = communityserver.slectOne(selectonebyid.getCommunityId().intValue());
        map.put("community",community);
        List<HouseImage> selecttupian = houseServerjie.selecttupian(Integer.valueOf(id),1);
        map.put("houseImage1List",selecttupian);
       map.put("houseImage2List",houseServerjie.selecttupian(Integer.parseInt(id),2));
        List<HouseBroker> selectbroker = houseServerjie.selectbroker(Integer.valueOf(id));
        map.put("houseBrokerList",selectbroker);
        List<HouseUser> selectusers = houseServerjie.selectusers(Integer.valueOf(id));
        map.put("houseUserList",selectusers);
        return "house/show";
    }
    @RequestMapping("/houseBroker/create")
    public  String  tocreate(@RequestParam("houseId")Integer houseid,Map map){
        map.put("houseId",houseid);
        List<Admin> selectall = adminServerjie.selectall();
        map.put("adminList",selectall);
        return  "houseBroker/create";
    }
    @RequestMapping("/houseBroker/save")
    public String savebroker(@RequestParam("houseId")Integer houseid,@RequestParam("brokerId") Integer adminid){
        Admin admin = adminServerjie.slectOne(adminid);
        HouseBroker houseBroker = new HouseBroker();
        houseBroker.setHouseId(houseid.longValue());
        houseBroker.setBrokerId(admin.getId());
        houseBroker.setBrokerName(admin.getName());
      houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        Integer addbroker = houseServerjie.addbroker(houseBroker);
        return "common/successPage";
    }
    @RequestMapping("/houseBroker/edit/{id}")
    public String toedit(@PathVariable("id") Integer id,Map map){
        HouseBroker houseBroker = new HouseBroker();
        houseBroker.setId(id.longValue());
        map.put("houseBroker",houseBroker);
        List<Admin> selectall = adminServerjie.selectall();
        map.put("adminList",selectall);
        return "houseBroker/edit";
    }
    @RequestMapping("/houseBroker/update")
    public String edit(@RequestParam("id")Long id,@RequestParam("brokerId") Integer brokerid){
        Admin admin = adminServerjie.slectOne(brokerid);
        HouseBroker houseBroker = new HouseBroker();
        houseBroker.setId(id);
        houseBroker.setBrokerId(admin.getId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseServerjie.updatabroker(houseBroker);
        return "common/successPage";
    }
    @RequestMapping("/houseBroker/delete/{houseid}/{id}")
    public String delbroke(@PathVariable("houseid")Integer houseid,@PathVariable("id") Integer id){
        houseServerjie.delbroker(id);
        return "redirect:/house/"+houseid;
    }
}
