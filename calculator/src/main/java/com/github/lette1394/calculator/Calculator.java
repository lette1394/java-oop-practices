package com.github.lette1394.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Calculator {
  public long calculate(String expression) {
    final List<Operator> operators = Arrays
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

    final List<Operand> operands = Arrays
      .stream(expression.split("[+-]"))
      .map(String::trim)
      .map(NumberOperand::of)
      .collect(Collectors.toList());

    AtomicInteger index = new AtomicInteger(0);
    return operands
      .stream()
      .reduce((a, b) -> operators
        .get(index.getAndIncrement())
        .apply(a, b))
      .orElseThrow()
      .asLong();
  }
}
