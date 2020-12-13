package com.github.lette1394.calculator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Operands {
  private final List<Operand> operands;

  public Operands(List<Operand> operands) {
    this.operands = operands;
  }

  public Operand reduce(List<Operator> operators) {
//    IntStream
//      .range(0, operands.size() - 1)
//      .mapToObj(i -> operators.get(i).apply(operands.get(i), operands.get(i + 1)))
//      .collect(Collectors.toList());
//
//    stream.reduce((o1, o2) -> operatorStream)

    return null;
  }
}
