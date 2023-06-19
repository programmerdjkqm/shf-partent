package com.atguigu.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.user_info_dao;
import com.djk.server.userserverjie;
import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vo.RegisterVo;
@Service(interfaceClass = userserverjie.class)
@Transactional
public class userserverjieiml implements userserverjie{
    @Autowired
    private user_info_dao userInfoDao;
    @Override
    public UserInfo selectbyphone(String phone) {
        return userInfoDao.selectbyphone(phone);
    }

    @Override
    public void add(UserInfo userInfo) {
     userInfoDao.add(userInfo);
    }
}
