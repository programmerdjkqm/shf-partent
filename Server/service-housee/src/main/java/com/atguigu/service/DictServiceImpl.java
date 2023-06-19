package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.DictServicedao;
import com.djk.server.DictService;
import entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service(interfaceClass = DictService.class)
@Transactional
public class DictServiceImpl implements DictService {
    @Autowired
     private DictServicedao dictServicedao;
    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        List<Dict> slectznodesbypartentid =dictServicedao.slectznodesbypartentid(id);
         List<Map<String,Object>> list =new ArrayList<>();
        for (Dict dict : slectznodesbypartentid) {
            Map map =new HashMap();
            map.put("id",dict.getId());
            map.put("name",dict.getName());
            List<Dict> slectznodesbypartentid1 = dictServicedao.slectznodesbypartentid(dict.getId());
            map.put("isParent",!slectznodesbypartentid1.isEmpty());
            list.add(map);
        }
         return  list;
    }
    @Override
    public List<Dict> slectznodesbypartentid(Long id){
        return dictServicedao.slectznodesbypartentid(id);
    }

    @Override
    public Long selectbubainma(String bianma) {
        if (bianma.equals("beijing")){
            bianma="北京";
        }
        if (bianma.equals("houseType")){
            bianma="户型";
        }
        if (bianma.equals("floor")){
            bianma="楼层";
        }
        if (bianma.equals("buildStructure")){
            bianma="建筑结构";
        }
        if (bianma.equals("direction")){
            bianma="朝向";
        }
        if (bianma.equals("decoration")){
            bianma="装修情况";
        }
        if (bianma.equals("houseUse")){
            bianma="房屋用途";
        }
        Long selectbybianma = dictServicedao.selectbybianma(bianma);
        return selectbybianma;
    }

    @Override
    public String selectbyid(Long id) {
        return dictServicedao.getNameByareaId(id);
    }
}
