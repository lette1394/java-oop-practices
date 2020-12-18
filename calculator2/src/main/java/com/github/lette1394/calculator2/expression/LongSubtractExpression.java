package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Pattern;

public class LongSubtractExpression extends LongExpression {
  private final static Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*(-)\\s*(\\d+)\\s*");
  private final String expression;

  public LongSubtractExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  public Result evaluate() throws
                           DivideByZeroException,
                           OverflowException,
                           UnderflowException,
                           EvaluationTimeoutException {
    return of(left() - right());
  }

  @Override
  public String toString() {
    return expression;
  }
}
