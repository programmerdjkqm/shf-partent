package com.example.server;

import base.baseServer;
import base.basedao;
import com.alibaba.dubbo.config.annotation.Service;
import com.djk.server.roleServerjie;
import com.example.dao.permissiondao;
import com.example.dao.roleandpermdao;
import com.example.dao.roledao;

import entity.Permission;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = roleServerjie.class)
@Transactional
public class roleServer extends baseServer<Role> implements roleServerjie {
    @Autowired
    @Resource
    private roledao roledao;
    @Autowired
    private permissiondao permissiondao;
    @Autowired
    private roleandpermdao roleandpermdao;
    @Override
    public basedao<Role> getEntityDao() {
        return roledao;
    }

    @Override
    public List<Map<String, Object>> selecttrees(Long id) {
        List<Permission> selecttree = permissiondao.selecttree();
        List<Long> selectpermissionbyid = roleandpermdao.selectpermissionbyid(id);
        List<Map<String, Object>> maps = new LinkedList<>();
        for (Permission permission : selecttree) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", permission.getId());
            map.put("pId", permission.getParentId());
            map.put("name", permission.getName());
            if(selectpermissionbyid.contains(permission.getId())) {
                map.put("checked", true);
            }
            maps.add(map);
        }
        return maps;
    }

    @Override
    public List<String> selectpermissions(Long id) {
        if (id==1){
           return   permissiondao.findall();
        }else {
            return  permissiondao.findpermissionbyid(id);
        }

    }

    @Override
    public List<Permission> findpermissionsbyid(Long id) {
       if (id==1){
         return   permissiondao.selecttree();
       }else {
           return permissiondao.findpermissionbyadminid(id);
       }
    }

    @Override
    public void updatarandp(Long roleid, Long[] permissionIds) {
        if (permissionIds!=null){
            roleandpermdao.deLAll(roleid);
            for (Long permissionId : permissionIds) {
                roleandpermdao.add(roleid,permissionId);
            }
        }else {
            roleandpermdao.deLAll(roleid);
        }
    }
}
