package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

import static com.github.lette1394.calculator3.pattern.PatternMatcher.*

class PatternMatcherSpec extends Specification {
  def 'find integer #targets in #expression'() {
    expect:
      PatternMatcherResult result = matcher()
        .compose(integer())
        .match(expression)

      findAll(result) == targets
    where:
      expression   | targets
      '1'          | ['1']
      '10'         | ['10']
      '10+2'       | ['10', '2']
      '10+-2'      | ['10', '-2']
      '10abc123'   | ['10', '123']
      '-10abc-123' | ['-10', '-123']
      '10abc 123'  | ['10', '123']
      '10 abc123'  | ['10', '123']
      '10 abc 123' | ['10', '123']

      '2.7'        | ['2', '7']
      '2.0'        | ['2', '0']
      '2e+8'       | ['2', '8']
      '2.0e+8'     | ['2', '0', '8']
      '2.0E+8'     | ['2', '0', '8']
  }

  def 'find real number #targets in #expression'() {
    expect:
      PatternMatcherResult result = matcher()
        .compose(realNumber())
        .match(expression)

      findAll(result) == targets
    where:
      expression   | targets
      '1'          | ['1']
      '10'         | ['10']
      '10+2'       | ['10', '2']
      '10+-2'      | ['10', '-2']
      '10abc123'   | ['10', '123']
      '-10abc-123' | ['-10', '-123']
      '10abc 123'  | ['10', '123']
      '10 abc123'  | ['10', '123']
      '10 abc 123' | ['10', '123']

      '2.7'        | ['2.7']
      '2.0'        | ['2.0']
      '2e+8'       | ['2e+8']
      '2E+8'       | ['2E+8']
      '2.0e+8'     | ['2.0e+8']
      '2.0E+8'     | ['2.0E+8']
      '2 * 3 * 6'  | ['2', '3', '6']
      '10 +2.0'    | ['10', '2.0']
      '10 +-2.0'   | ['10', '-2.0']
  }

  def 'find expression #targets in #expression'() {
    expect:
      PatternMatcherResult result = matcher()
        .compose(and(realNumber(), just("\\+"), realNumber()))
        .match(expression)

      findAll(result) == targets
    where:
      expression           | targets
      '99+112 - 1823+34+3' | ['99+112', '1823+34']
  }

  private static def findAll(PatternMatcherResult matchedPattern) {
    def ret = []
    while (true) {
      Optional<String> next = matchedPattern.next()
      if (next.isPresent()) {
        ret << next.get()
        continue
      }
      break
    }
    return ret
  }
}
