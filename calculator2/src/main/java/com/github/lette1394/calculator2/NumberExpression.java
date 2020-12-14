package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberExpression implements Expression {
  private final Number value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}
