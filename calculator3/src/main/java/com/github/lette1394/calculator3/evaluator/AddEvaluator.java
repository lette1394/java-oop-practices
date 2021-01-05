package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.NotFoundPatternException;
import com.github.lette1394.calculator3.pattern.RealNumericPatternFinder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddEvaluator extends BaseEvaluator {
  private final RealNumericPatternFinder patternFinder;
  private final Adder adder;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    final String left = find(expression);
    final String right = find(substringAfter(expression, left));
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
    return "add";
  }
}
