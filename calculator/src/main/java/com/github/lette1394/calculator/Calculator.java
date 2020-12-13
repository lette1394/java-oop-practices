package com.github.lette1394.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Calculator {
  public long calculate(String expression) {
    final List<String> operators = Arrays
      .stream(expression.split("[^+-]"))
      .map(String::trim)
      .filter(StringUtils::isNotBlank)
      .collect(Collectors.toList());

    final List<Long> operands = Arrays
      .stream(expression.split("[+-]"))
      .map(String::trim)
      .map(Long::parseLong)
      .collect(Collectors.toList());

    AtomicInteger index = new AtomicInteger(0);
      return operands
        .stream()
        .reduce((a, b) -> {
          final int i = index.getAndIncrement();
          if (operators.get(i).equals("+")) {
            return a + b;
          }
          if (operators.get(i).equals("-")) {
            return a - b;
          }
          throw new RuntimeException();
        })
        .orElse(-1L);
  }
}
