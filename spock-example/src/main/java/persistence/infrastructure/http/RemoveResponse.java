package persistence.infrastructure.http;

import java.util.Optional;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class RemoveResponse {
  int statusCode;
  Optional<String> xLineStorageDeleted;
}
