package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class IntegerPatternFinderSpec extends Specification {
  def 'find a integer:[#expected] at #expression'() {
    given: 'expression'
    when: 'call find()'
    then: 'return only a number string'
      subject(expression) == expected
    where:
      expression | expected
      '0'        | '0'
      '123'      | '123'
      '-500'     | '-500'
  }

  def 'throw exception if not found:[#expected] at #expression'() {
    given: 'expression which not contain integer'
    when: 'call find()'
      subject(expression)
    then: 'throw NotFoundPatternException'
      NotFoundPatternException e = thrown()
    where:
      expression | expected
      '1.23'     | void
      '-50.0'    | void
      '4.9e-3'   | void
      '4.9e+3'   | void
      '0.0'      | void
  }

  static def subject(String expression) {
    return new IntegerPatternFinder().find(expression)
  }
}