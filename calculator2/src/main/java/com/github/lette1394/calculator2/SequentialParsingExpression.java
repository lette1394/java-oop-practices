package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequentialParsingExpression implements Expression {
  private final String value;

  @Override
  public long evaluate() {
    return parse().evaluate();
  }

  private Expression parse() {
    final Parser parser = Parser.parse(value);
    final Expression left = parser.left();
    final Expression right = parser.right();

    return parser
      .operator()
      .apply(left, right);
  }
}