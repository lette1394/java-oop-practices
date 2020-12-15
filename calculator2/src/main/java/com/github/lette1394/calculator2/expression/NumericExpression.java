package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class NumericExpression implements Expression {
  private final Number value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}