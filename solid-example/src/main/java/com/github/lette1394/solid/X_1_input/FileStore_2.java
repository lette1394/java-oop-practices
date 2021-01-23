package com.github.lette1394.solid.X_1_input;

import java.io.IOException;

public class FileStore_2 {
  public static void main(String[] args) throws IOException {

    // hmm... looks fine
    final FileStore fileStore = new FileStore();
    fileStore.save(99, "hello world");  // will fail
  }
}
