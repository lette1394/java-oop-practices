package com.github.lette1394.calculator2;

public class ParsingExpression implements Expression {
  private final String expression;
  private final Parser parser;

  public ParsingExpression(String expression, Parser parser) {
    this.expression = expression;
    this.parser = parser;
  }

  @Override
  public long evaluate() {
    return parser
      .parse(expression)
      .evaluate();
  }
}