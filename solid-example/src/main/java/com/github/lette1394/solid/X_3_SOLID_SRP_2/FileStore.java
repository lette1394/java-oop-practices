package com.github.lette1394.solid.X_3_SOLID_SRP_2;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import com.github.lette1394.solid.X_3_SOLID_SRP_1.StoreLogger;
import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import lombok.Getter;

public class FileStore {
  @Getter
  private final String workingDirectory;
  private final StoreCache cache;
  private final StoreLogger log;

  public FileStore(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException("workingDirectory is null");
    }
    if (!Path.of(workingDirectory).toFile().exists()) {
      throw new IllegalArgumentException("workingDirectory not exist");
    }
    this.workingDirectory = workingDirectory;
    this.cache = new StoreCache();
    this.log = new StoreLogger();
  }

  public void save(long id, String message) {
    log.saving(id);
    File file = getFile(id);
    Files.write(file.toPath(), message);
    cache.put(id, message);
    log.saved(id);
  }

  public Optional<String> read(long id) {
    log.reading(id);
    File file = getFile(id);
    if (!file.exists()) {
      log.didNotFound(id);
      return Optional.empty();
    }
    String message = cache.computeIfAbsent(id,
      () -> Files.readAllBytes(file.toPath()));
    log.returning(id);
    return Optional.of(message);
  }

  public File getFile(long id) {
    return Path.of(workingDirectory, id + ".txt").toFile();
  }
}
//
//
//
//
//
//
//
//






























