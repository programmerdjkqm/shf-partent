package com.djk.server;

import entity.UserInfo;
import vo.RegisterVo;

public interface userserverjie {
    UserInfo selectbyphone(String phone);
    void add(UserInfo userInfo);
}
