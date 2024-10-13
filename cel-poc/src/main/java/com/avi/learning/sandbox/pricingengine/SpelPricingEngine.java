package com.avi.learning.sandbox.pricingengine;

import com.avi.learning.sandbox.model.Order;
import com.avi.learning.sandbox.model.RateCardMetadata;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;

public class SpelPricingEngine {

    public BigDecimal calculatePrice(Order order, RateCardMetadata rateCardMetadata) {
        // Initialize SpEL parser
        ExpressionParser parser = new SpelExpressionParser();

        // Define the pricing rule (CEL-like expression)
        String expression = rateCardMetadata.getPricingRule();

        // Create a new evaluation context and populate it with necessary variables
        StandardEvaluationContext evalContext = new StandardEvaluationContext();
        evalContext.setVariable("order", order);
        evalContext.setVariable("rates", rateCardMetadata.getRates());

        // Evaluate the expression
        Object result = parser.parseExpression(expression).getValue(evalContext);

        // Convert the result to BigDecimal
        if (result instanceof BigDecimal) {
            return (BigDecimal) result;
        } else if (result instanceof Double) {
            return BigDecimal.valueOf((Double) result);
        } else {
            throw new IllegalArgumentException("Invalid result type: " + result.getClass().getName());
        }
    }
}
