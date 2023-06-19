package com.djk.server;

import com.github.pagehelper.PageInfo;
import entity.Admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface adminServerjie {
    List<Admin> selectall();
     Integer add(Admin a);
    Admin slectOne(Integer a);
    Integer uppdata(Admin a );
    void del(Serializable parseInt);
     PageInfo<Admin> findPages(Map<String, Object> filters);

    Map<String, Object> fenpeijuese(Long adminid);

    void uppdataaandr(Long adminid, Long[] rolesid);

    Admin selectbyname(String username);
}
