package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;
import static java.lang.Long.parseLong;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringExpression implements Expression {
  private final String value;

  @Override
  public Result evaluate() {
    return of(parseLong(value.trim()));
  }
}
