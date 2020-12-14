package com.github.lette1394.calculator2;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.subtractExact;

import java.math.BigInteger;

public class Operators {
//  public static Operator add() {
//    return (left, right) -> fallback(
//      new AddExpression(left, right),
//      () -> cache(new BigIntegerExpression(
//        new BigInteger(String.valueOf(left.evaluate()))
//          .add(new BigInteger(String.valueOf(right.evaluate()))))).evaluate())
//      .apply(left, right);
//  }

  public static Operator add() {
    return fallback(numberAdd(), bigIntegerAdd());
  }

  public static Operator bigIntegerAdd() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.add(right);
      }
    };
  }

  public static Operator bigIntegerSubtract() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.subtract(right);
      }
    };
  }

  public static Operator numberAdd() {
    return (left, right) -> new NumberOperator(left, right) {
      @Override
      protected Number whenUseLong(long left, long right) {
        return addExact(left, right);
      }

      @Override
      protected Number whenUseDouble(double left, double right) {
        return left + right;
      }
    };
  }

  public static Operator subtract() {
    return fallback(numberSubtract(), bigIntegerSubtract());
  }

  public static Operator numberSubtract() {
    return (left, right) -> new NumberOperator(left, right) {
      @Override
      protected Number whenUseLong(long left, long right) {
        return subtractExact(left, right);
      }

      @Override
      protected Number whenUseDouble(double left, double right) {
        return left - right;
      }
    };
  }

  public static Operator multiply() {
    return (left, right) -> new NumberOperator(left, right) {
      @Override
      protected Number whenUseLong(long left, long right) {
        return multiplyExact(left, right);
      }

      @Override
      protected Number whenUseDouble(double left, double right) {
        return left - right;
      }
    };
  }

  public static Operator divide() {
    return (left, right) -> new NumberOperator(left, right) {
      @Override
      protected Number whenUseLong(long left, long right) {
        checkDivideByZero(right);
        return left / right;
      }

      @Override
      protected Number whenUseDouble(double left, double right) {
        checkDivideByZero(right);
        return left / right;
      }

      private void checkDivideByZero(Number value) {
        if (value.doubleValue() == 0) {
          throw new DivideByZeroException();
        }
      }
    };
  }

  public static Operator fallback(Operator operator, Operator fallback) {
    return (left, right) -> new FallbackExpression(
      DivideByZeroException.class,
      () -> operator.apply(left, right).evaluate(),
      () -> fallback.apply(left, right).evaluate());
  }
}