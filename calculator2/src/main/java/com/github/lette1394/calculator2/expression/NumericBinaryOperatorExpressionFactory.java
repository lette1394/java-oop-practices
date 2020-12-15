package com.github.lette1394.calculator2.expression;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.subtractExact;

import com.github.lette1394.calculator2.Operator;
import com.github.lette1394.calculator2.OperatorFactory;

public class NumericBinaryOperatorExpressionFactory implements OperatorFactory {
  public static final OperatorFactory INSTANCE = new NumericBinaryOperatorExpressionFactory();

  private NumericBinaryOperatorExpressionFactory() {
  }

  @Override
  public Operator add() {
    return (left, right) -> new NumericBinaryOperatorExpression(left, right) {
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

  @Override
  public Operator subtract() {
    return (left, right) -> new NumericBinaryOperatorExpression(left, right) {
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

  @Override
  public Operator multiply() {
    return (left, right) -> new NumericBinaryOperatorExpression(left, right) {
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

  @Override
  public Operator divide() {
    return (left, right) -> new NumericBinaryOperatorExpression(left, right) {
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
}
