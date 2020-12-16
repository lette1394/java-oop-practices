package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.Result;
import java.math.BigDecimal;

class BigDecimalExpression implements Expression {
  private final BigDecimal value;

  public BigDecimalExpression(BigDecimal value) {
    this.value = value;
  }

  public BigDecimalExpression(double value) {
    this(BigDecimal.valueOf(value));
  }

  @Override
  public Result evaluate() {
    return of(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
