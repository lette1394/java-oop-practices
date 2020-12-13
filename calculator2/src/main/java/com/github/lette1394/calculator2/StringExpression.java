package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringExpression implements Expression {
  private final String value;

  @Override
  public long evaluate() {
    return Long.parseLong(value.trim());
  }
}
