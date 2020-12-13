package com.github.lette1394.ladder;

import java.util.function.Consumer;

public interface Draw {
  Draw down();

  Draw left();

  Draw right();

  Draw visit(Consumer<Dot> dotConsumer);
}
