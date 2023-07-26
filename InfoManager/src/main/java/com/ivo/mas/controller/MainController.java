package com.ivo.mas.controller;

import com.ivo.mas.pojo.User;
import com.ivo.mas.service.MainService;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@BaseResponse
public class MainController {

    @Resource
    MainService mainService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseR
    public ResponseResult getUserInfo(){
        return mainService.getUserInfo();
    }

}
