package com.ivo.mas.mapper;

import com.ivo.mas.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * (User)表数据库访问层
 *
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

}
