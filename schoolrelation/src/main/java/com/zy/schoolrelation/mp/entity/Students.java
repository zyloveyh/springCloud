package com.zy.schoolrelation.mp.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zy
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 名字
     */

    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 老师
     */
    private Integer teacher;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * lastName
     */
    private String lastName;


}
