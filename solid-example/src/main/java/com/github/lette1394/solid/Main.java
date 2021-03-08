package com.github.lette1394.solid;

import java.util.List;

public class Main {
}


interface Finder {
  /**
   * @param id - must be positive
   */
  List<String> find(long id);
}









interface Finder2 {
  List<String> find(PositiveLong id);
}









class PositiveLong {}




class Wow {

}
