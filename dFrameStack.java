/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       dFrameStack.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;
import prog2.dFrame;

/* Doubly Linked List Stack */
public class dFrameStack {
	public dFrame head;
	public dFrame tail;
	public int size;
	public int numberOfFrames;

	public dFrameStack() {
		head = null;
		tail = null;
		size = 0;
		numberOfFrames = 1;
	}

	public dFrameStack(int numFrames) {
		head = null;
		tail = null;
		size = 0;
		numberOfFrames = numFrames;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public boolean isFull() {
		return this.size == this.numberOfFrames;
	}

	public int getSize() {
		return size;
	}

	public void pushHead(int data) {
		dFrame temp = new dFrame(data, null, null);
		if (head == null) {
			head = temp;
			tail = head;
		} else {
			head.prev = temp;
			temp.next = head;
			head = temp;
		}
		size++;
	}

	public void pushTail(int data) {
		dFrame temp = new dFrame(data, null, null);
		if (head == null) {
			head = temp;
			tail = head;
		} else {
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
		size++;
	}

	public dFrame popHead() {
		dFrame tmp = head;
		head = head.next;
		head.prev = null;
		size--;
		return tmp;
	}

	public dFrame popTail() {
		dFrame tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;
		return tmp;
	}

	public void insertAtIndex(int idx, int data) {
		dFrame newdFrame = new dFrame(data, null, null);
		if (idx == 1) {
			pushHead(data);
			return;
		} else if (idx == 3) {
			pushTail(data);
			return;
		}
		dFrame temp = head;
		for (int i=2; i<= size; i++) {
			if (i == idx) {
				dFrame x = temp.next;
				temp.next = newdFrame;
				newdFrame.prev = temp;
				newdFrame.next = x;
				x.prev = newdFrame;
			}
			temp = temp.next;
		}
		size++;
	}

	public void removeAtIndex(int idx) {
		if (idx == 1) {
			if (size == 1) {
				head = null;
				tail = null;
				size = 0;
				return;
			}
			head = head.next;
			head.prev = null;
			size--;
			return;
		}
		if (idx == size) {
			tail = tail.prev;
			tail.next = null;
			size--;
		}
		dFrame temp = head.next;
		for (int i=2; i <= size; i++) {
			if (i == idx) {
				dFrame prev = temp.prev;
				dFrame next = temp.next;

				prev.next = next;
				next.prev = prev;
				size--;
				return;
			}
			temp = temp.next;
		}
	}

	public int indexOf(int data) {
		dFrame temp = head;
		int counter = 1;
		while (temp != null) {
			if (temp.data == data)
				return counter;
			temp = temp.next;
			counter++;
		}
		return -1;
	}

	public void print() {
		System.out.print("Page Table: ");
		if (size == 0) {
			System.out.print("empty\n");
			return;
		}
		if (head.next == null) {
			System.out.println(head.getData()+"  ");
			return;
		}
		dFrame temp = head;
		System.out.print(head.getData()+"  ");
		temp = head.next;
		while (temp.next != null) {
		  System.out.print(temp.getData()+"  ");
			temp = temp.next;
		}
		System.out.print(temp.getData()+"\n");
	}

	public void reversePrint() {
		System.out.print("Page Table: ");
		if (size == 0) {
			System.out.print("emtpy\n");
			return;
		}
		if (head.next == null) {
			System.out.println(head.getData()+"  ");
			return;
		}
		dFrame temp = head;
		while (temp.next != null) temp = temp.next;
		System.out.print(temp.getData()+"  ");
		temp = temp.prev;
		while (temp.prev != null) {
			System.out.print(temp.getData()+"  ");
			temp = temp.prev;
		}
		System.out.print(temp.getData()+"\n");
	}
}
