package com.github.lette1394.solid.X_1_input;

public class FileStore_Input_2 {
  public static void main(String[] args) {

    // hmm... looks fine
    final FileStore_Input fileStore = new FileStore_Input();
    fileStore.save(99, "hello world");  // will fail
  }
}
