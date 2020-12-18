package com.github.lette1394.calculator2.expression;

import static java.lang.Long.parseLong;

import java.util.regex.Matcher;

public abstract class LongExpression extends MatchedTwoOperandExpression<Long, Long> {
  protected LongExpression(Matcher matcher) throws UnsupportedExpressionException {
    super(matcher);
  }

  @Override
  protected Long toLeft(String left) {
    return parseLong(left);
  }

  @Override
  protected Long toRight(String right) {
    return parseLong(right);
  }
}
