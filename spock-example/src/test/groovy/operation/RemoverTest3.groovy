package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.HttpRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

class RemoverTest3 extends Specification {
  Storage storage
  String savedId

  def setup() {
    storage = new MemoryStorage()
    savedId = "id-123"
//  existingId
//  remainingId
//  validId
  }

  def 'remove 6: Add meaning to id variable'() {
    given:
      storage.save(savedId, "message")
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      isRemoved()
  }

  private boolean isRemoved(String id) {
    return !storage.exists(id).join()
  }
}
