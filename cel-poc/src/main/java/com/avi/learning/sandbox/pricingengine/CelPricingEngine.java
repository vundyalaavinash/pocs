package com.avi.learning.sandbox.pricingengine;

import com.avi.learning.sandbox.model.Order;
import com.avi.learning.sandbox.model.RateCardMetadata;
import dev.cel.common.CelAbstractSyntaxTree;
import dev.cel.common.CelValidationException;
import dev.cel.common.types.CelType;
import dev.cel.common.types.SimpleType;
import dev.cel.compiler.CelCompiler;
import dev.cel.compiler.CelCompilerFactory;
import dev.cel.expr.Expr;
import dev.cel.parser.CelParser;
import dev.cel.runtime.CelEvaluationException;
import dev.cel.runtime.CelRuntime;
import dev.cel.runtime.CelRuntimeFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CelPricingEngine {

    private CelRuntime celRuntime;
    private CelCompiler celCompiler;

    public CelPricingEngine() {
        this.celRuntime = CelRuntimeFactory.standardCelRuntimeBuilder().build();
        this.celCompiler =
                CelCompilerFactory.standardCelCompilerBuilder()
                        .addVar("variableName", SimpleType.ANY)
                        .setResultType(SimpleType.DYN)
                        .build();
    }

    public BigDecimal calculatePrice(Order order, RateCardMetadata rateCardMetadata) throws Exception {
        // Define the CEL expression
        String expression = rateCardMetadata.getPricingRule();

        // Parse the CEL expression
        Expr parsedExpr;
        CelRuntime.Program program = celRuntime.createProgram(ast);
        CelAbstractSyntaxTree ast = exercise2.compile("value < 0", "value", SimpleType.DOUBLE);
        parsedExpr = CelParser.parse(expression);

        // Create variables for CEL evaluation
        Map<String, Object> variables = new HashMap<>();
        variables.put("order", order);
        variables.put("rates", rateCardMetadata.getRates());

        // Evaluate the CEL expression
        Object result = celRuntime.eval(parsedExpr, variables);

        // Convert the result to BigDecimal
        if (result instanceof BigDecimal) {
            return (BigDecimal) result;
        } else {
            throw new IllegalArgumentException("Invalid result type");
        }


        return null;
    }

    CelAbstractSyntaxTree compile(String expression, String variableName, CelType variableType) {
        // Compile (parse + type-check) the expression
        // CelCompiler is immutable and when statically configured can be moved to a static final

        try {
            return celCompiler.compile(expression).getAst();
        } catch (CelValidationException e) {
            throw new IllegalArgumentException(
                    "Failed to compile expression. Reason: " + e.getMessage(), e);
        }
    }

    public Object eval(CelAbstractSyntaxTree ast, Map<String, ?> parameterValues) {
        CelRuntime celRuntime = CelRuntimeFactory.standardCelRuntimeBuilder().build();
        try {
            CelRuntime.Program program = celRuntime.createProgram(ast);

            // Evaluate using the provided map as input variables (key: variableName, value:
            // variableValue)
            return program.eval(parameterValues);
        } catch (CelEvaluationException e) {
            // Report any evaluation errors, if present
            throw new IllegalArgumentException(
                    "Evaluation error has occurred. Reason: " + e.getMessage(), e);
        }
    }
}
