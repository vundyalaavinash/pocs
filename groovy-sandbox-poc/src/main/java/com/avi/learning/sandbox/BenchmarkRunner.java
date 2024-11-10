package com.avi.learning.sandbox;

import com.avi.learning.sandbox.models.TestClass;
import com.avi.learning.sandbox.scriptexecutor.MVELExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
//        org.openjdk.jmh.Main.main(args);
        try {
            MVELExecutor executor = new MVELExecutor();
//            System.out.println(executor.getValue(20));
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json")) {
            ObjectMapper mapper = new ObjectMapper();
            final byte[] bytes = in.readAllBytes();
            String jsonText = new String(bytes);
            Map<String, Object> map = mapper.readValue(jsonText, Map.class);
            TestClass testClass = mapper.readValue(jsonText, TestClass.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}