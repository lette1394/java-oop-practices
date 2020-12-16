package com.github.lette1394.calculator2.result;

import java.math.BigInteger;

class BigIntegerResult implements Result {
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

  @Override
  public String toString() {
    return asString();
  }
}
