/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       dFrame.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;

/* Frame Nodes for Doubly Linked List */
public class dFrame {
	public int data;
	public dFrame next, prev;

	public dFrame() {
		data = 0;
		next = null; prev = null;
	}

	public dFrame(int d, dFrame n, dFrame p) {
		data = d;
		next = n;
		prev = p;
	}

	public int getData() { return data; }
}
