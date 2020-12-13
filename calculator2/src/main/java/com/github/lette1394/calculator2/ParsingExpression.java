package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.add;
import static com.github.lette1394.calculator2.Expressions.of;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParsingExpression implements Expression {
  private final String value;

  @Override
  public long evaluate() {
    final String[] s = value.split(" ");
    return add(of(s[0]), of(s[2])).evaluate();
  }
}