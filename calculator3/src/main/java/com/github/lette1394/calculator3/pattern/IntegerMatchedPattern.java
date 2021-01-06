package com.github.lette1394.calculator3.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerMatchedPattern implements MatchedPattern {
  private static final Pattern pattern = Pattern.compile("-?\\d+");
  private final Matcher matcher;

  public IntegerMatchedPattern(String expression) {
    this.matcher = pattern.matcher(expression);
  }

  @Override
  public String next() throws NotMatchedAnymoreException {
    if (matcher.find()) {
      return matcher.group();
    }
    throw new NotMatchedAnymoreException();
  }
}
