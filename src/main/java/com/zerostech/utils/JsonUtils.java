package com.zerostech.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * Created by gjason on 2017/3/2.
 */
public class JsonUtils {
    /**
     * 检查Json串key
     *
     * @param key
     * @param jsonObject
     * @return
     */
    public static boolean checkJsonKeyIsExist(String key, JSONObject jsonObject) {
        if (jsonObject == null || StringUtils.isBlank(key)) {
            return false;
        }
        if (jsonObject.containsKey(key)) {
            return true;
        }
        return false;
    }
}
