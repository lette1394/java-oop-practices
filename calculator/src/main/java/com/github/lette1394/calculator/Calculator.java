package com.github.lette1394.calculator;

import com.google.common.collect.Streams;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Calculator {

  public static void main(String[] args) {
    final long ret = new NumberOperand(2)
      .apply(new AddOperator(), new NumberOperand(5))
      .apply(new SubtractOperator(), new NumberOperand(3))
      .asLong();

    System.out.println(ret);
  }

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

    final Iterator<Operator> iterator = operators.iterator();
    return operands
      .stream()
      .reduce((left, right) -> left.apply(iterator.next(), right))
      .orElseThrow()
      .asLong();
  }
}
