package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.common.Contracts.requires;
import static java.lang.String.format;

import java.util.regex.Matcher;

public abstract class MatchedTwoOperandExpression implements Expression {
  private final Matcher matcher;

  protected MatchedTwoOperandExpression(Matcher matcher) throws UnsupportedExpressionException {
    this.matcher = matcher;
    requires(matcher.matches(), new UnsupportedExpressionException(format("matcher: %s", matcher)));
  }

  protected static String toInteger(String expression) {
    if (expression.contains(".")) {
      final int dot = expression.indexOf(".");
      return expression.substring(0, dot);
    }
    return expression;
  }

  protected static boolean isDecimal(String expression) {
    return expression.contains(".") && !haveAllZero(expression);
  }

  private static boolean haveAllZero(String expression) {
    final String substring = expression.substring(expression.indexOf(".") + 1); // next index of dot
    final String[] characters = substring.split("");
    for (String ch : characters) {
      if (ch.equals("0")) {
        continue;
      }
      return false;
    }
    return true;
  }

  protected final String left() {
    return matcher.group(1);
  }

  protected final String right() {
    return matcher.group(3);
  }
}
