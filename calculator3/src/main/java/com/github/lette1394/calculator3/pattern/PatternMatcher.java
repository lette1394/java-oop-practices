package com.github.lette1394.calculator3.pattern;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PatternMatcher {
  private final String pattern;

  public static PatternMatcher matcher() {
    return matcher("");
  }

  public static PatternMatcher matcher(Pattern pattern) {
    return matcher(pattern.pattern());
  }

  public static PatternMatcher matcher(String regex) {
    return new PatternMatcher(regex);
  }

  public PatternMatcher(String pattern) {
    this.pattern = pattern;
  }

  public PatternMatcher then(PatternMatcher other) {
    return matcher(this.pattern + other.pattern);
  }

  public static PatternMatcher or(PatternMatcher... others) {
    return merge(joining("|", "(", ")"), others);
  }

  public static PatternMatcher and(PatternMatcher... others) {
    return merge(joining(""), others);
  }

  private static PatternMatcher merge(Collector<CharSequence, ?, String> collector,
    PatternMatcher... others) {
    final String composedOr = List.of(others)
      .stream()
      .map(builder -> builder.pattern)
      .collect(collector);
    return matcher(composedOr);
  }

  public static PatternMatcher just(String regex) {
    return matcher(format("(%s)", regex));
  }

  public static PatternMatcher integer() {
    return matcher("(-?\\d+)");
  }

  public static PatternMatcher blank() {
    return matcher("\\s*");
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

  public static PatternMatcher realNumber() {
    return or(decimal(), integer());
  }

  public PatternMatcherResult match(String expression) {
    final Matcher matcher = compile(pattern).matcher(expression);
     return new PatternMatcherResult(expression, matcher);
  }
}
