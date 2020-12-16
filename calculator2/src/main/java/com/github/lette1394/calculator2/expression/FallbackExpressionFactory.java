package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FallbackExpressionFactory implements ExpressionFactory {
  private final ExpressionFactory factory;
  private final ExpressionFactory fallback;

  public FallbackExpressionFactory(
    ExpressionFactory factory,
    ExpressionFactory fallback) {
    this.factory = factory;
    this.fallback = fallback;
  }

  @Override
  public Expression of(String value) {
    return installFallback(factory.of(value), fallback.of(value));
  }

  @Override
  public Expression of(long value) {
    return installFallback(factory.of(value), fallback.of(value));
  }

  @Override
  public Expression of(double value) {
    return installFallback(factory.of(value), fallback.of(value));
  }

  @Override
  public Expression of(BigInteger value) {
    return installFallback(factory.of(value), fallback.of(value));
  }

  @Override
  public Expression of(BigDecimal value) {
    return installFallback(factory.of(value), fallback.of(value));
  }

  private Expression installFallback(Expression expression, Expression fallback) {
    return new Expression() {
      @Override
      public Result evaluate() throws DivideByZeroException {
        try {
          return expression.evaluate();
        } catch (UnrecoverableException e) {
          throw e;
        } catch (Exception e) {
          return fallback.evaluate();
        }
      }

      @Override
      public String toString() {
        // FIXME (jaeeun) 2020-12-16: fallback은 어떻게 표현하지?
        return format("%s", expression);
      }
    };
  }
}
