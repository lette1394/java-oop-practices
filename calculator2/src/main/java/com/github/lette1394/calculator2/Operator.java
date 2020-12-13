package com.github.lette1394.calculator2;

@FunctionalInterface
public interface Operator {
  Expression apply(Expression left, Expression right);
}
