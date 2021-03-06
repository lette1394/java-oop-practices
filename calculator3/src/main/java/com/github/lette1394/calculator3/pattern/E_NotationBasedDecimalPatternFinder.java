package com.github.lette1394.calculator3.pattern;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class E_NotationBasedDecimalPatternFinder implements PatternFinder {
  private static final Pattern pattern = Pattern.compile("\\s*(-?\\d+\\.\\d+[eE][+-]\\d+)\\s*");

  public String find(String expression) throws NotFoundPatternException {
    final Matcher matcher = pattern.matcher(expression);
    if (matcher.matches()) {
      return matcher.group(1);
    }
    throw new NotFoundPatternException(format("not found a decimal at:[%s]", expression));
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
