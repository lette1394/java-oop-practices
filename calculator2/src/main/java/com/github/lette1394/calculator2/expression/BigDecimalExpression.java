package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.Results.of;

import com.github.lette1394.calculator2.Result;
import java.math.BigDecimal;

public class BigDecimalExpression implements Expression {
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
}
