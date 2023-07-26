package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * School表实体类
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements Serializable {
    private static final long serialVersionUID = 352773932292207260L;
    /**
     * 自增主键
     */
    private Long id;
    /**
     * 学校
     */
    private String name;
    /**
     * 性质
     */
    private String type;
    /**
     * 最低分
     */
    private Long score;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long creater;
    /**
     * 有效标识
     */
    private Long validFlag;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 分段
     */
    private String level;


}

