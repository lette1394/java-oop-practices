package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongExpression implements Expression {
  private final long value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}
