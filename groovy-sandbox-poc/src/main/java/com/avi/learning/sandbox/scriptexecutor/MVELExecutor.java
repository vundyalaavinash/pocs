package com.avi.learning.sandbox.scriptexecutor;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

public class MVELExecutor {

    public int getValue(Map<String, Object> context) {
        return MVEL.eval("n*20", context, Integer.class);
    }
}
