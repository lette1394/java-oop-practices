package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.NotFoundPatternException;
import com.github.lette1394.calculator3.pattern.RealNumericPatternFinder;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddEvaluator implements Evaluator {
  private final RealNumericPatternFinder patternFinder;

  // TODO: operator를 찾는 finder도 넣어주자
  //  이름은 single character finder? 정도로

  private final static Pattern pattern =
    Pattern.compile("\\s*(-?\\d+\\.?\\d*)\\s*(\\+)\\s*(-?\\d+\\.?\\d*)\\s*");

  private final Adder adder;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    final String left = find(expression);
    // TODO: 여기에 substring을 계산하는 로직을 넣자.

    final String right = find(expression.substring());
    return adder.add(left, right);
  }

  private String find(String expression) {
    try {
      return patternFinder.find(expression);
    } catch (NotFoundPatternException e) {
      throw new UnsupportedExpressionException(format("Not supported expression: %s", expression), e);
    }
  }

  @Override
  public String toString() {
    return "add evaluator";
  }
}
