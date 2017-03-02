package com.zerostech.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjason on 2017/3/2.
 * 类型转换工具类
 */
public class ConvertUtils {

    /**
     * InputStream转换成byte
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] convertByte(InputStream inputStream) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 1024)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    /**
     * Bean转换为Map
     *
     * @param obj
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map<String, String> transformBeanToMap(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        return transform(map, beanInfo.getPropertyDescriptors(), obj);

    }

    /**
     * Bean转换为Map
     *
     * @param obj
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> transformBeanToObjectMap(Object obj) throws IntrospectionException,
            InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        return transform(map, beanInfo.getPropertyDescriptors(), obj);

    }

    private final static Map transform(Map map, PropertyDescriptor[] propertyDescriptors, Object obj) throws
            InvocationTargetException, IllegalAccessException {
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // filter the class's property
            if (!key.equals("class")) {
                // get method
                Method getter = property.getReadMethod();
                String value = null;
                if (null == getter.invoke(obj)) {
                    continue;
                }
                value = String.valueOf(getter.invoke(obj));
                map.put(key, value);
            }

        }
        return map;
    }
}
