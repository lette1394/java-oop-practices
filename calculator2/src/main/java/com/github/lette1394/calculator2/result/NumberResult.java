package com.github.lette1394.calculator2.result;

public class NumberResult implements Result {
  private final Number number;

  public NumberResult(Number number) {
    this.number = number;
  }

  @Override
  public long asLong() {
    return number.longValue();
  }

  @Override
  public long asLongExact() {
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
