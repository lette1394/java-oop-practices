package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class IntegerPatternFinderSpec extends Specification {
  def 'evaluate n operator #expression == #expected'() {
    given: 'complex expression'
    when: 'evaluate'
    then: 'get a evaluated value'
      find(expression) == expected
    where:
      expression     | expected
      '2'            | '2'
      '22'           | '22'
      '22abasfd1929' | '22'
      'abasfd1929'   | '1929'
      'abasfd-1929'  | '-1929'
      '2+3+6'        | '2'
      '-2+3+6'       | '-2'
  }

  private static String find(String expression) {
    return new IntegerPatternFinder().find(expression)
  }
}
