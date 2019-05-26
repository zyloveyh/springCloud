package com.zy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students implements Serializable {

    private Integer id;

    private String name;

    private String address;

    private Integer age;

    private String sex;

    private Integer teacher;

    private String lastName;

    private String fatherName;


}