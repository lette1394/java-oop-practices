package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.Result;

abstract class NumericBinaryOperatorExpression extends TwoOperandExpression {
  public NumericBinaryOperatorExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    final Number whenUseDouble = whenUseDouble(left().asDouble(), right().asDouble());
    final Number whenUseLong = whenUseLong(left().asLongExact(), right().asLongExact());
    if (whenUseLong.doubleValue() == whenUseDouble.doubleValue()) {
      return of(whenUseLong);
    }
    return of(whenUseDouble);
  }

  protected abstract Number whenUseLong(long left, long right);

  protected abstract Number whenUseDouble(double left, double right);
}
