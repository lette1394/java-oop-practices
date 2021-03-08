import org.mockserver.client.MockServerClient
import org.testcontainers.containers.MockServerContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import static org.mockserver.model.HttpRequest.request
import static org.mockserver.model.HttpResponse.response

@Testcontainers
class MockServerContainerTest extends Specification {
  MockServerContainer mockServer = new MockServerContainer("5.11.2")

  void setup() {
    new MockServerClient(mockServer.getHost(), mockServer.getServerPort())
      .when(request()
        .withMethod("GET")
        .withPath("/person")
        .withQueryStringParameter("name", "peter"))
      .respond(response()
        .withHeader('hello', 'world!')
        .withBody("Peter the person!"))
  }

  void testSimplePutAndGet() {
    given:
      def uri = URI.create(String.format("%s/person?name=peter", mockServer.endpoint))
      def request = HttpRequest.newBuilder(uri)
        .GET()
        .build()
    when:
      def response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
      println response.headers()

    then:
      response.body() == 'Peter the person!'

      with(response.headers()) {
        it.firstValue('hello').get() == 'world!!'
      }
  }
}