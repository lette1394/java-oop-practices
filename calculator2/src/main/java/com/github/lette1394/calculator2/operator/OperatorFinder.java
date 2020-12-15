package com.github.lette1394.calculator2.operator;

public interface OperatorFinder {
  Operator find(String operatorAsString) throws OperatorNotFoundException;
}