package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class PatternFinderSpec extends Specification {
  def 'find a numeric value :[#expected] at #expression'() {
    given: 'expression which contains a number'
    when: 'call find()'
    then: 'return the value'
      find(expression) == expected
    where:
      expression | expected
      '0'        | '0'
      '123'      | '123'
      '-500'     | '-500'

      '1.23'     | '1.23'
      '-50.0'    | '-50.0'
      '4.9e-3'   | '4.9e-3'
      '4.9E-3'   | '4.9E-3'
      '4.9e+3'   | '4.9e+3'
      '4.9E+3'   | '4.9E+3'
      '0.0'      | '0.0'
  }

  static def subject() {
    return new FirstMatchedPatternFinder(List.of(
      new IntegerPatternFinder(),
      new DecimalPatternFinder(),
      new E_NotationBasedDecimalPatternFinder(),
    ))
  }

  static def find(String expression) {
    return subject().find(expression)
  }
}
