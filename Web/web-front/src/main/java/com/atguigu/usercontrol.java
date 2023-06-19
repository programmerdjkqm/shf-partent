package com.atguigu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.user_followserverjie;
import com.djk.server.userserverjie;
import com.github.pagehelper.PageInfo;
import entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;
import result.ResultCodeEnum;
import util.MD5;
import vo.LoginVo;
import vo.RegisterVo;
import vo.UserFollowVo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class usercontrol {
    @Reference
    private userserverjie userserverjie;
    @Reference
    private user_followserverjie userFollowserver;
    @ResponseBody
    @RequestMapping("/userInfo/sendCode/{phone}")
    public Result yanzhengma(@PathVariable("phone") String phone, HttpSession session){
        String code = "888888";
        session.setAttribute("code",code);
        return Result.ok(code);

    }
    @ResponseBody
    @RequestMapping("/userInfo/register")
   public  Result login(@RequestBody RegisterVo registerVo,HttpSession httpSession){
        Object code = httpSession.getAttribute("code");
        if (! registerVo.getCode().equals((String) code)){
           return   Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
        if (userserverjie.selectbyphone(registerVo.getPhone()) !=null ){
            return  Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(registerVo.getPhone());
        userInfo.setPassword(MD5.encrypt(registerVo.getPassword()));
        userInfo.setNickName(registerVo.getNickName());
        userInfo.setStatus(1);
        userserverjie.add(userInfo);
        return  Result.ok();
    }
    @ResponseBody
    @RequestMapping("/userInfo/login")
    public Result login(@RequestBody LoginVo loginVo,HttpSession session){
        UserInfo selectbyphone = userserverjie.selectbyphone(loginVo.getPhone());
        if (selectbyphone==null){
            return  Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (! MD5.encrypt(loginVo.getPassword()).equals(selectbyphone.getPassword()))
        {return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);}
        if (selectbyphone.getStatus()==0){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        session.setAttribute("userinfo",selectbyphone);
        Map map =new HashMap();
        map.put("nickName",selectbyphone.getNickName());
        return Result.ok(map);
    }
    @ResponseBody
    @RequestMapping("/userInfo/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("userinfo");
        return Result.ok();
    }
    @ResponseBody
@RequestMapping("/userFollow/auth/follow/{houseid}")
    public Result guanzhu(@PathVariable("houseid") Long id,HttpSession session){
    UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
    userFollowserver.add(id, userinfo.getId());
    return Result.ok();
}
@ResponseBody
    @RequestMapping("/userFollow/auth/list/{pageNum}/{pageSize}")
    public Result guanzhu(@PathVariable("pageNum") Long pageNum,@PathVariable("pageSize") Long pageSize,HttpSession session){
    UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
    PageInfo<UserFollowVo> pageInfo =userFollowserver.findlistpage(pageNum,pageSize,userinfo.getId());
    return  Result.ok(pageInfo);
    }
    @ResponseBody
    @RequestMapping("/userFollow/auth/cancelFollow/{id}")
    public Result guanzhuquxiao(@PathVariable("id") Long id){
        userFollowserver.del(id);
        return Result.ok();
    }
}
