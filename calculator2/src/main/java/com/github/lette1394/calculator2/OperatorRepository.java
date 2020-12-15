package com.github.lette1394.calculator2;

public interface OperatorRepository {
  Operator find(String operatorAsString) throws OperatorNotFoundException;
}
