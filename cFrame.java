/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       cFrame.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;

/* Frame nodes for a circular linked list */
public class cFrame {
  public int data;
  public int accessBit;
  public cFrame link;

  public cFrame() {
    link = null;
    data = 0;
    accessBit = 0;
  }

  public cFrame(int d, cFrame f) {
    data = d;
    link = f;
    accessBit = 0;
  }
}
