package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.StorageRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

import java.util.concurrent.CompletionStage

class RemoverTest5 extends Specification {
  Storage storage
  String savedId

  def setup() {
    storage = new MemoryStorage()
    savedId = anyNonBlankString()
    storage.save(savedId, anyNonBlankString())
  }

  def 'remove 10: Add constraint to test class field'() {
    given:
      AsyncRemoveOperation operation = subject()
    when:
      await operation.remove(savedId)
    then:
      doesRemoved()
  }

  def 'remove 11: Apply type hide of groovy/java'() {
    given:
      var operation = subject()
    when:
      await operation.remove(savedId)
    then:
      doesRemoved()
  }

  def 'remove 12: Delete meaningless given label'() {
    when:
      await subject().remove(savedId)
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

  private static String anyNonBlankString() {
    return "<any-string>" // may be random string
  }
}
