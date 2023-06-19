package com.example.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface adminroledao {
   List<Long> selectrolebyadmin(Long admin);

   void delroles(Long adminid);

   void add(@Param("adminid") Long adminid, @Param("roleid") Long roleid);
}
