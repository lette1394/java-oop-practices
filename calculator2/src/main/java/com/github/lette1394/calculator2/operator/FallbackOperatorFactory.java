package com.github.lette1394.calculator2.operator;

import static java.lang.String.format;

import com.github.lette1394.calculator2.expression.DivideByZeroException;
import com.github.lette1394.calculator2.expression.EvaluationTimeoutException;
import com.github.lette1394.calculator2.expression.Expression;
import com.github.lette1394.calculator2.expression.UnrecoverableException;
import com.github.lette1394.calculator2.result.Result;

public class FallbackOperatorFactory implements OperatorFactory {
  private final OperatorFactory factory;
  private final OperatorFactory fallbackFactory;

  public FallbackOperatorFactory(
    OperatorFactory factory,
    OperatorFactory fallbackFactory) {
    this.factory = factory;
    this.fallbackFactory = fallbackFactory;
  }

  @Override
  public Operator add() {
    return installFallback(factory.add(), fallbackFactory.add());
  }

  @Override
  public Operator subtract() {
    return installFallback(factory.subtract(), fallbackFactory.subtract());
  }

  @Override
  public Operator multiply() {
    return installFallback(factory.multiply(), fallbackFactory.multiply());
  }

  @Override
  public Operator divide() {
    return installFallback(factory.divide(), fallbackFactory.divide());
  }

  // FIXME (jaeeun) 2020-12-16: toString
  private Operator installFallback(Operator operator, Operator fallback) {
    return new Operator() {
      @Override
      public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
        return new Expression() {
          @Override
          public Result evaluate() throws DivideByZeroException {
            try {
              return operator.apply(left, right).evaluate();
            } catch (UnrecoverableException unrecoverableException) {
              throw unrecoverableException;
            } catch (Exception e) {
              return fallback.apply(left, right).evaluate();
            }
          }

          @Override
          public String toString() {
            return format("%s %s %s", left, operator, right);
          }
        };
      }

      @Override
      public String toString() {
        return format("%s", operator);
      }
    };
  }
}
