package com.atguigu.dao;

import entity.UserInfo;
import org.springframework.stereotype.Component;
import vo.RegisterVo;
@Component
public interface user_info_dao {

    UserInfo selectbyphone(String phone);
    void add(UserInfo userInfo);
}
