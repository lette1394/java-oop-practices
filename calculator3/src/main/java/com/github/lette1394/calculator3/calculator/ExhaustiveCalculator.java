package com.github.lette1394.calculator3.calculator;

import com.github.lette1394.calculator3.expression.AddExpression;
import com.github.lette1394.calculator3.expression.AdderInNumeric;
import com.github.lette1394.calculator3.expression.Expression;
import java.util.regex.Pattern;

public class ExhaustiveCalculator implements Calculator {
  private static Pattern pattern = Pattern.compile("-?\\d+|-?\\d+\\.\\d+");
  private final Expression expression;


  public ExhaustiveCalculator() {
    expression = new AddExpression(new AdderInNumeric());
  }

  @Override
  public String evaluate(String expression) {
    if (pattern.matcher(expression).matches()) {
      return expression;
    }

    return this.expression.evaluate(expression);
  }
}
