package com.example.server;

import base.baseServer;
import base.basedao;
import com.alibaba.dubbo.config.annotation.Service;
import com.djk.server.adminServerjie;
import com.example.dao.admindao;
import com.example.dao.adminroledao;
import com.example.dao.roledao;
import entity.Admin;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = adminServerjie.class)
@Transactional
public class adminServer extends baseServer<Admin> implements adminServerjie {
    @Autowired
    @Resource
    private admindao admindao;
    @Autowired
    private roledao roledao;
    @Autowired
    private adminroledao adminroledao;
    @Override
    public basedao<Admin> getEntityDao() {
        return admindao;
    }

    @Override
    public Map<String, Object> fenpeijuese(Long adminid) {
        List<Role> slectall = roledao.slectall();
       List<Long> longList=adminroledao.selectrolebyadmin(adminid);
        LinkedList<Role> noAssginRoleList = new LinkedList<Role>();
        LinkedList<Role> assginRoleList = new LinkedList<Role>();

        for (Role role : slectall) {
            if (longList.contains(role.getId())){
                assginRoleList.add(role);
            }
            else {
                noAssginRoleList.add(role);
            }
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("assginRoleList",assginRoleList);
        stringObjectHashMap.put("noAssginRoleList",noAssginRoleList);
        return stringObjectHashMap;
    }

    @Override
    public Admin selectbyname(String username) {
        return admindao.selectbyname(username);
    }

    @Override
    public void uppdataaandr(Long adminid, Long[] rolesid) {
        adminroledao.delroles(adminid);
        for (Long roleid : rolesid) {
            if (roleid==null){
                continue;
            }
            adminroledao.add(adminid,roleid);
        }
    }
}
