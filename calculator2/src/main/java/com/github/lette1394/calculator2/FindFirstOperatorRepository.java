package com.github.lette1394.calculator2;

import static java.lang.String.format;

import java.util.List;

public class FindFirstOperatorRepository implements OperatorRepository {
  private final List<OperatorRepository> operatorRepositories;

  public FindFirstOperatorRepository(List<OperatorRepository> operatorRepositories) {
    this.operatorRepositories = operatorRepositories;
  }

  public FindFirstOperatorRepository(OperatorRepository... operatorRepositories) {
    this(List.of(operatorRepositories));
  }

  @Override
  public Operator find(String operatorAsString) throws OperatorNotFoundException {
    final RuntimeException ex = new OperatorNotFoundException(
      format("Cannot find operator: [%s]", operatorAsString));

    for (OperatorRepository repository : operatorRepositories) {
      try {
        return repository.find(operatorAsString);
      } catch (OperatorNotFoundException e) {
        ex.addSuppressed(e);
      }
    }

    throw ex;
  }
}
