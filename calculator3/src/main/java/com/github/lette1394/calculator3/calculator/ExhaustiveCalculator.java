package com.github.lette1394.calculator3.calculator;

import com.github.lette1394.calculator3.evaluator.AddEvaluator;
import com.github.lette1394.calculator3.evaluator.AdderInNumeric;
import com.github.lette1394.calculator3.evaluator.DivideEvaluator;
import com.github.lette1394.calculator3.evaluator.DividerInNumeric;
import com.github.lette1394.calculator3.evaluator.Evaluator;
import com.github.lette1394.calculator3.evaluator.FallbackEvaluator;
import com.github.lette1394.calculator3.evaluator.JustEvaluator;
import com.github.lette1394.calculator3.evaluator.MultiplierInNumeric;
import com.github.lette1394.calculator3.evaluator.MultiplyEvaluator;
import com.github.lette1394.calculator3.evaluator.PriorReducingEvaluator;
import com.github.lette1394.calculator3.evaluator.SubtractEvaluator;
import com.github.lette1394.calculator3.evaluator.SubtractorInNumeric;
import java.util.List;
import java.util.regex.Pattern;

public class ExhaustiveCalculator implements Calculator {
  private final Evaluator evaluator;

  public ExhaustiveCalculator() {
    final Evaluator operators = new FallbackEvaluator(List.of(
      new AddEvaluator(new AdderInNumeric()),
      new SubtractEvaluator(new SubtractorInNumeric()),
      new MultiplyEvaluator(new MultiplierInNumeric()),
      new DivideEvaluator(new DividerInNumeric()),
      new JustEvaluator()
    ));

    final Evaluator priority = new FallbackEvaluator(List.of(
      new PriorReducingEvaluator(Pattern.compile("(.*)(\\+|- )(.*)"), operators),
      new PriorReducingEvaluator(Pattern.compile("(.*)([*/])(.*)"), operators)
    ));
    evaluator = priority;
  }

  @Override
  public String evaluate(String expression) {
    return this.evaluator.evaluate(expression);
  }
}
