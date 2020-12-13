package com.github.lette1394.calculator;

import java.util.List;
import lombok.experimental.Delegate;

public class Operators {
  private final List<Operator> operators;

  public Operators(List<Operator> operators) {
    this.operators = operators;
  }

  public List<Operator> asList() {
    return operators;
  }
}
