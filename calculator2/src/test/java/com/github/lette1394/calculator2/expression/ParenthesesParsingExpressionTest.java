package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.github.lette1394.calculator2.result.Result;
import org.junit.jupiter.api.Test;

class ParenthesesParsingExpressionTest {

  @Test
  void parentheses() {
    assertThat(subjectLong("(1)"), is(1L));
    assertThat(subjectLong("(50)"), is(50L));
  }

  @Test
  void parentheses_two_operand() {
    assertThat(subjectLong("(1) + (2)"), is(3L));
  }

  private Long subjectLong(String expression) {
    return subject(expression).asLongExact();
  }

  private Result subject(String expression) {
    return new ParenthesesParsingExpression(expression, NumericExpressionFactory.INSTANCE)
      .evaluate();
  }
}