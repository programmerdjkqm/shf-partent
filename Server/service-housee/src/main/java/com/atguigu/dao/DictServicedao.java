package com.atguigu.dao;

import entity.Dict;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DictServicedao {
    List<Dict> slectznodesbypartentid(Long id);
    String getNameByPlaneId(Long id);
    String getNameByareaId(Long id);
    Long selectbybianma(String id);
}
