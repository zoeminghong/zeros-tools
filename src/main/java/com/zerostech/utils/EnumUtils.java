package com.zerostech.utils;

import java.util.EnumSet;

/**
 * Created by gJason on 2016/10/9.
 * 枚举工具类
 */
public class EnumUtils {

    /**
     * 检查枚举值是否包含于该枚举中
     *
     * @param value
     * @param tEnum
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> boolean check(String value, Class<E> tEnum) {
        EnumSet<E> currEnumSet = EnumSet.allOf(tEnum);
        for (E element : currEnumSet) {
            if (element.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
