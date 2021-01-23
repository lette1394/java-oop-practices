package com.github.lette1394.solid.X_2_output;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_5_Maybe {
  private String workingDirectory;

  public static void main(String[] args) {
    final FileStore_5_Maybe fileStore = new FileStore_5_Maybe();

    // fluent API 장점: 버그가 끼어들 틈이 없다! - 앞서 봤던 다른거랑 비교해보자
    // type-safe, read() 메서드가 더더더더 많은 정보를 준다!
    final String message = fileStore.read(13).orElse("");
  }


  // vs public String read(long id)
  // caller에게 더 많은 guarantee 를 줄 수록 caller, 즉 개발자는 이해하기도 쉽고 사용하기도 쉽다
  //
  // 이제 Optional<String>read() 메서드는 절대로
  // null Optional 객체를 반환하지 않는다는 사실을 우리는 모두 알고 있다.
  // 자연스럽게 읽힌다!
  public Optional<String> read(long id) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return Optional.of(new String(bytes));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}


// 이해하기 매우 쉽다.
// Maybe is just a collection, 오직 0개 혹은 1개의 원소만 있는 collection
// Optional.

