package org.ru2nuts.learn.hackerrank.ctci;

import java.util.Comparator;

/**
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem
 */
class SortingComparator {
  class Checker implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
      if (o1.score == o2.score) {
        return o1.name.compareTo(o2.name);
      } else if (o1.score > o2.score)
        return -1;
      else
        return 1;
    }
  }

  class Player {
    String name;
    int score;

    Player(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }
}