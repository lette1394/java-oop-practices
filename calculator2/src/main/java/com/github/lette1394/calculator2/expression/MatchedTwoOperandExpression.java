package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.common.Contracts.requires;
import static java.lang.String.format;

import java.util.regex.Matcher;

public abstract class MatchedTwoOperandExpression<L, R> implements Expression {
  private final Matcher matcher;

  protected MatchedTwoOperandExpression(Matcher matcher) throws UnsupportedExpressionException {
    this.matcher = matcher;
    requires(matcher.matches(), new UnsupportedExpressionException(format("matcher: %s", matcher)));
  }

  protected final L left() {
    return toLeft(matcher.group(1));
  }

  protected final R right() {
    return toRight(matcher.group(3));
  }

  protected abstract L toLeft(String left);

  protected abstract R toRight(String right);
}
