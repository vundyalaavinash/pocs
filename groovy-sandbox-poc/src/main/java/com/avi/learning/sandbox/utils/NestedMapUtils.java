package com.avi.learning.sandbox.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class NestedMapUtils {

    public static Object getValue(Map<String, Object> context, String key) {
        String[] keys = key.split("\\.");
        Object value = context;
        for (String k : keys) {
            if (value instanceof Map<?, ?> nestedMap) {
                value = nestedMap.get(k);
            } else {
                return null;
            }
        }
        return value;
    }
}
