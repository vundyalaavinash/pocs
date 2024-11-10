package com.avi.learning.sandbox.benchmarks;

import com.avi.learning.sandbox.models.TestClass;
import com.avi.learning.sandbox.utils.NestedMapUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class MapVsDirectBenchmark {

    private Map<String, Object> map;

    private TestClass testClass;

    @Setup
    public void setup() throws URISyntaxException {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json")) {
            ObjectMapper mapper = new ObjectMapper();
            final byte[] bytes = in.readAllBytes();
            String jsonText = new String(bytes);
            map = mapper.readValue(jsonText, Map.class);
            testClass = mapper.readValue(jsonText, TestClass.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void getUsingMap() {
        NestedMapUtils.getValue(map, "id");
        NestedMapUtils.getValue(map, "subClass.sample");
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void getUsingClass() {
        testClass.getId();
        testClass.getSubClass().getSample();
    }
}
