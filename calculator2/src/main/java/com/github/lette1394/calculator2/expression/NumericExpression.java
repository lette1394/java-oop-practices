package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.OverflowException;
import com.github.lette1394.calculator2.result.Result;
import com.github.lette1394.calculator2.result.UnderflowException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class NumericExpression implements Expression {
  private final Number value;

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return of(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
