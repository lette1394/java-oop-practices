package com.github.lette1394.ladder;

import java.util.function.Consumer;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Dot implements Draw {
  private final int row;
  private final int col;

  private Dot(int row, int col) {
    Contracts.requires(row >= 0, "requires row >= 0");
    Contracts.requires(col >= 0, "requires col >= 0");

    this.row = row;
    this.col = col;
  }

  public static Dot starting(int col) {
    return of(0, col);
  }

  public static Dot of(int row, int col) {
    return new Dot(row, col);
  }

  @Override
  public Draw down() {
    return new Line(this, new Dot(row + 1, col));
  }

  @Override
  public Draw left() {
    return new Line(this, new Dot(row, col - 1));
  }

  @Override
  public Draw right() {
    return new Line(this, new Dot(row, col + 1));
  }

  @Override
  public Draw visit(Consumer<Dot> dotConsumer) {
    dotConsumer.accept(this);
    return this;
  }
}
