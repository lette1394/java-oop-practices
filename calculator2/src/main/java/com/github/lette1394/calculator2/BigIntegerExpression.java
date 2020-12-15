package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import java.math.BigInteger;

public class BigIntegerExpression implements Expression {
  private final BigInteger value;

  public BigIntegerExpression(BigInteger value) {
    this.value = value;
  }

  public BigIntegerExpression(String value) {
    this(new BigInteger(value));
  }

  public BigIntegerExpression(long value) {
    this(Long.toString(value));
  }

  @Override
  public Result evaluate() {
    return of(value);
  }
}
