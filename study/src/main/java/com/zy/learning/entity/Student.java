package com.zy.learning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student extends Person {
    public int age;
    public String name;
    private String address;
}
