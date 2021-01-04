package com.github.lette1394.calculator3.pattern

import spock.lang.Specification


class NumericPatternSpec extends Specification {
  def 'find a integer:[#expected] at #expression'() {
    given: 'expression'
    when: 'find()'
    then: 'return only a number string'
      subject(expression) == expected
    where:
      expression | expected
      '123'      | '123'
      '-500'     | '-500'
  }

  static def subject(String expression) {
    return new NumericPattern().find();
  }
}