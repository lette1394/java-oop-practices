package com.github.lette1394.solid.X_3_SOLID_SRP;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileStore {
  @Getter
  private final String workingDirectory;
  private final Map<Long, String> cache;

  public FileStore(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException("workingDirectory is null");
    }
    if (!Path.of(workingDirectory).toFile().exists()) {
      throw new IllegalArgumentException("workingDirectory not exist");
    }
    this.workingDirectory = workingDirectory;
    this.cache = new HashMap<>();
  }

  public void save(long id, String message) {
    log.info("Saving message [{}]", id);
    File file = this.getFile(id);
    Files.write(file.toPath(), message);
    this.cache.put(id, message);
    log.info("Saved message [{}]", id);
  }

  public Optional<String> read(long id) {
    log.debug("Reading message: [{}]", id);
    File file = this.getFile(id);
    if (!file.exists()) {
      log.debug("No message [{}] found", id);
      return Optional.empty();
    }
    String message = this.cache.computeIfAbsent(id,
      __ -> Files.readAllBytes(file.toPath()));
    log.debug("Returning message [{}]", id);
    return Optional.of(message);
  }

  public File getFile(long id) {
    return Path.of(this.workingDirectory, id + ".txt").toFile();
  }
}





// 질문: 읽기 쉬운가?
//  쉽다는 뜻의 정의:
//  실제로 무엇을 하고 있는지만 간결하게 표현하고 있는가?
//  그렇지 않다: cache, logging 등으로 방해받고 있어서