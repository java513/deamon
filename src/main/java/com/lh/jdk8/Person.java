package com.lh.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-31 15:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {
    private String name;
    private Integer age;
}
