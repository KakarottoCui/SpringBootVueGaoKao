package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * User表实体类
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 775720341784996490L;
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 类型，0管理员，1用户
     */
    private Long type;
    /**
     * 姓名
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * token，临时使用
     */
    private String token;
    /**
     * 电话
     */
    private String tel;


}

