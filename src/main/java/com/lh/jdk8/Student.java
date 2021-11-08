package com.lh.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-11-01 21:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private Integer age;
    private Integer score;
}
