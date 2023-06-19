package com.atguigu.server;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.user_follow_dao;
import com.atguigu.dao.user_info_dao;
import com.djk.server.DictService;
import com.djk.server.user_followserverjie;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vo.UserFollowVo;

import java.util.List;

@Service(interfaceClass = user_followserverjie.class)
@Transactional
public class user_followserver implements user_followserverjie {
    @Override
    public PageInfo<UserFollowVo> findlistpage(Long pageNum, Long pageSize, Long id) {
        PageHelper.startPage(Math.toIntExact(pageNum), Math.toIntExact(pageSize));
        List<UserFollowVo> findlistpages = userFollowDao.findlistpages(id);
        for (UserFollowVo findlistpage : findlistpages) {
            findlistpage.setHouseTypeName(dictService.selectbyid(findlistpage.getHouseTypeId()));
            findlistpage.setFloorName(dictService.selectbyid(findlistpage.getFloorId()));
            findlistpage.setDirectionName(dictService.selectbyid(findlistpage.getDirectionId()));
        }
        return new PageInfo<UserFollowVo>(findlistpages,5);
    }

    @Override
    public void del(Long id) {
        userFollowDao.del(id);
    }

    @Autowired
    private user_follow_dao userFollowDao;
    @Reference
    private DictService dictService;

    @Override
    public void add(Long houseid, Long userid) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userid);
        userFollow.setHouseId(houseid);
        userFollowDao.add(userFollow);
    }

    @Override
    public Integer shifuguanzhu(Long houseid, Long userid) {
        return userFollowDao.panduanguanzhu(houseid, userid);
    }
}
