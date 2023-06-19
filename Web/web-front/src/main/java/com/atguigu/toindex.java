package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.DictService;
import com.djk.server.communityserver;
import com.djk.server.houseServerjie;
import com.djk.server.user_followserverjie;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;
import vo.HouseQueryVo;
import vo.HouseVo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class toindex {
    @Reference
    private DictService dictService;
    @Reference
    private houseServerjie houseServerjie;
    @Reference
    private communityserver communityserver;
    @Reference
    private user_followserverjie userFollowserverjie;
    @RequestMapping("/")
    public String toindex(){
        return "/WEB-INF/index.html";
    }
    @ResponseBody
    @RequestMapping("/dict/findListByDictCode/{bianma}")
    public Result select(@PathVariable String bianma){
        Long selectbubainma = dictService.selectbubainma(bianma);
        List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(selectbubainma);
        return Result.ok(slectznodesbypartentid);
    }
    @ResponseBody
    @RequestMapping("/house/list/{pageNum}/{pageSize}")
    public Result fenye(@RequestBody HouseQueryVo houseVo, @PathVariable("pageNum") Long pagenum,
                        @PathVariable("pageSize")Long pagesize){
        PageInfo<HouseVo> finlist = houseServerjie.finlist(pagenum, pagesize, houseVo);
        return Result.ok(finlist);
    }
    @ResponseBody
    @RequestMapping("/dict/findListByParentId/{areaId}")
    public Result<List<Dict>> ji(@PathVariable("areaId") String id){
        List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(Long.valueOf(id));
        return Result.ok(slectznodesbypartentid);
    }
    @ResponseBody
    @RequestMapping("/house/info/{id}")
    public Result info(@PathVariable("id")String id, HttpSession session){
        House house = houseServerjie.selectonebyid(id);
        Community community =communityserver.slectOne(Math.toIntExact(house.getCommunityId()));
        List<HouseBroker> houseBrokerList = houseServerjie.selectbroker(Integer.valueOf(id));
        List<HouseImage> houseImage1List = houseServerjie.selecttupian(Integer.valueOf(id),1);

        Map<String, Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        //关注业务后续补充，当前默认返回未关注
//        map.put("isFollow",false);
        UserInfo userinfo = (UserInfo)session.getAttribute("userinfo");
        if (userinfo==null){
            map.put("isFollow",false);
            return Result.ok(map);
        }
        Integer shifuguanzhu = userFollowserverjie.shifuguanzhu(house.getId(), userinfo.getId());
        if (shifuguanzhu!=0){
            map.put("isFollow",true);
        }
        else {
            map.put("isFollow",false);
        }
        return Result.ok(map);
    }
}
