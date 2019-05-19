package com.zy.schoolrelation.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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
    @TableId(type = IdType.AUTO)  //主键策略
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
    private Timestamp createTime;

    /**
     * lastName
     */
    @TableField("last_name")
    private String lastName;


}
