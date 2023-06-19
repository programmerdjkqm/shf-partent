package com.atguigu.dao;

import base.basedao;
import entity.HouseImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface hse_house_image extends basedao<HouseImage> {
    List<HouseImage> selectbyhouseid(@Param("id") Integer houseid, @Param("type") Integer type);

}
