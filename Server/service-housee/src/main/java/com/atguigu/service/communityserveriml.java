package com.atguigu.service;

import base.baseServer;
import base.basedao;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.DictServicedao;
import com.atguigu.dao.communitydao;
import com.djk.server.communityserver;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.CastUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = communityserver.class)
@Transactional
public class communityserveriml extends baseServer<Community> implements communityserver {
    @Autowired
    private communitydao communitydao;
    @Autowired
    private DictServicedao dictServicedao;
    @Override
    public basedao getEntityDao() {
        return communitydao;
    }

    @Override
    public PageInfo findPages(Map filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);
        List<Community> page = getEntityDao().findPage(filters);
        for (Community community : page) {
            String nameByPlaneId = dictServicedao.getNameByPlaneId(community.getPlateId());
            String nameByareaId = dictServicedao.getNameByareaId(community.getAreaId());
            community.setAreaName(nameByareaId);
            community.setPlateName(nameByPlaneId);
        }
        return new PageInfo<>(page,10);
    }

    @Override
    public Integer add(Community community) {
      return   communitydao.add(community);

    }

    @Override
    public Community slectOne(Integer a) {

        Community community = communitydao.slectOne(a);
        String nameByPlaneId = dictServicedao.getNameByPlaneId(community.getPlateId());
        String nameByareaId = dictServicedao.getNameByareaId(community.getAreaId());
        community.setAreaName(nameByareaId);
        community.setPlateName(nameByPlaneId);
        return community;
    }

    @Override
    public Integer uppdata(Community a) {
        return communitydao.updata(a);
    }

    @Override
    public void del(Serializable parseInt) {
        communitydao.del(parseInt);
    }
}
