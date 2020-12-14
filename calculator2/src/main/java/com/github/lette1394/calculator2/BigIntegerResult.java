package com.github.lette1394.calculator2;

import java.math.BigInteger;

public class BigIntegerResult implements Result {
  private final BigInteger value;

  public BigIntegerResult(BigInteger value) {
    this.value = value;
  }

  @Override
  public long asLong() {
    return value.longValue();
  }

  @Override
  public long asLongExact() {
    return value.longValueExact();
  }

  @Override
  public double asDouble() {
    return value.doubleValue();
  }

  @Override
  public String asString() {
    return value.toString();
  }
}
