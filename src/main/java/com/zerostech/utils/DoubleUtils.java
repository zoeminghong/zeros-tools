package com.zerostech.utils;

import java.math.BigDecimal;

/**
 * Created by gjason on 2017/3/2.
 * double类型相关的工具方法
 */
public class DoubleUtils {

    /**
     * 四舍五入
     *
     * @param count 保留位数
     * @param value 数据值
     * @return String
     */
    public String format(int count, double value) {
        return format(count, value, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 自定义精确模式
     *
     * @param count        保留位数
     * @param value        数据值
     * @param roundingMode BigDecimal.ROUND_HALF_UP
     * @return String
     */
    public String format(int count, double value, int roundingMode) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(count, roundingMode).toString();
    }
}
