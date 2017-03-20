package com.zerostech.validate;

import com.zerostech.annotation.ContainEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by gjason on 2017/3/17.
 * 校验是否属于指定枚举类型
 */
public class ContainEnumValidator implements ConstraintValidator<ContainEnum, Object> {
    private static Object[] objects;

    @Override
    public void initialize(ContainEnum constraintAnnotation) {
        Class cls = constraintAnnotation.cls();
        if (cls.isEnum()) {
            objects = cls.getEnumConstants();
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean flg = false;
        for (int i = 0; i < objects.length; i++) {
            if (value == null || objects[i].toString().equals(value)) {
                flg = true;
                break;
            }
        }
        return flg;
    }
}
