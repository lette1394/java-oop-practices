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

  def 'evaluate add operator #expression == #expected'() {
    given: 'add expression'
    and: 'two operand'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '10+2'     | '12'
      '0+0'      | '0'
      '-10+500'  | '490'
      '-10+-500' | '-510'
  }

  def 'evaluate subtract operator #expression == #expected'() {
    given: 'subtract expression'
    and: 'two operand'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '10-2'     | '8'
      '10-500'   | '-490'
      '0-0'      | '0'
      '-10-500'  | '-510'
  }

  def 'evaluate multiply operator #expression == #expected'() {
    given: 'multiply expression'
    and: 'two operand'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '10*2'     | '20'
      '0*0'      | '0'
      '500*0'    | '0'
      '10*-500'  | '-5000'
      '-10*500'  | '-500'
  }

  static def evaluate(String expression) {
    return new ExhaustiveCalculator().evaluate(expression)
  }
}