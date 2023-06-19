package com.example.dao;

import base.basedao;
import entity.Admin;
import org.springframework.stereotype.Component;

@Component
public interface admindao extends basedao<Admin> {
    Admin selectbyname(String username);
}
