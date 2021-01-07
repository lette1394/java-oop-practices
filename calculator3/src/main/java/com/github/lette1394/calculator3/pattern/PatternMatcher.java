package com.github.lette1394.calculator3.pattern;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PatternMatcher {
  private final String pattern;

  public static PatternMatcher matcher() {
    return matcher("");
  }

  public static PatternMatcher matcher(String pattern) {
    return new PatternMatcher(pattern);
  }

  public PatternMatcher(String pattern) {
    this.pattern = pattern;
  }

  public PatternMatcher then(PatternMatcher other) {
    return build(this.pattern + other.pattern);
  }

  public PatternMatcher or(PatternMatcher... others) {
    final String composedOr = List.of(others)
      .stream()
      .map(builder -> builder.pattern)
      .collect(Collectors.joining("|"));
    return build(composedOr);
  }

  public PatternMatcher single(char ch) {
    return this;
  }

  public PatternMatcher integer() {
    return this;
  }

  public PatternMatcher decimal() {
    return this;
  }

  public PatternMatcher plainDecimal() {
    return this;
  }

  public PatternMatcher eBasedDecimal() {
    return this;
  }

  public PatternMatcherResult match(String expression) {
    final Matcher matcher = Pattern.compile(Pattern.quote(pattern)).matcher(expression);
    return () -> {
      if (matcher.find()) {
        return Optional.of(matcher.group());
      }
      return Optional.empty();
    };
  }

  private PatternMatcher build(String expression) {
    return new PatternMatcher(expression);
  }
}
