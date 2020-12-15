package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.List;

public class FindFirstOperatorFactory implements OperatorFactory {
  private final List<OperatorFactory> operatorRepositories;

  public FindFirstOperatorFactory(List<OperatorFactory> operatorRepositories) {
    this.operatorRepositories = operatorRepositories;
  }

  public FindFirstOperatorFactory(OperatorFactory... operatorRepositories) {
    this(List.of(operatorRepositories));
  }

  @Override
  public Operator find(String operatorAsString) throws OperatorNotFoundException {
    final RuntimeException ex = new OperatorNotFoundException(
      format("Cannot find operator: [%s]", operatorAsString));

    for (OperatorFactory repository : operatorRepositories) {
      try {
        return repository.find(operatorAsString);
      } catch (OperatorNotFoundException e) {
        ex.addSuppressed(e);
      }
    }

    throw ex;
  }
}
