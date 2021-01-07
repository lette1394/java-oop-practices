package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

class DecimalMatchedPatternSpec extends Specification {
  def 'find #targets in #expression'() {
    expect:
      findAll(subject(expression)) == targets
    where:
      expression                | targets
      '1.0'                     | ['1.0']
      '10.4'                    | ['10.4']
      '10.555+2.123123'         | ['10.555', '2.123123']
      '10.555+-2.123123'        | ['10.555', '-2.123123']
      '10.555abc123.123123'     | ['10.555', '123.123123']
      '10.555abc 123.123123'    | ['10.555', '123.123123']
      '10.555 abc123.123123'    | ['10.555', '123.123123']
      '10.555 abc 123.123123'   | ['10.555', '123.123123']
      '-10.555 abc -123.123123' | ['-10.555', '-123.123123']

      '2e+8'                    | ['2e+8']
      '2E+8'                    | ['2E+8']
      '2.0e+8'                  | ['2.0e+8']
      '-2.0e+8'                 | ['-2.0e+8']
      '2.0e-8'                  | ['2.0e-8']
      '-2.0e-8'                 | ['-2.0e-8']
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
      pattern.next() == '10.0'
      pattern.next() == '2.123'
      pattern.next()
    then: 'throws exception'
      thrown(NotMatchedAnymoreException.class)
    where:
      expression = '10.0+2.123'
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
    return new DecimalMatchedPattern(expression)
  }
}
