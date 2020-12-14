package com.github.lette1394.calculator2;

import java.math.BigDecimal;

public class BigDecimalResult implements Result {
  private final BigDecimal bigDecimal;

  public BigDecimalResult(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  @Override
  public long asLong() {
    return bigDecimal.longValue();
  }

  @Override
  public long asLongExact() {
    return bigDecimal.longValueExact();
  }

  @Override
  public double asDouble() {
    return bigDecimal.doubleValue();
  }

  @Override
  public String asString() {
    return bigDecimal.toString();
  }

  @Override
  public String toString() {
    return asString();
  }
}
