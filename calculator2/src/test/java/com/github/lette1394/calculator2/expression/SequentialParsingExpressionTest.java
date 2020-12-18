package com.github.lette1394.calculator2.expression;

import org.junit.jupiter.api.Test;

class SequentialParsingExpressionTest {

  @Test
  void test1() {

  }

  private long subjectLong(String expression) {
    return new SequentialParsingExpression(expression, null, null).evaluate().asLongExact();
  }
}