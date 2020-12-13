package com.github.lette1394.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Expression {
  private final List<Operator> operators;
  private final List<Operand> operands;

  public Expression(String expression) {
    this.operators = Arrays
      .stream(expression.split("[^+-]"))
      .map(String::trim)
      .filter(StringUtils::isNotBlank)
      .map(str -> {
        if (str.equals("+")) {
          return new AddOperator();
        }
        if (str.equals("-")) {
          return new SubtractOperator();
        }
        throw new RuntimeException();
      })
      .collect(Collectors.toList());

    this.operands = Arrays
      .stream(expression.split("[+-]"))
      .map(String::trim)
      .map(NumberOperand::of)
      .collect(Collectors.toList());
  }

  Operators operators() {
    return new Operators(operators);
  }

  Operands operands() {
    return new Operands(operands);
  }
}
