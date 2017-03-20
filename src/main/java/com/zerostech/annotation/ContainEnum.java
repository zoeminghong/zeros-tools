package com.zerostech.annotation;

import com.zerostech.validate.ContainEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by gjason on 2017/3/17.
 */
@Constraint(validatedBy = ContainEnumValidator.class) //具体的实现
@Target( { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface ContainEnum {
    String message() default "不属于当前枚举类型"; //提示信息,可以写死,可以填写国际化的key

    Class<?> cls();

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
