package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.houseServerjie;
import com.djk.server.houseuserserver;
import entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
 public class houseusercontrllor {
    @Reference
    private houseuserserver houseuserserver;
    @RequestMapping("/houseUser/create")
    public  String tocreat(@RequestParam("houseId")Long houseid, Map map){
        map.put("houseId",houseid);
        return "houseuser/create";
    }
    @RequestMapping("/houseUser/save")
    public String save(HouseUser houseUser,Map map){
        Integer add = houseuserserver.add(houseUser);
        map.put("messagePage",add);
        return "common/successPage";
    }
    @RequestMapping("/houseUser/edit/{id}")
    public String toedit(@PathVariable("id")Long id,Map map){
        HouseUser selectbyid = houseuserserver.selectbyid(id);
        map.put("houseUser",selectbyid);
        return "houseuser/edit";
    }
    @RequestMapping("/houseUser/update")
    public String edit(HouseUser houseUser,Map map){
        Integer edit = houseuserserver.edit(houseUser);
        map.put("messagePage",edit);
        return "common/successPage";
    }
    @RequestMapping("/houseUser/delete/{houseid}/{id}")
    public String del(@PathVariable("houseid")Long houseid,@PathVariable("id")Long id){
     houseuserserver.del(id);
     return "redirect:/house/"+houseid;
    }

}
