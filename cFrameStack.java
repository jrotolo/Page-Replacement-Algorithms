/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       cFrameStack.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;
import prog2.cFrame;

/* Circular Linked List Stack */
public class cFrameStack {
  public cFrame head;
  public cFrame tail;
  public int size;
  public int numberOfFrames;

  public cFrameStack() {
    head = null;
    tail = null;
    size = 0;
    numberOfFrames = 1;
  }

  public cFrameStack(int numFrames) {
		head = null;
		tail = null;
		size = 0;
	  numberOfFrames = numFrames-1;
	}

  public boolean isEmpty() { return head == null; }

  public boolean isFull() { return this.size == this.numberOfFrames;  }

  public void pushHead(int data) {
    cFrame temp = new cFrame(data, null);
    temp.accessBit = 0;
    temp.link = this.head;
    if (this.head == null) {
      this.head = temp;
      temp.link = this.head;
      this.tail = this.head;
    } else {
      this.tail.link = temp;
      this.head = temp;
    }
    size++;
  }

  public void pushTail(int data) {
    cFrame temp = new cFrame(data, null);
    temp.accessBit = 0;
    temp.link = this.head;
    if (this.head == null) {
      this.head = temp;
      temp.link = this.head;
      this.tail = this.head;
    } else {
      this.tail.link = temp;
      this.tail = temp;
    }
    size++;
  }

  public cFrame popHead() {
    cFrame temp = head;
		head = head.link;
		size--;
		return temp;
	}

	public cFrame popTail() {
    cFrame temp = tail;
		tail = tail.link;
		size--;
		return temp;
	}

  public void insertAtIndex(int idx, int data) {
    cFrame newFrame = new cFrame(data, null);
    cFrame ptr = head;
    idx = idx - 1;
    for (int i=1; i<size-1; i++) {
      if (i == idx) {
        cFrame tmp = ptr.link;
        ptr.link = newFrame;
        newFrame.link = tmp;
        break;
      }
      ptr = ptr.link;
    }
    size++;
  }

  public void removeAtIndex(int idx) {
    if (size == 1 && idx == 1) {
      head = null;
      tail = null;
      size = 0;
      return;
    }
    if (idx == 1) {
      head = head.link;
      tail.link = head;
      size--;
      return;
    }
    if (idx == size) {
      cFrame h = head;
      cFrame t = head;
      while (h != tail) {
        t = h;
        h = t.link;
      }
      tail = t;
      tail.link = head;
      size--;
      return;
    }
    cFrame ptr = head;
    idx = idx-1;
    for (int i=1; i<size-1; i++) {
      if (i == idx) {
        cFrame temp = ptr.link;
        temp = temp.link;
        ptr.link = temp;
        break;
      }
      ptr = ptr.link;
    }
    size--;
  }

  public int indexOf(int val) {
    int counter = 1;
    cFrame temp = head;
    while (temp.link != head) {
      if (temp.data == val)
        return counter;
      counter++;
      temp = temp.link;
    }
    return -1;
  }

  public cFrame frameAtIndex(int idx) {
    int counter = 1;
    cFrame temp = head;
    while (temp.link != head) {
      if (counter == idx)
        return temp;
      counter++;
      temp = temp.link;
    }
    return temp;
  }

  public void print() {
    System.out.print("Page Table: ");
    cFrame temp = head;
    if (size == 0) {
      System.out.print("empty\n");
      return;
    }
    if (head.link == head) {
      System.out.println(head.data+"  ");
      return;
    }
    System.out.print(head.data+"  ");
    temp = head.link;
    while (temp.link != head) {
      System.out.print(temp.data+"  ");
      temp = temp.link;
    }
    System.out.print(temp.data+"  ");
    temp = temp.link;
    System.out.println(temp.data);
  }

  public void reversePrint() { }

}
