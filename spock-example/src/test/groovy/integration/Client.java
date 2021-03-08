package integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RequiredArgsConstructor
public class Client {
  private final String serverEndpoint;

  public void upload(String payload) {
    OkHttpClient client = new OkHttpClient();
    RequestBody body = RequestBody.create(payload.getBytes(StandardCharsets.UTF_8));
    Request request = new Request.Builder()
      .url(serverEndpoint)
      .post(body)
      .build();
    try (Response response = client.newCall(request).execute()) {
      assertThat(response.code(), is(201));
      assertThat(response.header("content-length"), is(201));
      assertThat(response.body(), is(null));
    } catch (IOException e) {
      throw new AssertionError("io exception", e);
    }
  }
}
