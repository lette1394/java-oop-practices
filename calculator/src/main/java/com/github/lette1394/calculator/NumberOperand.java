package com.github.lette1394.calculator;

public class NumberOperand implements Operand {
  private final long value;

  public NumberOperand(long value) {
    this.value = value;
  }

  public static Operand of(String value) {
    return of(Long.parseLong(value));
  }

  public static Operand of(long value) {
    return new NumberOperand(value);
  }

  @Override
  public Operand apply(Operator operator, Operand other) {
    return operator.apply(this, other);
  }

  @Override
  public long asLong() {
    return value;
  }
}
