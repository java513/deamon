package com.lh.validator.manValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义校验注解
 */
@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {UserStatusValidator.class}
)  //@Constraint 说明当前注解由谁完成校验工作
public @interface UserStatus {
    String message() default "user status必须是1000/1001/1002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
