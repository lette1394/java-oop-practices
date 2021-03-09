package persistence.infrastructure.http

import spock.lang.Specification

import static persistence.infrastructure.http.IsRemoved2.isDeleted
import static persistence.infrastructure.http.IsRemoved2.statusCodeIs200

class IsRemoved2Test extends Specification {
  def 'test1'() {
    expect:
      statusCodeIs200().test(RemoveResponse.builder()
        .statusCode(200)
        .build())

      !statusCodeIs200().test(RemoveResponse.builder()
        .statusCode(401)
        .build())

      !statusCodeIs200().test(RemoveResponse.builder()
        .statusCode(500)
        .build())
  }

  def 'test2'() {
    expect:
      isDeleted().test(RemoveResponse.builder()
        .xLineStorageDeleted(Optional.of("true"))
        .build())

      !isDeleted().test(RemoveResponse.builder()
        .xLineStorageDeleted(Optional.of("TRUE"))
        .build())

      !isDeleted().test(RemoveResponse.builder()
        .xLineStorageDeleted(Optional.of("True"))
        .build())
  }
}
