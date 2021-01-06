package com.github.lette1394.calculator3.evaluator

import com.github.lette1394.calculator3.pattern.PatternFinderFactory
import spock.lang.Specification

class AddEvaluatorSpec extends Specification {
  def 'evaluate n operator #expression == #expected'() {
    given: 'complex expression'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '2 + 3'    | '5'
  }

  private static String evaluate(String expresion) {
    return new AddEvaluator(PatternFinderFactory.realNumber(), new NumericAdder()).evaluate(expresion);
  }
}
