package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.result.Result;
import java.util.List;
import java.util.function.Supplier;

public class FirstMatchedExpression implements Expression {
  private final List<Supplier<Expression>> expressions;

  public FirstMatchedExpression(List<Supplier<Expression>> expressions) {
    this.expressions = expressions;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return null;
  }
}
