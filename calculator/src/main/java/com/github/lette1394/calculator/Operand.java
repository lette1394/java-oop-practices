package com.github.lette1394.calculator;

public interface Operand {
  Operand apply(Operator operator, Operand other);

  long asLong();
}
