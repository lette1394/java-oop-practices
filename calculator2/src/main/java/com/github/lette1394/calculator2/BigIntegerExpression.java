package com.github.lette1394.calculator2;

import java.math.BigInteger;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BigIntegerExpression implements Expression {
  private final BigInteger value;

  @Override
  public long evaluate() {
    return value.longValueExact();
  }
}
