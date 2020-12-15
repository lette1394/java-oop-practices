package com.github.lette1394.calculator2;

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
