package com.avi.learning.sandbox.scriptexecutor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class GroovyScriptExecutor {

    private final String groovyScript = "";

    private final Invocable inv;

    public GroovyScriptExecutor() throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        if (engine == null) {
            throw new IllegalStateException("Groovy ScriptEngine not found");
        }
        String fact = "def compute(n) { n * 20 }";
        engine.eval(fact);
        this.inv = (Invocable) engine;
    }

    public int getValue(int n) throws ScriptException, NoSuchMethodException {
        Object[] params = {n};
        return (int) inv.invokeFunction("compute", params);
    }
}
