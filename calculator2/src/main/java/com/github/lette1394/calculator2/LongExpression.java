package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongExpression implements Expression {
  private final long value;

  @Override
  public long evaluate() {
    return value;
  }
}
