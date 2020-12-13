package com.github.lette1394.calculator;

import java.util.List;

public class Expression {
  private final List<Operator> operators;
  private final List<Operand> operands;

  public Expression(String expression) {
    this.operators = new OperatorParser().parse(expression);
    this.operands = new OperandParser().parse(expression);
  }

  public Operators operators() {
    return new Operators(operators);
  }

  public Operands operands() {
    return new Operands(operands);
  }
}
