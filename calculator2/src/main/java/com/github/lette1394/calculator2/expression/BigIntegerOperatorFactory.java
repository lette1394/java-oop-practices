package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.operator.Operator;
import com.github.lette1394.calculator2.operator.OperatorFactory;
import java.math.BigInteger;

// FIXME (jaeeun) 2020-12-16: package 위치가 마음에 안든다. 어떻게 해결하지?
public class BigIntegerOperatorFactory implements OperatorFactory {
  public static final OperatorFactory INSTANCE = new BigIntegerOperatorFactory();

  private BigIntegerOperatorFactory() {
  }

  @Override
  public Operator add() {
    return (left, right) -> new BigIntegerTwoOperandExpression(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.add(right);
      }

      @Override
      public String toString() {
        return format("%s + %s", leftExpression, rightExpression);
      }
    };
  }

  @Override
  public Operator subtract() {
    return (left, right) -> new BigIntegerTwoOperandExpression(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.subtract(right);
      }

      @Override
      public String toString() {
        return format("%s - %s", leftExpression, rightExpression);
      }
    };
  }

  @Override
  public Operator multiply() {
    return new Operator() {
      @Override
      public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
        return new BigIntegerTwoOperandExpression(left, right) {
          @Override
          protected BigInteger handle(BigInteger left, BigInteger right) {
            return left.multiply(right);
          }

          @Override
          public String toString() {
            return format("%s * %s", leftExpression, rightExpression);
          }
        };
      }

      @Override
      public String toString() {
        return "*";
      }
    };
  }

  @Override
  public Operator divide() {
    return (left, right) -> new BigIntegerTwoOperandExpression(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        if (right.longValue() == 0) {
          throw new DivideByZeroException();
        }
        return left.divide(right);
      }

      @Override
      public String toString() {
        return format("%s / %s", leftExpression, rightExpression);
      }
    };
  }
}
