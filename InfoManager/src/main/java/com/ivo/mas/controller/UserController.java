package com.ivo.mas.controller;
import com.ivo.mas.service.UserService;
import com.ivo.mas.pojo.User;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;
    
    /**
     * 查询多条数据
     *
     * @param user 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryUserList(@RequestBody User user){
        return userService.queryUserList(user);
    }
    /**
     * 查询一条数据
     *
     * @param user 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryUserObject(@RequestBody User user){
        return userService.queryUserObject(user);
    }
    /**
     * 新增一条数据
     *
     * @param user 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ResponseResult<Object> addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    /**
     * 修改一条数据
     *
     * @param user 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public ResponseResult<Object> editUser(@RequestBody User user){
        return userService.editUser(user);
    }
    
}
