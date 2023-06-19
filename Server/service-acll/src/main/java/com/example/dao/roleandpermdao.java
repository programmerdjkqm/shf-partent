package com.example.dao;

import entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface roleandpermdao {
  List<Long> selectpermissionbyid(Long id);
  void deLAll(Long roleid);

  void add(@Param("roleid") Long roleid, @Param("permissionId") Long permissionId);
}
