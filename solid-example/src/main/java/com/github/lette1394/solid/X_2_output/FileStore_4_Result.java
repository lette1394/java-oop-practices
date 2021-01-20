package com.github.lette1394.solid.X_2_output;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_4_Result {
  private String workingDirectory;

  public static void main(String[] args) {
    final FileStore_4_Result fileStore = new FileStore_4_Result();

    final ReadResult read = fileStore.read(13);
    if (read.isExist()) { // Result 클래스는 문제를 해결해주지 않는다.
      // null check에서 이름만 달라진 is-success check를 해야한다.
      // fluent API에 방해되는 것도 여전하다
      final String message = read.getMessage();
    }
  }

  public ReadResult read(long id) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return new ReadResult(true, new String(bytes));
    } catch (Exception e) {
      return new ReadResult(false, null);
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }

  @Data
  @AllArgsConstructor
  static class ReadResult {
    boolean exist;
    String message;
  }
}
