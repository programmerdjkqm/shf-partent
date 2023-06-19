package com.atguigu.service;

import base.baseServer;
import base.basedao;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.Page;
import com.atguigu.dao.*;
import com.djk.server.houseServerjie;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.House;
import entity.HouseBroker;
import entity.HouseImage;
import entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.CastUtil;
import vo.HouseQueryVo;
import vo.HouseVo;

import java.util.List;
import java.util.Map;
@Service(interfaceClass = houseServerjie.class)
@Transactional
public class houseServeriml extends baseServer<House> implements houseServerjie {
    @Autowired
    private houseServerdao houseServerdao;
    @Autowired
    private DictServicedao dictServicedao;
    @Autowired
    private hse_house_image  hse_house_image;
    @Autowired
    private hse_house_brokerdao hse_house_broker;
    @Autowired
    private hse_house_userdao hse_house_userdao;

    @Override
    public PageInfo<House> findPages(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);
        List<House> selectbyhouse = houseServerdao.Selectbyhouse(filters);
        for (House house : selectbyhouse) {
            house.setHouseTypeName(dictServicedao.getNameByPlaneId(house.getHouseTypeId()));
          house.setFloorName(dictServicedao.getNameByareaId(house.getFloorId()));
          house.setBuildStructureName(dictServicedao.getNameByareaId(house.getBuildStructureId()));
         house.setDirectionName(dictServicedao.getNameByareaId(house.getDirectionId()));
          house.setDecorationName(dictServicedao.getNameByareaId(house.getDecorationId()));
            house.setHouseUseName( dictServicedao.getNameByPlaneId(house.getHouseUseId()));
        }
        return new PageInfo<House>(selectbyhouse,10);
    }

    @Override
    public basedao getEntityDao() {
        return houseServerdao;
    }

   @Override
    public Integer add(House a) {
        Integer integer = getEntityDao().add(a);
        return integer;
    }

@Override
    public House selectonebyid(String id) {
        House house = houseServerdao.slectOne(Integer.parseInt(id));
       house.setHouseTypeName(dictServicedao.getNameByPlaneId(house.getHouseTypeId()));
    house.setFloorName(dictServicedao.getNameByareaId(house.getFloorId()));
    house.setBuildStructureName(dictServicedao.getNameByareaId(house.getBuildStructureId()));
    house.setDirectionName(dictServicedao.getNameByareaId(house.getDirectionId()));
    house.setDecorationName(dictServicedao.getNameByareaId(house.getDecorationId()));
   house.setHouseUseName( dictServicedao.getNameByPlaneId(house.getHouseUseId()));
        return house;
    }

    @Override
    public Integer updata(House house) {
        return houseServerdao.updata(house);
    }

    @Override
    public void del(String id) {
        houseServerdao.del(Integer.parseInt(id));
    }

    @Override
    public void fabu(Integer id, Integer statues) {
        House house = new House();
        house.setId(id.longValue());
        house.setStatus(statues);
        houseServerdao.updata(house);
    }

    @Override
    public List<HouseBroker> selectbroker(Integer id) {
        return hse_house_broker.selectbyhouseid(id);
    }

    @Override
    public List<HouseImage> selecttupian(Integer id,Integer type) {
        return hse_house_image.selectbyhouseid(id,type);
    }

    @Override
    public void delimage(Long a) {
        hse_house_image.del(a);
    }

    @Override
    public void addimage(HouseImage houseImage) {
        hse_house_image.add(houseImage);
    }

    @Override
    public List<HouseUser> selectusers(Integer id) {
        List<HouseUser> selectbyid = hse_house_userdao.selectbyid(id);
        return selectbyid;
    }

    @Override
    public Integer addbroker(HouseBroker houseBroker) {
        return hse_house_broker.add(houseBroker);
    }

    @Override
    public Integer updatabroker(HouseBroker houseBroker) {
        return hse_house_broker.updata(houseBroker);
    }

    @Override
    public void delbroker(Integer id) {
        hse_house_broker.del(id);
    }

    @Override
    public PageInfo<HouseVo> finlist(Long pagenum, Long pagesize, HouseQueryVo houseVo) {
        PageHelper.startPage(Math.toIntExact(pagenum), Math.toIntExact(pagesize));
        List<HouseVo> findlist = houseServerdao.findlist(houseVo);
        for (HouseVo vo : findlist) {
          vo.setHouseTypeName( dictServicedao.getNameByareaId(vo.getHouseTypeId()));
            vo.setFloorName(dictServicedao.getNameByareaId(vo.getFloorId()));
            vo.setDirectionName(dictServicedao.getNameByareaId(vo.getDirectionId()));
        }
        PageInfo<HouseVo> houseVoPageInfo = new PageInfo<>(findlist, 5);
        return houseVoPageInfo;
    }
}
