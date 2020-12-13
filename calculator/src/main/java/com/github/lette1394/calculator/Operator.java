package com.github.lette1394.calculator;

public interface Operator {
  Operand apply(Operand left, Operand right);

}
