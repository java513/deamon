package com.lh.validator.bean;

import javax.validation.constraints.NotEmpty;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-27 22:44
 **/
public class Grade {
    @NotEmpty
    private String no;

    public Grade(String no) {
        this.no = no;
    }
}
