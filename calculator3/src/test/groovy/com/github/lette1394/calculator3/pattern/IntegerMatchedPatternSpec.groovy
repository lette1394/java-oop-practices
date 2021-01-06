package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class IntegerMatchedPatternSpec extends Specification {
  def 'find #targets in #expression'() {
    expect:
      def pattern = subject(expression)
      pattern.next() == '10'
      pattern.next() == '2'
    where:
      expression = '10+2'
      targets = ['10', '2']
  }

  def 'throw exception when expression does not contain integer'() {
    when:
      subject(expression).next()
    then:
      thrown(NotMatchedAnymoreException.class)
    where:
      expression << [
        '       ',
        'abc',
        'not a number',
        '2.7',
        '2.0',
        '2e+8',
        '2.0e+8'
      ]
  }

  def 'throw exception when call find() more than it needs'() {
    given: 'a expression'
      def pattern = subject(expression)
    when: 'call find() more than it needs'
      pattern.next() == '10'
      pattern.next() == '2'
      pattern.next()
    then: 'throws exception'
      thrown(NotMatchedAnymoreException.class)
    where:
      expression = '10+2'
      targets = ['10', '2']
  }

  private static MatchedPattern subject(String expression) {
    return new IntegerMatchedPattern(expression)
  }
}
