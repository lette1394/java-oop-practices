package com.github.lette1394.calculator3.calculator;

import com.github.lette1394.calculator3.expression.AddExpression;
import com.github.lette1394.calculator3.expression.AdderInNumeric;
import com.github.lette1394.calculator3.expression.Expression;

public class ExhaustiveCalculator implements Calculator {
  private final Expression expression;

  public ExhaustiveCalculator() {
    expression = new AddExpression(new AdderInNumeric());
  }

  @Override
  public String evaluate(String expression) {
    return this.expression.evaluate(expression);
  }
}
