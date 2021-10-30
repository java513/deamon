package com.lh.validator.bean;

import com.lh.validator.manValid.UserStatus;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @program: deamon
 * @description: validator校验
 * @author: lh
 * @date: 2021-10-27 21:08
 **/
public class UserInfo {
    //标记接口 新增组
    public interface Add {}
    //修改组
    public interface Update{}
    /**
     * 不能是null,"","  "
     */
    @Null(groups = {Add.class},message = "新增时id必须为null") //新增组，id必须为null
    @NotNull(groups = Update.class,message = "修改时id不能为null") //修改组，id不能为null
    private Long id;
    /**
     * 不能是null,"","  "
     */
    @NotBlank
    private String name;
    /**
     * 正数数 1-150
     */
    @NotNull
    //@Min(1)@Max(150)
    //@Range(min = 1,max = 150)//闭区间
    @Min(value = 18,message = "年龄小于{value},不能结婚")
    private Integer age;
    /**
     * 邮箱格式
     */
    @NotNull
    @Email
    private String email;
    /**
     * 手机号格式
     */
    @NotEmpty
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$",message = "手机号格式不正确")
    private String phone;
    /**
     * 当前日期之前
     */
    @NotNull
    @Past
    private LocalDateTime birthday;
    /**
     * URL格式
     */
    @URL
    private String personalPage;
    @NotNull
    @Valid //被校验的对象必须加上@valid，才能进行级联校验
    private Grade grade;
    @UserStatus
    private Integer userStatus;

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }
}
