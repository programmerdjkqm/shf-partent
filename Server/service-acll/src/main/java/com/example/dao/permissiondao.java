package com.example.dao;

import entity.Permission;

import java.util.List;

public interface permissiondao {
    List<Permission> selecttree();


    List<Permission> findpermissionbyadminid(Long id);

    List<String> findall();

    List<String> findpermissionbyid(Long id);
}
