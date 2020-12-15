package com.github.lette1394.calculator2;

import com.github.lette1394.calculator2.expression.ArrayBasedExpressionFactory;
import com.github.lette1394.calculator2.expression.BigIntegerOperatorFactory;
import com.github.lette1394.calculator2.expression.Expression;
import com.github.lette1394.calculator2.expression.ExpressionFactory;
import com.github.lette1394.calculator2.expression.FallbackExpressionFactory;
import com.github.lette1394.calculator2.expression.NumericOperatorFactory;
import com.github.lette1394.calculator2.expression.PriorityParsingExpression;
import com.github.lette1394.calculator2.expression.TypeExpressionFactory;

public class Calculator {
  public static Result calculate(String expression) {
    final OperatorFinder operatorFinder = new FindFirstOperatorFinder(
      new FindFirstOperatorFinder(
        new NumericFourRulesFinder(
          new FallbackOperatorFactory(
            NumericOperatorFactory.INSTANCE,
            BigIntegerOperatorFactory.INSTANCE))));

    final ExpressionFactory expressionFactory = new FallbackExpressionFactory(
      TypeExpressionFactory.INSTANCE,
      ArrayBasedExpressionFactory.INSTANCE
    );

    final Expression compositeExpression =
      new PriorityParsingExpression(
        expression,
        operatorFinder,
        expressionFactory);

    return compositeExpression.evaluate();
  }
}
