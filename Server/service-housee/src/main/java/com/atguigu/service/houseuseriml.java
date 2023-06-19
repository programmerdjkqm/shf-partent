package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.hse_house_userdao;
import com.djk.server.houseuserserver;
import entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class houseuseriml implements houseuserserver {
    @Autowired
    private hse_house_userdao hse_house_userdao;

    @Override
    public Integer add(HouseUser houseUser) {
        return hse_house_userdao.add(houseUser);
    }

    @Override
    public HouseUser selectbyid(Long id) {
        return hse_house_userdao.slectOne(id);
    }

    @Override
    public Integer edit(HouseUser houseUser) {
        return hse_house_userdao.updata(houseUser);
    }

    @Override
    public void del(Long id) {
        hse_house_userdao.del(id);
    }
}
