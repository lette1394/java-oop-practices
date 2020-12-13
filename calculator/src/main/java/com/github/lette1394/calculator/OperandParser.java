package com.github.lette1394.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class OperandParser implements Parser<Operand> {
  @Override
  public List<Operand> parse(String expression) {
    return Arrays
      .stream(expression.split("[^0-9]"))
      .map(String::trim)
      .filter(StringUtils::isNotBlank)
      .map(NumberOperand::of)
      .collect(Collectors.toList());
  }
}
