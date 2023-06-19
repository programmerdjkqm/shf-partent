package com.djk.server;

import entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {
   //找字节点
    List<Map<String,Object>> findZnodes(Long id);
    List<Dict> slectznodesbypartentid(Long id);
    Long selectbubainma(String bianma);
    String selectbyid(Long id);
}
