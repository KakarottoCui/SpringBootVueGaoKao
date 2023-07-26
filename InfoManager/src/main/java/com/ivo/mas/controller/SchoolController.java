package com.ivo.mas.controller;
import com.ivo.mas.service.SchoolService;
import com.ivo.mas.pojo.School;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/school")
public class SchoolController {

    @Resource
    SchoolService schoolService;
    
    /**
     * 查询多条数据
     *
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> querySchoolObject(@RequestBody School school){
        return schoolService.querySchoolObject(school);
    }

}
