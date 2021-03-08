package com.github.lette1394.solid.X_7_SOLID_DIP_4;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

public class FileStore implements StoreReader, StoreWriter, FileLocator {
  private final String workingDirectory;

  public FileStore(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException("workingDirectory is null");
    }
    if (!Path.of(workingDirectory).toFile().exists()) {
      throw new IllegalArgumentException("workingDirectory not exist");
    }
    this.workingDirectory = workingDirectory;
  }

  @Override
  public Optional<String> read(long id) {
    final File file = getFile(id);
    if (file.exists()) {
      return Optional.of(Files.readAllBytes(getFile(id).toPath()));
    }
    return Optional.empty();
  }

  @Override
  public void save(long id, String string) {
    Files.write(getFile(id).toPath(), string);
  }

  @Override
  public File getFile(long id) {
    return Path.of(workingDirectory, id + ".txt").toFile();
  }
}
