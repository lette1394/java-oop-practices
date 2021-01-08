package com.github.lette1394.calculator3.pattern

import spock.lang.Specification

import static com.github.lette1394.calculator3.pattern.PatternMatcher.*

class PatternMatcherSpec extends Specification {
  def 'find #targets in #expression'() {
    expect:
      PatternMatcherResult result = matcher()
        .then(or(integer(), decimal()))
        .then(single('+' as char))
        .then(or(integer(), decimal()))
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
