package com.lh.validator.util;

import com.lh.validator.bean.UserInfo;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-27 21:34
 **/
public class ValidationUtil {
    //线程安全
    private static Validator validator;
    private static Validator failFastValidator;
    //非bean参数校验
    private static ExecutableValidator executableValidator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //快速失败
        failFastValidator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
        //校验入参或返回值
        executableValidator= validator.forExecutables();
    }

    /**
     * 校验非bean
     * @param t
     * @param method
     * @param parameterValues
     * @param groups
     * @param <T>
     * @return
     */
    public static <T> List<String> validNotBean(T t, Method method,Object[] parameterValues,Class<T>... groups) {
        Set<ConstraintViolation<Object>> set = executableValidator.validateParameters(t, method, parameterValues, groups);
        List<String> list = set.stream().map(v -> "属性：" + v.getPropertyPath() + ",属性的值：" + v.getInvalidValue()
                + ",校验未通过的提示信息：" + v.getMessage()).collect(Collectors.toList());
        return list;
    }

    public static List<String> valid(UserInfo userInfo,Class<?>... groups) {
        //如果被校验对象userInfo没有校验通过，则set里面有未校验通过的信息
        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo,groups);

        List<String> list = violations.stream().map(v -> "属性：" + v.getPropertyPath() + ",属性的值：" + v.getInvalidValue()
                + ",校验未通过的提示信息：" + v.getMessage()).collect(Collectors.toList());
        return list;
    }

    /**
     * 校验快速失败（有一个校验不通过，后面都不再校验）
     * @param userInfo
     * @param groups
     * @return
     */
    public static List<String> validFailFast(UserInfo userInfo,Class<?>... groups) {
        //如果被校验对象userInfo没有校验通过，则set里面有未校验通过的信息
        Set<ConstraintViolation<UserInfo>> violations = failFastValidator.validate(userInfo,groups);

        List<String> list = violations.stream().map(v -> "属性：" + v.getPropertyPath() + ",属性的值：" + v.getInvalidValue()
                + ",校验未通过的提示信息：" + v.getMessage()).collect(Collectors.toList());
        return list;
    }

}
