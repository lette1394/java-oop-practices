package com.github.lette1394.calculator3.pattern;

import static java.util.regex.Pattern.compile;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
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
    return matcher(this.pattern + other.pattern);
  }

  public static PatternMatcher or(PatternMatcher... others) {
    final String composedOr = List.of(others)
      .stream()
      .map(builder -> builder.pattern)
      .collect(Collectors.joining("|"));
    return matcher(composedOr);
  }

  public static PatternMatcher single(char ch) {
    return matcher("" + ch);
  }

  public static PatternMatcher integer() {
    return matcher("(-?\\d+)");
  }

  public static PatternMatcher decimal() {
    return or(eBasedDecimal(), plainDecimal());
  }

  public static PatternMatcher plainDecimal() {
    return matcher("(-?\\d+\\.\\d+)");
  }

  public static PatternMatcher eBasedDecimal() {
    return matcher("(-?\\d+(\\.\\d)?\\d*[Ee][+-]?\\d+)");
  }

  public PatternMatcherResult match(String expression) {
    final Matcher matcher = compile(pattern).matcher(expression);
    return () -> {
      if (matcher.find()) {
        return Optional.of(matcher.group());
      }
      return Optional.empty();
    };
  }
}
