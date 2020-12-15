package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

public class NumericFourRulesRepository implements OperatorRepository {
  private final Map<String, Operator> operators = new HashMap<>();

  public NumericFourRulesRepository() {
    operators.put("+", Operators.numberAdd());
    operators.put("-", Operators.numberSubtract());
    operators.put("*", Operators.numberMultiply());
    operators.put("/", Operators.divide());
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
