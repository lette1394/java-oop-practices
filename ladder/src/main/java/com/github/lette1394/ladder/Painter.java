package com.github.lette1394.ladder;

public class Painter {
  StringBuilder sb = new StringBuilder();
  int[][] m = new int[5][5];

  public String paint(Draw draw) {

    draw.visit(dot -> m[dot.getRow()][dot.getCol()] = 1);

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        if (m[row][col] == 0) {
          sb.append("ㅣ");
          continue;
        }
        sb.append(ch(row, col));
      }
      sb.append("\n");
      System.out.println();
    }

    System.out.println();
    System.out.println();

    return sb.toString();
  }

  private void draw(String str) {
    sb.append(str);
  }

  private String ch(int row, int col) {

    if (m[row][col] == 1 && m[row+1][col+1] == 1) {
      return "ㅏ";
    }
    if (row > 0 && m[row-1][col] == 1) {
      return "ㅓ";
    }
    return "ㅣ";
  }
}
