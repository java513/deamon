package com.lh.validator.test;

import com.lh.validator.bean.Grade;
import com.lh.validator.bean.UserInfo;
import com.lh.validator.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: deamon
 * @description: validator测试类
 * @author: lh
 * @date: 2021-10-27 21:12
 **/
public class ValidatorTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1l);
        userInfo.setName("海棠");
        userInfo.setAge(17);
        userInfo.setEmail("110110@qq.com");
        userInfo.setPhone("13034567896");
        userInfo.setBirthday(LocalDateTime.now().minusDays(1l));
        userInfo.setPersonalPage("http://www.aa.cn");
        //traditionValid(userInfo);
        //分组校验
        //List<String> list = ValidationUtil.valid(userInfo, UserInfo.Update.class);
        //List<String> list = ValidationUtil.valid(userInfo);
        //级联校验
        Grade grade = new Grade("5");
        userInfo.setGrade(grade);
        //自定义注解校验
        userInfo.setUserStatus(1003);
        //List<String> list = ValidationUtil.valid(userInfo);
        List<String> list = ValidationUtil.validFailFast(userInfo);

        System.out.println(list);
    }

    /**
     * 传统的参数校验
     * @param userInfo
     */
    private static void traditionValid(UserInfo userInfo) {
        String name = userInfo.getName();
        if (name == null || "".equals(name) ||" ".equals(name.trim())) {
            throw new RuntimeException("name不符合校验规则");
        }
        Integer age = userInfo.getAge();
        boolean ageValida = age > 1 && age < 150;
        if (!ageValida) {
            throw new RuntimeException("age不符合校验规则");
        }
        //。。。。。。
    }
}
