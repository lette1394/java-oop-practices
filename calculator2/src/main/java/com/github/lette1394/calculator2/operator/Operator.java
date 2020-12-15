package com.github.lette1394.calculator2.operator;

import com.github.lette1394.calculator2.expression.Expression;

@FunctionalInterface
public interface Operator {
  Expression apply(Expression left, Expression right);
}
