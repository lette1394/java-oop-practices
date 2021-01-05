package com.github.lette1394.calculator3.evaluator;

import com.github.lette1394.calculator3.pattern.PatternFinder;

public class AddEvaluator extends BaseEvaluator {
  private final Adder adder;

  public AddEvaluator(PatternFinder patternFinder, Adder adder) {
    super(patternFinder);
    this.adder = adder;
  }

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

  @Override
  public String toString() {
    return "add";
  }
}
