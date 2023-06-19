package com.djk.server;

import entity.HouseUser;

public interface houseuserserver {
    Integer add(HouseUser houseUser);
    HouseUser selectbyid(Long id);
    Integer edit(HouseUser houseUser);
    void del(Long id);
}
