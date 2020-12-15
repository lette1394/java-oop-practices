package com.github.lette1394.calculator2.operator;

import static java.lang.String.format;

import com.github.lette1394.calculator2.Contracts;
import java.util.HashMap;
import java.util.Map;

public class NumericFourRulesFinder implements OperatorFinder {
  private final Map<String, Operator> operators = new HashMap<>();

  public NumericFourRulesFinder(OperatorFactory operatorFactory) {
    operators.put("+", operatorFactory.add());
    operators.put("-", operatorFactory.subtract());
    operators.put("*", operatorFactory.multiply());
    operators.put("/", operatorFactory.divide());
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
