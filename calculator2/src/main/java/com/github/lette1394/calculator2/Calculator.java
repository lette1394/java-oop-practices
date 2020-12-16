package com.github.lette1394.calculator2;

import com.github.lette1394.calculator2.expression.ArrayBasedExpressionFactory;
import com.github.lette1394.calculator2.expression.BigIntegerOperatorFactory;
import com.github.lette1394.calculator2.expression.DecorateOperatorFactory;
import com.github.lette1394.calculator2.expression.Expression;
import com.github.lette1394.calculator2.expression.ExpressionFactory;
import com.github.lette1394.calculator2.expression.FallbackExpressionFactory;
import com.github.lette1394.calculator2.expression.NumericBinaryOperatorExpressionFactory;
import com.github.lette1394.calculator2.expression.NumericExpressionFactory;
import com.github.lette1394.calculator2.expression.PreemptiveTimeoutBinaryOperator;
import com.github.lette1394.calculator2.expression.PriorityParsingExpression;
import com.github.lette1394.calculator2.operator.FallbackOperatorFactory;
import com.github.lette1394.calculator2.operator.FindFirstOperatorFinder;
import com.github.lette1394.calculator2.operator.NumericFourRulesFinder;
import com.github.lette1394.calculator2.operator.OperatorFactory;
import com.github.lette1394.calculator2.operator.OperatorFinder;
import com.github.lette1394.calculator2.result.Result;
import java.time.Duration;

public class Calculator {
  public static Result calculate(String expression) {
    final OperatorFactory operatorFactory =
      new DecorateOperatorFactory(
        operator -> new PreemptiveTimeoutBinaryOperator(operator, Duration.ofMillis(100)),
        new FallbackOperatorFactory(
          NumericBinaryOperatorExpressionFactory.INSTANCE,
          BigIntegerOperatorFactory.INSTANCE));

    final OperatorFinder operatorFinder = new FindFirstOperatorFinder(
      new FindFirstOperatorFinder(
        new NumericFourRulesFinder(operatorFactory)));

    final ExpressionFactory expressionFactory = new FallbackExpressionFactory(
      NumericExpressionFactory.INSTANCE,
      ArrayBasedExpressionFactory.INSTANCE
    );

    final Expression compositeExpression =
      new PriorityParsingExpression(
        expression,
        operatorFinder,
        expressionFactory);

//    Calculator calculator = Calculator
//      .builder()
//      .timeout(Duration.ofSeconds(5))
//      .build();

    return compositeExpression.evaluate();
  }

  public static CalculatorBuilder builder() {
    return null;
  }
}
