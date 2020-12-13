package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.of;

public class ExhaustiveParser implements Parser {

  @Override
  public Expression parse(String expression) {
    final String[] s = expression.split(" ");
    return new AddExpression(of(s[0]), of(s[2]));
  }
}
