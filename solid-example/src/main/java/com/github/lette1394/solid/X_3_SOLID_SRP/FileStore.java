package com.github.lette1394.solid.X_3_SOLID_SRP;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FileStore {
  @Getter
  private final String workingDirectory;
  private final Map<Long, String> cache;

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