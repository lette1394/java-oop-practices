package com.github.lette1394.calculator;

public class PartialOperand implements Operand {
  @Override
  public Operand apply(Operator operator, Operand other) {
    return null;
  }

  @Override
  public long asLong() {
    return 0;
  }
}
