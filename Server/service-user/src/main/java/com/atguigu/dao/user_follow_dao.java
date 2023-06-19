package com.atguigu.dao;

import base.basedao;
import entity.UserFollow;
import entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import vo.UserFollowVo;

import java.util.List;

@Component
public interface user_follow_dao extends basedao<UserFollow>{
    Integer panduanguanzhu(@Param("houseid") Long houseid, @Param("userid") Long userid);
    List<UserFollowVo> findlistpages(Long id);

}
