package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import java.math.BigInteger;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BigIntegerExpression implements Expression {
  private final BigInteger value;

  @Override
  public Result evaluate() {
    return of(value);
  }
}
