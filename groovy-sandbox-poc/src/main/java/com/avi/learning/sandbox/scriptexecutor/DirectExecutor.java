package com.avi.learning.sandbox.scriptexecutor;

import java.util.Map;

public class DirectExecutor {

    public int getValue(Map<String, Integer> context) {
        return context.get("n") * 20;
    }
}
