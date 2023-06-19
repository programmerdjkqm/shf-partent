package com.djk.server;

import com.github.pagehelper.PageInfo;
import entity.Community;
import entity.Dict;

import java.io.Serializable;
import java.util.Map;

public interface communityserver{
     PageInfo<Community> findPages(Map<String, Object> filters);

     Integer add(Community community);
     Community slectOne(Integer a);
     Integer uppdata(Community a);
     void del(Serializable parseInt);

}
