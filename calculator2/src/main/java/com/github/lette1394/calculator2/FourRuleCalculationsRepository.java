package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

public class FourRuleCalculationsRepository implements OperatorRepository {
  private final Map<String, Operator> operators = new HashMap<>();

  public FourRuleCalculationsRepository() {
    operators.put("+", OperatorFactory.add());
    operators.put("-", OperatorFactory.subtract());
    operators.put("*", OperatorFactory.multiply());
    operators.put("/", OperatorFactory.divide());
  }

  @Override
  public Operator find(String operatorAsString) {
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
