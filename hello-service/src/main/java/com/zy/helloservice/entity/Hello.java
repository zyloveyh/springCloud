package com.zy.helloservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Hello {
    private String key;
    private String world;
}
