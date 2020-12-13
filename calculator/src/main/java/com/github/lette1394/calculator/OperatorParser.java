package com.github.lette1394.calculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class OperatorParser implements Parser<Operator> {

  private final Map<String, Operator> operators;

  public OperatorParser(Map<String, Operator> operators) {
    this.operators = operators;
  }

  public OperatorParser() {
    operators = new HashMap<>();
    operators.put("+", new AddOperator());
    operators.put("-", new SubtractOperator());
    operators.put("*", new MultiplyOperator());
  }

  @Override
  public List<Operator> parse(String expression) {
    return Arrays
      .stream(expression.split(String.format("[^%s]", String.join("", operators.keySet()))))
      .map(String::trim)
      .filter(StringUtils::isNotBlank)
      .map(str -> operators.get(str))
      .collect(Collectors.toList());
  }
}
