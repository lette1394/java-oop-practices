package com.github.lette1394.calculator2.operator;

public interface OperatorFinder {
  BinaryOperator find(String operatorAsString) throws OperatorNotFoundException;
}
