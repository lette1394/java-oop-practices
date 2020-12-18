package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Matcher;

public abstract class LongExpression extends MatchedTwoOperandExpression<Number, Number> {
  protected LongExpression(Matcher matcher) throws UnsupportedExpressionException {
    super(matcher);
  }

  private static double parseDouble(String value) {
    try {
      final double result = Double.parseDouble(value);
      if (Double.isNaN(result)) {
        // FIXME (jaeeun) 2020-12-19: specify exception type
        throw new RuntimeException(value);
      }
      if (result == POSITIVE_INFINITY) {
        throw new OverflowException(value);
      }
      if (result == NEGATIVE_INFINITY) {
        throw new UnderflowException(value);
      }
      return result;
    } catch (NumberFormatException e) {
      throw new OverflowException(e);
    }
  }

  @Override
  public final Result evaluate() throws DivideByZeroException,
                                        OverflowException,
                                        UnderflowException,
                                        EvaluationTimeoutException {
    final Number whenUseDouble = whenUseDouble(left().doubleValue(), right().doubleValue());
    final Number whenUseLong = whenUseLong(left().longValue(), right().longValue());
    if (whenUseLong.doubleValue() == whenUseDouble.doubleValue()) {
      return of(whenUseLong);
    }
    return of(whenUseDouble);
  }

  @Override
  protected final Number toLeft(String left) {
    return parse(left);
  }

  @Override
  protected final Number toRight(String right) {
    return parse(right);
  }

  protected abstract Number whenUseLong(long left, long right);

  protected abstract Number whenUseDouble(double left, double right);

  private Number parse(String value) {
    if (isDecimal(parseDouble(value))) {
      return parseDouble(value);
    }
    return parseLong(value);
  }

  private boolean isDecimal(double value) {
    return value % 1 != 0;
  }

  private Number parseLong(String value) {
    try {
      final long result = Long.parseLong(value);
      return result;
    } catch (NumberFormatException e) {
      throw e;
    }
  }
}
