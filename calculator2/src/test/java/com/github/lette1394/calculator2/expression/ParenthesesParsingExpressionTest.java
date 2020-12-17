package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.TestFixtures.subjectString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class ParenthesesParsingExpressionTest {

  @Test
  void parentheses() {
    assertThat(subjectString("(1)"), is(1L));
  }
}