import com.github.lette1394.calculator3.calculator.ExhaustiveCalculator
import com.github.lette1394.calculator3.evaluator.DivideByZeroException
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
      '-10*500'  | '-5000'
  }

  def 'evaluate divide operator #expression == #expected'() {
    given: 'divide expression'
    and: 'two operand'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression | expected
      '10/2'     | '5'
      '500/-10'  | '-50'
      '-500/-10' | '50'
  }

  def 'throw exception when divide by zero'() {
    given: 'divide expression'
    and: 'two operand with zero'
    when: 'try to evaluate'
      evaluate('1/0')
    then: 'throw exception'
      DivideByZeroException exception = thrown()
  }

  def 'evaluate n operator #expression == #expected'() {
    given: 'complex expression'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression           | expected
      '2 + 3 + 6'          | '11'
      '2 - 3 - 6'          | '-7'
      '2 * 3 * 6'          | '36'
      '2 + 3 - 6'          | '-1'
      '2 - 3 + 6'          | '5'
      '2 * 3 + 6'          | '12'
      '2 * 3 - 6'          | '0'
      '2 * 3 / 6'          | '1'
      '2 * 3 / 6'          | '1'
      '2 / 3 / 6'          | '0.1111111111111111'
      '2 / 3 + 6'          | '6.666666666666667'
      '2 / 3 - 6'          | '-5.333333333333333'
      '2 / 3 * 6'          | '4'
      '2 - 3 + 6 - 5'      | '0'
      '2 - 3 + 6 - 1'      | '4'
      '99-3+3'             | '99'
      '99-3+3*99'          | '393'
      '99+112 - 1823+34+3' | '-1575'
      '99+112 -1823+34+3'  | '-1575'
      '99+112- 1823+34+3'  | '-1575'
  }


  def 'evaluate parentheses #expression == #expected'() {
    given: 'complex expression with parentheses'
    when: 'evaluate'
    then: 'get a evaluated value'
      evaluate(expression) == expected
    where:
      expression                                               | expected
      '(1)'                                                    | '1'
      '(50)'                                                   | '50'
      '((1))'                                                  | '1'
      '(((1)))'                                                | '1'
      '(1) + 2'                                                | '3'
      '1 + (2)'                                                | '3'
      '(1) + (2)'                                              | '3'
      '((1 + 2) * 4)'                                          | '12'
      '((7 * 2 + 3) * ((10 + 2 - 5)* 9 + 3) * 4)'              | '4488'
      '((((((((1)))) + 2 + (6 * (51 - 59)) + ((3)))))+ 7) * 3' | '-105'
      '((((((((1)))) + 2 + (6 * (80 - 59)) + ((3)))))+ 7) * 3' | '417'
  }

  static def evaluate(String expression) {
    return new ExhaustiveCalculator().evaluate(expression)
  }
}