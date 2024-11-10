package com.avi.learning.sandbox.scriptexecutor;

import dev.cel.common.CelAbstractSyntaxTree;
import dev.cel.common.CelOptions;
import dev.cel.common.CelValidationException;
import dev.cel.common.types.SimpleType;
import dev.cel.compiler.CelCompiler;
import dev.cel.compiler.CelCompilerFactory;
import dev.cel.runtime.CelEvaluationException;
import dev.cel.runtime.CelRuntime;
import dev.cel.runtime.CelRuntimeFactory;

import java.util.Map;

public class CELExecutor {

    private final CelCompiler compiler;
    private final CelRuntime runtime;
    private final CelAbstractSyntaxTree ast;
    private final CelRuntime.Program program;

    public CELExecutor() throws CelValidationException, CelEvaluationException {
        compiler = CelCompilerFactory.standardCelCompilerBuilder()
                .addVar("n", SimpleType.ANY)
                .setOptions(CelOptions.newBuilder().build())
                .build();
        runtime = CelRuntimeFactory.standardCelRuntimeBuilder().build();
        ast = compiler.compile("n*20").getAst();
        program = runtime.createProgram(ast);

    }

    public double getValue(Map<String, Object> context) throws CelEvaluationException {
        return Double.parseDouble(program.eval(context).toString());
    }
}
