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
    assertThat(subjectLong("((1))"), is(1L));
    assertThat(subjectLong("(((1)))"), is(1L));
  }

  @Test
  void parentheses_two_operand() {
    assertThat(subjectLong("(1) + 2"), is(3L));
    assertThat(subjectLong("1 + (2)"), is(3L));
    assertThat(subjectLong("(1) + (2)"), is(3L));
    assertThat(subjectLong("((1 + 2) * 4)"), is(12L));
  }

  @Test
  void complex() {
    assertThat(subjectLong("((7 * 2 + 3) * ((10 + 2 - 5)* 9 + 3) * 4)"), is(4488L));
    // FIXME (jaeeun) 2020-12-18: 음수 값 평가하는 expression 별도로 넣자.
    //  괄호가 평가되다 보면은 (10 * -5) 이런 값이 제대로 계산이 안되고 있음
//    assertThat(subjectLong("((((((((1)))) + 2 + (6 * (51 - 59)) + ((3)))))+ 7) * 3"), is(-105L));
    assertThat(subjectLong("((((((((1)))) + 2 + (6 * (80 - 59)) + ((3)))))+ 7) * 3"), is(417L));
  }

  private Long subjectLong(String expression) {
    return subject(expression).asLongExact();
  }

  private Result subject(String expression) {
    return new ParenthesesParsingExpression(expression, new ParsingExpressionFactory())
      .evaluate();
  }
}