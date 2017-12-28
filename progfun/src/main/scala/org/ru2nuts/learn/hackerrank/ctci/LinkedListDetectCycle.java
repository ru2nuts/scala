package org.ru2nuts.learn.hackerrank.ctci;

/**
 * https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem
 */
public class LinkedListDetectCycle {
//Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

  class Node {
    int data;
    Node next;
  }

  boolean hasCycle(Node head) {
    Node slowRunner = head;
    Node fastRunner = head;
    while (slowRunner != null && fastRunner != null && fastRunner.next != null) {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next.next;
      if (slowRunner == fastRunner)
        return true;
    }
    return false;
  }
}
