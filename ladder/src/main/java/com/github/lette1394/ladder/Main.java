package com.github.lette1394.ladder;

public class Main {
  public static void main(String[] args) {
    Dot rootDot = Dot.starting(0);

    rootDot
      .down()
      .down()
      .down()
      .right()
      .right()
      .right()
      .down()
      .down()
      .left()
      .left()
      .left()
      .down()
      .down()
      .visit(dot -> System.out.println(dot));
  }
}
