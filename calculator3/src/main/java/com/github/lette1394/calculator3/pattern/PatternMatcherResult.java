package com.github.lette1394.calculator3.pattern;

import static java.lang.String.format;

import java.util.Optional;
import java.util.regex.Matcher;

public class PatternMatcherResult {
  private final String expression;
  private final Matcher matcher;

  PatternMatcherResult(String expression, Matcher matcher) {
    this.expression = expression;
    this.matcher = matcher;
  }

  public Optional<String> next() {
    if (matcher.find()) {
      return Optional.of(matcher.group());
    }
    return Optional.empty();
  }

  @Override
  public String toString() {
    return format("%s", expression);
  }
}
