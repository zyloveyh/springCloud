package com.zy.learning.entity;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Woman {
    static {
        System.out.println( "woman static 静态代码块");
    }
    private String type;
    private String name;
    private String sex;
    private String age;
}
