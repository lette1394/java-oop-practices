package com.github.lette1394.calculator2;

public interface OperatorFactory {
  Operator add();

  Operator subtract();

  Operator multiply();

  Operator divide();
}
