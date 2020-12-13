package com.github.lette1394.calculator;

public class Calculator {
  public long calculate(String rawExpression) {
    final Expression expression = new Expression(rawExpression);
    final Operands operands = expression.operands();
    final Operators operators = expression.operators();

    final Operand result = operands.apply(operators.asList());
    return result.asLong();
  }
}
