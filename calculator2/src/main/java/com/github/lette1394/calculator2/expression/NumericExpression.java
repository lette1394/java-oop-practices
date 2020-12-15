package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.Results.of;

import com.github.lette1394.calculator2.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumericExpression implements Expression {
  private final Number value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}
