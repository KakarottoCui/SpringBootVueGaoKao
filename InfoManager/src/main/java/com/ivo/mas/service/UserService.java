package com.ivo.mas.service;

import com.ivo.mas.mapper.UserMapper;
import com.ivo.mas.pojo.User;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    
    /**
     * 查询多条数据
     *
     * @param user 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryUserList(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",userMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param user 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryUserObject(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",userMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param user 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addUser(User user) {
        Long uid = SessionUtils.getUserId();
        user.setId(null);
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",user);
    }
    
    /**
     * 修改一条数据
     *
     * @param user 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editUser(User user) {
        userMapper.updateById(user);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",user);
    }

}
