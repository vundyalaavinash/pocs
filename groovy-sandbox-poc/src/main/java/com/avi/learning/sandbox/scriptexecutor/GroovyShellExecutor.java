package com.avi.learning.sandbox.scriptexecutor;

import groovy.lang.GroovyShell;

import java.util.Map;


public class GroovyShellExecutor {

    private final GroovyShell shell;

    private final String groovyScript = "";

    public GroovyShellExecutor() {
        shell = new GroovyShell();
    }

    public int getValue(Map<String, Object> context) {
        shell.setVariable("n", context.get("n"));
        return (int) shell.evaluate("n * 20");
    }
}
