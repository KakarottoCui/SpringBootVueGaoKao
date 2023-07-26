package com.ivo.mas.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.mapper.UserMapper;
import com.ivo.mas.pojo.User;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.utils.SessionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Service
public class MainService {

    @Resource
    private UserMapper userMapper;

    public ResponseResult login(User user,HttpServletRequest request){
    QueryWrapper queryWrapper = new QueryWrapper(user);
    user = userMapper.selectOne(queryWrapper);
    if(user!=null){
        user.setToken(UUID.randomUUID().toString()+System.currentTimeMillis());
        userMapper.updateById(user);
        user.setPassword("");
        request.getSession().setAttribute("user",user);
        return new ResponseResult(ResponseCode.SUCCESS,"登录成功",user);
    }else{
        return new ResponseResult(ResponseCode.SERVICE_ERROR,"用户名或密码错误,或权限不足");
    }
}

public ResponseResult register(User userSub){
    User user = new User();
    user.setNickname(userSub.getNickname());
    QueryWrapper queryWra
    return new ResponseResult(ResponseCode.SUCCESS,"退出登录成功");
}

public User getUser(User user){
    QueryWrapper queryWrapper = new QueryWrapper(user);
    return userMapper.selectOne(queryWrapper);
}

public ResponseResult getUserInfo(){
    User user = SessionUtils.getUser();
    user.setPassword("");
    return new ResponseResult(ResponseCode.SUCCESS,"查询成功",user);
}


}
