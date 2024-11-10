package com.avi.learning.sandbox.benchmarks;

import com.avi.learning.sandbox.scriptexecutor.CELExecutor;
import com.avi.learning.sandbox.scriptexecutor.DirectExecutor;
import com.avi.learning.sandbox.scriptexecutor.GroovyShellExecutor;
import com.avi.learning.sandbox.scriptexecutor.MVELExecutor;
import dev.cel.common.CelValidationException;
import dev.cel.runtime.CelEvaluationException;
import org.openjdk.jmh.annotations.*;

import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class ScriptVsDirectBenchmark {

    Random random = new Random();
    GroovyShellExecutor scriptExecutor;
    DirectExecutor directExecutor;
    MVELExecutor mvelExecutor;
    CELExecutor celEvaluator;

    @Setup
    public void setup() throws ScriptException, CelValidationException, CelEvaluationException {
        scriptExecutor = new GroovyShellExecutor();
        celEvaluator = new CELExecutor();
        directExecutor = new DirectExecutor();
        mvelExecutor = new MVELExecutor();
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runScriptExecutor() {
        try {
            Map<String, Object> context = new HashMap<>();
            context.put("n", random.nextInt(100));
            scriptExecutor.getValue(context);
        } catch (Exception ex) {
            System.out.println("Script Exception: " + ex.getMessage());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runDirectExecutor() {
        try {
            Map<String, Integer> context = new HashMap<>();
            context.put("n", random.nextInt(100));
            directExecutor.getValue(context);
        } catch (Exception ex) {
            System.out.println("Direct Exception: " + ex.getMessage());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runMVELExecutor() {
        try {
            Map<String, Object> context = new HashMap<>();
            context.put("n", random.nextInt(100));
            mvelExecutor.getValue(context);
        } catch (Exception ex) {
            System.out.println("MVEL Exception: " + ex.getMessage());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runCELExecutor() {
        try {
            Map<String, Object> context = new HashMap<>();
            context.put("n", random.nextInt(100));
            celEvaluator.getValue(context);
        } catch (Exception ex) {
            System.out.println("CEL Exception: " + ex.getMessage());
        }
    }
}
