package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.List;

public class FindFirstOperatorFinder implements OperatorFinder {
  private final List<OperatorFinder> operatorRepositories;

  public FindFirstOperatorFinder(List<OperatorFinder> operatorRepositories) {
    this.operatorRepositories = operatorRepositories;
  }

  public FindFirstOperatorFinder(OperatorFinder... operatorRepositories) {
    this(List.of(operatorRepositories));
  }

  @Override
  public Operator find(String operatorAsString) throws OperatorNotFoundException {
    final RuntimeException ex = new OperatorNotFoundException(
      format("Cannot find operator: [%s]", operatorAsString));

    for (OperatorFinder repository : operatorRepositories) {
      try {
        return repository.find(operatorAsString);
      } catch (OperatorNotFoundException e) {
        ex.addSuppressed(e);
      }
    }

    throw ex;
  }
}
