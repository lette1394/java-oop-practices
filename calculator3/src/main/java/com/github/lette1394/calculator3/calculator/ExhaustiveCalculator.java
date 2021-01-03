package com.github.lette1394.calculator3.calculator;

import com.github.lette1394.calculator3.expression.AddEvaluator;
import com.github.lette1394.calculator3.expression.AdderInNumeric;
import com.github.lette1394.calculator3.expression.Evaluator;
import com.github.lette1394.calculator3.expression.FallbackEvaluator;
import com.github.lette1394.calculator3.expression.MultiplierInNumeric;
import com.github.lette1394.calculator3.expression.MultiplyEvaluator;
import com.github.lette1394.calculator3.expression.SubtractEvaluator;
import com.github.lette1394.calculator3.expression.SubtractorInNumeric;
import java.util.List;
import java.util.regex.Pattern;

public class ExhaustiveCalculator implements Calculator {
  private static Pattern pattern = Pattern.compile("-?\\d+|-?\\d+\\.\\d+");
  private final Evaluator evaluator;

  public ExhaustiveCalculator() {
    evaluator = new FallbackEvaluator(List.of(
      new AddEvaluator(new AdderInNumeric()),
      new SubtractEvaluator(new SubtractorInNumeric()),
      new MultiplyEvaluator(new MultiplierInNumeric())
    ));
  }

  @Override
  public String evaluate(String expression) {
    if (pattern.matcher(expression).matches()) {
      return expression;
    }

    return this.evaluator.evaluate(expression);
  }
}
