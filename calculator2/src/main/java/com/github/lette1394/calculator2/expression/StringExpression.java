package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Long.parseLong;

import com.github.lette1394.calculator2.result.OverflowException;
import com.github.lette1394.calculator2.result.Result;
import com.github.lette1394.calculator2.result.UnderflowException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StringExpression implements Expression {
  private final String value;

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return of(parseLong(value.trim()));
  }

  @Override
  public String toString() {
    return value;
  }
}
