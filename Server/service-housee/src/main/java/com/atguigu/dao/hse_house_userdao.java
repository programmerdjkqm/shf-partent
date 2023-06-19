package com.atguigu.dao;

import base.basedao;
import entity.HouseUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface hse_house_userdao extends basedao<HouseUser> {
    List<HouseUser> selectbyid(Integer id);
}
