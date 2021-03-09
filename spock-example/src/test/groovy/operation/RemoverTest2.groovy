package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.HttpRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

class RemoverTest2 extends Specification {
  Storage storage
  String id

  def setup() {
    storage = new MemoryStorage()
    id = "id-123"
  }

  def 'remove 5: Make assertion be much more readable'() {
    given:
      storage.save(id, "message")
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      isRemoved()
  }

  private boolean isRemoved(String id) {
    return !storage.exists(id).join()
  }
}
