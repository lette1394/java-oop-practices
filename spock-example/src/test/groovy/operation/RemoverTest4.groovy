package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.StorageRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

import java.util.concurrent.CompletionStage

class RemoverTest4 extends Specification {
  Storage storage
  String savedId

  def setup() {
    storage = new MemoryStorage()
    savedId = "id-123"
    storage.save(savedId, "message")
  }

  def 'remove 7: Prepare storage in advance'() {
    given:
      AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      doesRemoved()
  }

  def 'remove 8: Prepare AsyncRemoveOperation using method'() {
    given:
      AsyncRemoveOperation operation = subject()
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      doesRemoved()
  }

  def 'remove 9: Add helper'() {
    given:
      AsyncRemoveOperation operation = subject()
    when:
      await operation.remove(savedId)
    then:
      doesRemoved(savedId)
  }

  private static await(CompletionStage<?> stage) {
    return stage.toCompletableFuture().join()
  }

  private boolean doesRemoved(String id) {
    return !storage.exists(id).join()
  }

  private AsyncRemoveOperation subject() {
    return new StorageRemoveOperation(storage)
  }
}
