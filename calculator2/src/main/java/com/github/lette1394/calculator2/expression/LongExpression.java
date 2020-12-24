package com.github.lette1394.calculator2.expression;

import java.util.regex.Matcher;

public abstract class LongExpression extends MatchedTwoOperandExpression {
  protected LongExpression(Matcher matcher) throws UnsupportedExpressionException {
    super(matcher);
  }
}
