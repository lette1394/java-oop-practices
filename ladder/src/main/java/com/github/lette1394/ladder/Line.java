package com.github.lette1394.ladder;

import java.util.function.Consumer;

public class Line implements Draw {
  private final Draw prev;
  private final Draw branch;

  public Line(Draw prev, Draw branch) {
    this.prev = prev;
    this.branch = branch;
  }

  @Override
  public Draw down() {
    return new Line(prev, branch.down());
  }

  @Override
  public Draw left() {
    return new Line(prev, branch.left());
  }

  @Override
  public Draw right() {
    return new Line(prev, branch.right());
  }

  @Override
  public Draw visit(Consumer<Dot> dotConsumer) {
    prev.visit(dotConsumer);
    branch.visit(dotConsumer);
    return this;
  }
}
