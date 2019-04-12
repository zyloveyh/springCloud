package com.zy.helloservice.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sun.tools.javah.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    private Integer id;

    private String name;

    private String address;

    private Integer age;

    private String sex;

    private Integer teacher;

    private Date createtime;

    private String lastName;

    @TableField(exist = false)
    private String fatherName;


}