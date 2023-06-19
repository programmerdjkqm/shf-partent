package com.djk.server;

import com.github.pagehelper.PageInfo;
import vo.UserFollowVo;

public interface user_followserverjie {
    void add(Long houseid,Long userid);

    Integer  shifuguanzhu(Long houseid, Long userid);

    PageInfo<UserFollowVo> findlistpage(Long pageNum, Long pageSize, Long id);

    void del(Long id);
}
