package com.github.lette1394.calculator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Operands {
  private final List<Operand> operands;

  public Operands(List<Operand> operands) {
    this.operands = operands;
  }

  public Operand apply(List<Operator> operators) {
    final Iterator<Operator> iterator = operators.iterator();
    return operands
      .stream()
      .reduce((left, right) -> left.apply(iterator.next(), right))
      .orElseThrow();
  }
}
