package integration;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;

public class AlwaysAllowingAccessControllingServer {
  private static final MockServerContainer mockServer = new MockServerContainer("5.11.2");
  private final String path = "/auth";

  static {
    mockServer.start();
  }

  public boolean allowed() {
    return true;
  }

  public AlwaysAllowingAccessControllingServer run() {
    new MockServerClient(mockServer.getHost(), mockServer.getServerPort())
      .when(request()
        .withMethod("GET")
        .withPath(path))
      .respond(response()
        .withStatusCode(200));

    return this;
  }

  public String getEndpoint() {
    return String.format("%s/%s", mockServer.getEndpoint(), path);
  }
}
