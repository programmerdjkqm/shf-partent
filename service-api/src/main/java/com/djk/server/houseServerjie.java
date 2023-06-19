package com.djk.server;

import com.github.pagehelper.PageInfo;
import entity.House;
import entity.HouseBroker;
import entity.HouseImage;
import entity.HouseUser;
import vo.HouseQueryVo;
import vo.HouseVo;

import java.util.List;
import java.util.Map;

public interface houseServerjie {
    public PageInfo<House> findPages(Map<String, Object> filters);
    public  Integer add(House a);

    House selectonebyid(String id);
    Integer updata(House house);
    void del(String id);
    void fabu(Integer id,Integer statues);
    List<HouseImage> selecttupian(Integer id,Integer type);
    List<HouseBroker> selectbroker(Integer id);
    List<HouseUser> selectusers(Integer id);
  Integer addbroker(HouseBroker houseBroker);
  Integer updatabroker(HouseBroker houseBroker);
  void  delbroker(Integer id);
  void addimage(HouseImage houseImage);
  void delimage(Long a);
  PageInfo<HouseVo> finlist(Long pagenum, Long pagesize, HouseQueryVo houseVo);
}
