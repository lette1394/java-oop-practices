package com.github.lette1394.ladder;

public class Main {
  public static void main(String[] args) {
    final Draw draw = Dot.starting(0)
      .down()
      .right()
      .down()
      .left()
      .down();


    final Draw draw2 = Dot.starting(3)
      .down()
      .down()
      .left()
      .down();

    Painter painter = new Painter();

    System.out.println(painter.paint(draw));
    System.out.println(painter.paint(draw2));
  }
}
