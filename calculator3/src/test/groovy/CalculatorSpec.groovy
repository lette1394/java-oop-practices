import com.github.lette1394.calculator3.calculator.ExhaustiveCalculator
import spock.lang.Specification


class CalculatorSpec extends Specification {
  def 'evaluate just a number #expression == #expected'() {
    given: 'a number'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '123'      | '123'
      '456'      | '456'
      '3.14'     | '3.14'
      '-500'     | '-500'
  }

  def 'evaluate two operand expression #expression == #expected'() {
    given: 'two operand expression'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '10+2'     | '12'
      '10*2'     | '20'
      '10x2'     | '20'
      '50-6'     | '44'
      '-10*2'    | '-20'
      '-10/2'    | '-10'
  }

  static def evaluate(String expression) {
    return new ExhaustiveCalculator().evaluate(expression)
  }
}