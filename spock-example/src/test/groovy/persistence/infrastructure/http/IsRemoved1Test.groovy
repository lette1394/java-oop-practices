package persistence.infrastructure.http


import spock.lang.Specification

import java.net.http.HttpHeaders
import java.net.http.HttpResponse

import static persistence.infrastructure.http.IsRemoved.statusCodeIs200

class IsRemoved1Test extends Specification {

  def 'test 1'() {
    given:
    def httpResponse = Stub(HttpResponse) {
      statusCode() >> 200
      headers() >> HttpHeaders.of(["x-line-storage-deleted": ["true"]] as Map, (x, y) -> true)
    }
    expect:
      statusCodeIs200().test(httpResponse)
  }
}
