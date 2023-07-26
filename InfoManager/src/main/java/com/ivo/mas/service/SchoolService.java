package com.ivo.mas.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ivo.mas.mapper.SchoolMapper;
import com.ivo.mas.pojo.School;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class SchoolService {

    @Resource
    private SchoolMapper schoolMapper;
    
    /**
     * 查询多条数据
     *
     * @param school 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> querySchoolList(School school) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("score", school.getScore());
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",schoolMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param school 查询条件
     * @return 对象
     */
    public ResponseResult<Object> querySchoolObject(School school) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<School>(school);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",schoolMapper.selectOne(queryWrapper));
    }

    /**
     * 查询一条数据
     *
     * @param school 查询条件
     * @return 对象
     */
    public ResponseResult<Object> querySchoolObjectByScore(School school) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("score", school.getScore());
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",schoolMapper.selectOne(queryWrapper));
    }
    


}
