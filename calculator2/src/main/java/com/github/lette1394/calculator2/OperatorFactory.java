package com.github.lette1394.calculator2;

public interface OperatorFactory {
  Operator find(String operatorAsString) throws OperatorNotFoundException;
}
