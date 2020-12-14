package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BigDecimalExpression implements Expression {
  private final BigDecimal value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}
