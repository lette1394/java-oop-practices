package com.github.lette1394.calculator2.result;

class NumericResult implements Result {
  private final Number number;

  public NumericResult(Number number) {
    this.number = number;
  }

  @Override
  public long asLong() {
    return number.longValue();
  }

  @Override
  public long asLongExact() throws OverflowException, UnderflowException {
    return number.longValue();
  }

  @Override
  public double asDouble() {
    return number.doubleValue();
  }

  @Override
  public String asString() {
    return number.toString();
  }

  @Override
  public String toString() {
    return asString();
  }
}
