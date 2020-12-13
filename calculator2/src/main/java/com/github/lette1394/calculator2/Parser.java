package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.add;
import static com.github.lette1394.calculator2.Expressions.of;

import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  private final static Pattern pattern = Pattern.compile("^(.+)(\\+)(.+)$");
  private final Matcher matcher;
  private final String value;

  private Parser(String value) {
    this.value = value;
    this.matcher = pattern.matcher(value);
  }

  public static Parser parse(String value) {
    return new Parser(value);
  }

  public Expression left() {
    return of(parse(1));
  }

  public BiFunction<Expression, Expression, Expression> operator() {
    return (left, right) -> add(left, right);
  }

  public Expression right() {
    return of(parse(3));
  }

  private String parse(int groupIndex) {
    if (matcher.matches()) {
      return matcher.group(groupIndex);
    }
    return value;
  }
}
