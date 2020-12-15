package com.github.lette1394.calculator2;

import com.github.lette1394.calculator2.expression.ArrayBasedExpressionFactory;
import com.github.lette1394.calculator2.expression.BigIntegerOperatorFactory;
import com.github.lette1394.calculator2.expression.Expression;
import com.github.lette1394.calculator2.expression.ExpressionFactory;
import com.github.lette1394.calculator2.expression.FallbackExpressionFactory;
import com.github.lette1394.calculator2.expression.NumericBinaryOperatorExpressionFactory;
import com.github.lette1394.calculator2.expression.PriorityParsingExpression;
import com.github.lette1394.calculator2.expression.MathTypeExpressionFactory;
import com.github.lette1394.calculator2.result.Result;

public class Calculator {
  public static Result calculate(String expression) {
    final OperatorFinder operatorFinder = new FindFirstOperatorFinder(
      new FindFirstOperatorFinder(
        new NumericFourRulesFinder(
          new FallbackOperatorFactory(
            NumericBinaryOperatorExpressionFactory.INSTANCE,
            BigIntegerOperatorFactory.INSTANCE))));

    final ExpressionFactory expressionFactory = new FallbackExpressionFactory(
      MathTypeExpressionFactory.INSTANCE,
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
