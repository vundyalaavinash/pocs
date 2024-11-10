package com.avi.learning.sandbox.benchmarks;

import com.avi.learning.sandbox.scriptexecutor.DirectExecutor;
import com.avi.learning.sandbox.scriptexecutor.GroovyScriptExecutor;
import com.avi.learning.sandbox.scriptexecutor.MVELExecutor;
import org.openjdk.jmh.annotations.*;

import javax.script.ScriptException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class ScriptVsDirectBenchmark {

    Random random = new Random();
    GroovyScriptExecutor scriptExecutor;
    DirectExecutor directExecutor;
    MVELExecutor mvelExecutor;

    @Setup
    public void setup() throws ScriptException {
        scriptExecutor = new GroovyScriptExecutor();
        directExecutor = new DirectExecutor();
        mvelExecutor = new MVELExecutor();
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runScriptExecutor() {
        try {
            int n = random.nextInt(100);
            System.out.println("Script Result: " + scriptExecutor.getValue(n) + " - (" + n + ")");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runDirectExecutor() {
        try {
            int n = random.nextInt(100);
            System.out.println("Direct Result: " + directExecutor.getValue(n) + " - (" + n + ")");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void runMVELExecutor() {
        try {
            int n = random.nextInt(100);
            System.out.println("MVEL Result: " + mvelExecutor.getValue(n) + " - (" + n + ")");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
