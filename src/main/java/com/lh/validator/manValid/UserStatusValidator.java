package com.lh.validator.manValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: deamon
 * @description: 自定义校验类
 * @author: lh
 * @date: 2021-10-27 22:52
 **/
public class UserStatusValidator implements ConstraintValidator<UserStatus,Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Set<Integer> set =  new HashSet<Integer>();
        set.add(1000);
        set.add(1001);
        set.add(1002);
        return set.contains(value);
    }

    @Override
    public void initialize(UserStatus constraintAnnotation) {

    }
}
