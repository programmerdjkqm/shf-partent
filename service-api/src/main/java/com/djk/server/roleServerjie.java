package com.djk.server;

import com.github.pagehelper.PageInfo;
import entity.Permission;
import entity.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface roleServerjie {
    List<Role> selectall();
    Integer add(Role a);
    Role slectOne(Integer a);
    Integer uppdata(Role a );
    void del(Serializable parseInt);
    PageInfo<Role> findPages(Map<String, Object> filters);
    List<Map<String,Object>> selecttrees(Long id);

    void updatarandp(Long roleid, Long[] permissionIds);

    List<Permission> findpermissionsbyid(Long id);

    List<String> selectpermissions(Long id);
}
