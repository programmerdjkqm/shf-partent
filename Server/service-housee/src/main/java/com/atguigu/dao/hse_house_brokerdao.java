package com.atguigu.dao;

import base.basedao;
import entity.HouseBroker;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface hse_house_brokerdao extends basedao<HouseBroker> {
    List<HouseBroker> selectbyhouseid(Integer id);
}
