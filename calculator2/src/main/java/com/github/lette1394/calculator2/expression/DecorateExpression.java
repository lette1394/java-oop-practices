package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.result.Result;
import java.util.function.Function;

public class DecorateExpression<T extends Expression> implements Expression {
  private final Expression expression;
  private final Function<Expression, T> decorator;

  public DecorateExpression(Expression expression, Function<Expression, T> decorator) {
    this.expression = expression;
    this.decorator = decorator;
  }

  @Override
  public Result evaluate() throws DivideByZeroException {
    return decorator
      .apply(expression)
      .evaluate();
  }
}
