package com.avi.learning.sandbox.scriptexecutor;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

public class MVELExecutor {

    public int getValue(int n) {
        Map<String, Object> context = new HashMap<>();
        context.put("n", n);
        return MVEL.eval("n*20", n, Integer.class);
    }
}
