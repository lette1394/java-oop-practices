package com.github.lette1394.calculator2.operator;

import static java.lang.String.format;

import com.github.lette1394.calculator2.expression.DivideByZeroException;
import com.github.lette1394.calculator2.expression.EvaluationTimeoutException;
import com.github.lette1394.calculator2.expression.Expression;
import com.github.lette1394.calculator2.expression.UnrecoverableException;
import com.github.lette1394.calculator2.result.OverflowException;
import com.github.lette1394.calculator2.result.Result;
import com.github.lette1394.calculator2.result.UnderflowException;

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
  public BinaryOperator add() {
    return installFallback(factory.add(), fallbackFactory.add());
  }

  @Override
  public BinaryOperator subtract() {
    return installFallback(factory.subtract(), fallbackFactory.subtract());
  }

  @Override
  public BinaryOperator multiply() {
    return installFallback(factory.multiply(), fallbackFactory.multiply());
  }

  @Override
  public BinaryOperator divide() {
    return installFallback(factory.divide(), fallbackFactory.divide());
  }

  // FIXME (jaeeun) 2020-12-16: toString
  private BinaryOperator installFallback(BinaryOperator binaryOperator, BinaryOperator fallback) {
    return new BinaryOperator() {
      @Override
      public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
        return new Expression() {
          @Override
          public Result evaluate() throws DivideByZeroException,
                                          OverflowException,
                                          UnderflowException,
                                          EvaluationTimeoutException {
            try {
              return binaryOperator.apply(left, right).evaluate();
            } catch (UnrecoverableException unrecoverableException) {
              throw unrecoverableException;
            } catch (Exception e) {
              return fallback.apply(left, right).evaluate();
            }
          }

          @Override
          public String toString() {
            return format("%s %s %s", left, binaryOperator, right);
          }
        };
      }

      @Override
      public String toString() {
        return format("%s", binaryOperator);
      }
    };
  }
}
