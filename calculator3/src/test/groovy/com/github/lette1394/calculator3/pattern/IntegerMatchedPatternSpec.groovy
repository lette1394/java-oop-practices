package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class IntegerMatchedPatternSpec extends Specification {
  def 'find #targets in #expression'() {
    expect:
      def pattern = subject(expression)
      findAll(pattern) == targets
    where:
      expression   | targets
      '1'          | ['1']
      '10'         | ['10']
      '10+2'       | ['10', '2']
      '10abc123'   | ['10', '123']
      '10abc 123'  | ['10', '123']
      '10 abc123'  | ['10', '123']
      '10 abc 123' | ['10', '123']

      '2.7'        | ['2', '7']
      '2.0'        | ['2', '0']
      '2e+8'       | ['2', '8']
      '2.0e+8'     | ['2', '0', '8']
  }

  def 'throw exception in #expression'() {
    when:
      subject(expression).next()
    then:
      thrown(NotMatchedAnymoreException.class)
    where:
      expression << [
        '       ',
        'abc',
        'not a number',
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

  private static def findAll(MatchedPattern matchedPattern) {
    def ret = []
    while (true) {
      try {
        ret << matchedPattern.next()
      } catch (NotMatchedAnymoreException ignored) {
        break
      }
    }
    return ret
  }

  private static MatchedPattern subject(String expression) {
    return new IntegerMatchedPattern(expression)
  }
}
