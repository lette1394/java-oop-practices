package com.github.lette1394.calculator2.operator;

public interface OperatorFactory {
  BinaryOperator add();

  BinaryOperator subtract();

  BinaryOperator multiply();

  BinaryOperator divide();
}
