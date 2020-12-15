package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

public class BigIntegerOperatorFactory implements OperatorFactory {
  private final Map<String, Operator> operators = new HashMap<>();

  public BigIntegerOperatorFactory() {
    operators.put("+", Operators.bigIntegerAdd());
    operators.put("-", Operators.bigIntegerSubtract());
    operators.put("*", Operators.bigIntegerMultiply());
  }

  @Override
  public Operator find(String operatorAsString) throws OperatorNotFoundException {
    Contracts.requires(
      operators.containsKey(operatorAsString),
      notFoundException(operatorAsString));

    return operators.get(operatorAsString);
  }

  private OperatorNotFoundException notFoundException(String operatorAsString) {
    return new OperatorNotFoundException(
      format("does not support operator: [%s]", operatorAsString));
  }
}
