package remover

import spock.lang.Specification

import java.util.concurrent.CompletionStage

class RemoverTest4 extends Specification {
  Storage storage
  String savedId

  def setup() {
    storage = new Storage()
    savedId = "id-123"
    storage.save(savedId, "message")
  }

  def 'remove 7: Prepare storage in advance'() {
    given:
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      isRemoved()
  }

  def 'remove 8: Prepare AsyncRemoveOperation using method'() {
    given:
      AsyncRemoveOperation operation = subject()
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      isRemoved()
  }

  def 'remove 9: Add helper'() {
    given:
      AsyncRemoveOperation operation = subject()
    when:
      await operation.remove(savedId)
    then:
      isRemoved()
  }

  private static await(CompletionStage<?> stage) {
    return stage.toCompletableFuture().join()
  }

  private boolean isRemoved(String id) {
    return !storage.exists(id).join()
  }

  private AsyncRemoveOperation subject() {
    return new HttpRemoveOperation(storage)
  }
}
