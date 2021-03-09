package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.HttpRemoveOperation
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
      isRemoved()
  }

  def 'remove 11: Apply type hide of groovy/java'() {
    given:
      var operation = subject()
    when:
      await operation.remove(savedId)
    then:
      isRemoved()
  }

  def 'remove 12: Delete meaningless given label'() {
    when:
      await subject().remove(savedId)
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

  private static String anyNonBlankString() {
    return "<any-string>" // may be random string
  }
}
