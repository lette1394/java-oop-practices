import com.github.lette1394.calculator3.calculator.ExhaustiveCalculator
import spock.lang.Specification


class CalculatorSpec extends Specification {
  def 'evaluate just a number #number == #expected'() {
    given: 'a number'
    when: 'evaluate'
    then: 'get a evaluated number'
      evaluate(number) == expected
    where:
      number | expected
      '123'  | '123'
      '456'  | '456'
      '3.14' | '3.14'
  }


  static def evaluate(String expression) {
    return new ExhaustiveCalculator().evaluate(expression)
  }
}