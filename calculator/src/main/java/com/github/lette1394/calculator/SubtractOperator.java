package com.github.lette1394.calculator;

public class SubtractOperator implements Operator {
  @Override
  public Operand apply(Operand left, Operand right) {
    return new NumberOperand(left.asLong() - right.asLong());
  }
}
