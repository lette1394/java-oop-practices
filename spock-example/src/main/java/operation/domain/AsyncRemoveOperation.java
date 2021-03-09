package operation.domain;

import java.util.concurrent.CompletionStage;

@FunctionalInterface
public interface AsyncRemoveOperation {
  CompletionStage<Void> remove(String id);
}








