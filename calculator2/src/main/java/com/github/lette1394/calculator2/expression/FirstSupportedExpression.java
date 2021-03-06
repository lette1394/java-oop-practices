package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.result.Result;
import java.util.List;
import java.util.function.Supplier;

public class FirstSupportedExpression implements Expression {
  private final List<Supplier<Expression>> expressions;

  public FirstSupportedExpression(List<Supplier<Expression>> expressions) {
    this.expressions = expressions;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    final UnsupportedExpressionException unsupported = new UnsupportedExpressionException();
    for (Supplier<Expression> expression : expressions) {
      try {
        return expression.get().evaluate();
      } catch (UnsupportedExpressionException e) {
        unsupported.addSuppressed(e);
      }
    }
    throw unsupported;
  }
}
