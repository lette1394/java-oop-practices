package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

public class BigIntegerOperatorRepository implements OperatorRepository {
  private final Map<String, Operator> operators = new HashMap<>();

  public BigIntegerOperatorRepository() {
    operators.put("+", OperatorFactory.bigIntegerAdd());
    operators.put("-", OperatorFactory.bigIntegerSubtract());
    operators.put("*", OperatorFactory.bigIntegerMultiply());
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
