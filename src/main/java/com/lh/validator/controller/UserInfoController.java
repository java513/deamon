package com.lh.validator.controller;

import com.lh.validator.bean.UserInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.Default;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-28 21:29
 **/
@RestController
//@Validated 表示整个类都启用校验，如果碰到入参含有bean validation注解的话，就会自动校验,paoc抛出的是ConstraintViolationException异常
public class UserInfoController {

    @GetMapping("/getByName")
    public String getByName(String name) {
        return name + ":ok";
    }

    @GetMapping("/addUser")
    public String addUser(@Valid UserInfo userInfo, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error->
                    System.out.println(error.getObjectName()+":"+error.getDefaultMessage()));
            result.getFieldErrors().forEach(fieldError-> System.out.println(fieldError.getField()+":"
            +fieldError.getDefaultMessage()+",当前没有通过校验规则的值是："+fieldError.getRejectedValue()));
        }
        return "ok";
    }

    /**
     * Validated 分组校验
     * @param userInfo
     * @param result
     * @return
     */
    @GetMapping("/addUser2")
    public String addUser2(@Validated(value ={UserInfo.Add.class, Default.class} )
                                       UserInfo userInfo, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error->
                    System.out.println(error.getObjectName()+":"+error.getDefaultMessage()));
            result.getFieldErrors().forEach(fieldError-> System.out.println(fieldError.getField()+":"
                    +fieldError.getDefaultMessage()+",当前没有通过校验规则的值是："+fieldError.getRejectedValue()));
        }
        return "ok";
    }

    /**
     * Valid 统一异常处理
     * @param userInfo
     * @return
     */
    @GetMapping("/addUser3")
    public String addUser3(@Validated(value ={UserInfo.Update.class, Default.class} )
                                   UserInfo userInfo) {
        return "ok";
    }
}
