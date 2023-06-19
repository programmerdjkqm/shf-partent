package com.atguigu;

import com.atguigu.dao.DictServicedao;
import com.atguigu.dao.houseServerdao;
import com.atguigu.dao.hse_house_userdao;
import entity.Dict;
import entity.HouseUser;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@ContextConfiguration("classpath:spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class text01 {
    @Autowired
    private DictServicedao dictServicedao;
    @Autowired
    private houseServerdao houseServerdao;
    @Autowired
    private hse_house_userdao hse_house_userdao;
    @Test
    public void ji( ){
        List<Dict> slectznodesbypartentid = dictServicedao.slectznodesbypartentid(1l);
        for (Dict dict : slectznodesbypartentid) {
            System.out.println(dict);
        }
    }
    @Test
    public  void ko(){
        System.out.println(houseServerdao.Selectbyhouse(new HashMap()));
        System.out.println(houseServerdao.Selectbyhouse(new HashMap()));
        System.out.println(houseServerdao.Selectbyhouse(new HashMap()));
        System.out.println(houseServerdao.Selectbyhouse(new HashMap()));
        System.out.println(houseServerdao.Selectbyhouse(new HashMap()));
    }
    @Test
    public void  jui(){
        HouseUser houseUser = new HouseUser();


    }
}
