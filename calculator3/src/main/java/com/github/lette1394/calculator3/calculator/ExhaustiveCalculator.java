package com.github.lette1394.calculator3.calculator;

import static com.github.lette1394.calculator3.pattern.PatternMatcher.and;
import static com.github.lette1394.calculator3.pattern.PatternMatcher.blank;
import static com.github.lette1394.calculator3.pattern.PatternMatcher.just;
import static com.github.lette1394.calculator3.pattern.PatternMatcher.realNumber;

import com.github.lette1394.calculator3.evaluator.AddEvaluator;
import com.github.lette1394.calculator3.evaluator.DivideEvaluator;
import com.github.lette1394.calculator3.evaluator.Evaluator;
import com.github.lette1394.calculator3.evaluator.FallbackEvaluator;
import com.github.lette1394.calculator3.evaluator.JustEvaluator;
import com.github.lette1394.calculator3.evaluator.MultiplyEvaluator;
import com.github.lette1394.calculator3.evaluator.NumericAdderExactly;
import com.github.lette1394.calculator3.evaluator.NumericDivider;
import com.github.lette1394.calculator3.evaluator.NumericMultiplierExactly;
import com.github.lette1394.calculator3.evaluator.NumericSubtractorExactly;
import com.github.lette1394.calculator3.evaluator.ParenthesesEvaluator;
import com.github.lette1394.calculator3.evaluator.PriorReducingEvaluator;
import com.github.lette1394.calculator3.evaluator.SubtractEvaluator;
import com.github.lette1394.calculator3.evaluator.TrimmedAdder;
import com.github.lette1394.calculator3.evaluator.TrimmedSubtractor;
import java.util.List;
import java.util.regex.Pattern;

public class ExhaustiveCalculator implements Calculator {
  private final Evaluator evaluator;

  public ExhaustiveCalculator() {
    final Evaluator operators = new FallbackEvaluator(List.of(
      new AddEvaluator(and(realNumber(), blank(), just("\\+"), blank(), realNumber()), TrimmedAdder.trim(new NumericAdderExactly())),
      new SubtractEvaluator(and(realNumber(), blank(), just("\\-"), blank(), realNumber()), TrimmedSubtractor.trim(new NumericSubtractorExactly())),
      new MultiplyEvaluator(new NumericMultiplierExactly()),
      new DivideEvaluator(new NumericDivider()),
      new JustEvaluator()
    ));

    final Evaluator priority = new FallbackEvaluator(List.of(
      new PriorReducingEvaluator(Pattern.compile("(.*)(\\+|- )(.*)"), operators),
      new PriorReducingEvaluator(Pattern.compile("(.*)([*/])(.*)"), operators)
    ));

    final Evaluator parentheses = new ParenthesesEvaluator(priority);
    evaluator = parentheses;
  }

  @Override
  public String evaluate(String expression) {
    return this.evaluator.evaluate(expression);
  }
}
