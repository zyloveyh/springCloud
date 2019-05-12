package com.zy.schoolrelation.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zy
 * @since 2019-04-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private Integer id;
    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 老师
     */
    @TableField("teacher")
    private Integer teacher;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * lastName
     */
    @TableField("last_name")
    private String lastName;


}
