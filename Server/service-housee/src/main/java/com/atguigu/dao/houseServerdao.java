package com.atguigu.dao;

import base.basedao;
import entity.House;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Component;
import vo.HouseQueryVo;
import vo.HouseVo;

import java.util.List;
import java.util.Map;
@Component
public interface houseServerdao extends basedao<House> {
    List<House> Selectbyhouse(Map map);
    List<HouseVo> findlist(HouseQueryVo houseVo);
}
