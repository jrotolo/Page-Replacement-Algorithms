/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       PageReplacement.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;
import prog2.dFrameStack;
import prog2.cFrameStack;

public class PageReplacement {

  /* PageReplacement Driver Function */
  public static void pageReplacement(String algorithm, int numOfFrames) {
    //int[] pages = {4,7,0,7,1,0,1,2,1,2,7};
    int[] pages = {0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5, 4, 5, 0, 2};
    if (algorithm.equals("LRU"))
      pageReplacementLRU(new dFrameStack(), new dFrameStack(), pages, numOfFrames);
    else if (algorithm.equals("CLOCK"))
      pageReplacementCLOCK(new cFrameStack(), pages, numOfFrames);
    else if (algorithm.equals("BOTH")) {
      pageReplacementLRU(new dFrameStack(), new dFrameStack(), pages, numOfFrames);
      pageReplacementCLOCK(new cFrameStack(), pages, numOfFrames);
    } else
      System.out.println("Error: no algorithm found for "+algorithm);
  }

  /* LRU Page Replacement Algorithm */
  public static void pageReplacementLRU(dFrameStack memStack, dFrameStack lruStack, int[] pages, int numOfFrames) {
    memStack = new dFrameStack(numOfFrames);
    lruStack = new dFrameStack();
    int hitCounter = 0, missCounter = 0;
    int index;

    printHeader("LRU", numOfFrames);

    /* LRU Implementation */
    for (int i=0; i < pages.length; i++) {
      /* Base Case - Stack is Empty */
      if (memStack.isEmpty()) {
        memStack.pushHead(pages[i]);
        memStack.reversePrint();
        // Update LRU
        index = lruStack.indexOf(pages[i]);
        lruStack.pushHead(pages[i]);
        if (index != -1)
          lruStack.removeAtIndex(index);
        missCounter++;

      /* Stack not full and Page Miss */
      } else if (!memStack.isFull() && memStack.indexOf(pages[i]) == -1) {
        memStack.pushHead(pages[i]);
        memStack.reversePrint();
        // Update LRU
        index = lruStack.indexOf(pages[i]);
        lruStack.pushHead(pages[i]);
        if (index != -1)
          lruStack.removeAtIndex(index);
        missCounter++;

      /* Stack not full and Page Hit */
      } else if (!memStack.isFull() && memStack.indexOf(pages[i]) != -1) {
        // Update LRU
        index = lruStack.indexOf(pages[i]);
        if (index != -1)
          lruStack.removeAtIndex(index);
        lruStack.pushHead(pages[i]);
        hitCounter++;
        memStack.reversePrint();

      /* Stack is Full and Page Miss */
      } else if (memStack.isFull() && memStack.indexOf(pages[i]) == -1) {
        // POP LRU from LRU-stack
        // Use popped value to to update memStack
        index = memStack.indexOf(lruStack.tail.data);
        if (index != -1) {
          memStack.removeAtIndex(index);
          memStack.insertAtIndex(index, pages[i]);
          memStack.reversePrint();
        }
        // Update LRU with new page #
        lruStack.pushHead(pages[i]);
        lruStack.popTail();
        missCounter++;

      /* Stack is Full and Page Hit */
      } else if (memStack.isFull() && (memStack.indexOf(pages[i]) != -1)) {
        // Update LRU stack
        index = lruStack.indexOf(pages[i]);
        if (index != -1)
          lruStack.removeAtIndex(index);
        lruStack.pushHead(pages[i]);
        hitCounter++;
        memStack.reversePrint();
      }
    }
    memStack.reversePrint();
    System.out.println("\nTotal References: "+(missCounter+hitCounter)+" | Misses: "+missCounter+" | Hits: "+hitCounter+"\n");
  }

  public static void pageReplacementCLOCK(cFrameStack memStack, int[] pages, int numOfFrames) {
    memStack = new cFrameStack(numOfFrames);
    int hitCounter = 0, missCounter = 0;
    int index = 1, clkHand = 1;

    printHeader("CLOCK", numOfFrames);

    /* CLOCK Implementation */
    for (int i=0; i < pages.length; i++) {
      // Stack is Empty - Base Case
      if (memStack.isEmpty()) {
        memStack.pushHead(pages[i]);
        memStack.print();
        missCounter++;
        clkHand++;
        if (clkHand == numOfFrames+1)
          clkHand = 1;

      // Stack is not full - Page Miss
      } else if (!memStack.isFull() && memStack.indexOf(pages[i]) == -1) {
        memStack.pushHead(pages[i]);
        memStack.print();
        missCounter++;
        clkHand++;
        if (clkHand == numOfFrames+1)
          clkHand = 1;

      // Stack is not full - Page Hit
      } else if (!memStack.isFull() && memStack.indexOf(pages[i]) != -1) {
        index = memStack.indexOf(pages[i]);
        memStack.frameAtIndex(index).accessBit = 1;
        memStack.print();
        hitCounter++;

      // Stack is full - Page Miss
      } else if (memStack.isFull() && memStack.indexOf(pages[i]) == -1) {
        cFrame temp = memStack.frameAtIndex(clkHand);
        cFrame prev = temp;
        while (temp.link != prev) {
          if (temp.accessBit == 1)
            temp.accessBit = 0;
          else if (temp.accessBit == 0) {
            temp.data = pages[i];
            break;
          }
          temp = temp.link;
        }
        memStack.print();
        missCounter++;
        clkHand++;
        if (clkHand == numOfFrames+1)
          clkHand = 1;

      // Stack is full - Page Hit
      } else if (memStack.isFull() && memStack.indexOf(pages[i]) != -1) {
        // Find page # on stack
        index = memStack.indexOf(pages[i]);
        cFrame temp = memStack.frameAtIndex(index);
        // Set its accessBit = !accessBit
        temp.accessBit = temp.accessBit == 1 ? 0 : 1;
        memStack.print();
        hitCounter++;
      }
    }
    System.out.println("\nTotal References: "+(missCounter+hitCounter)+" | Misses: "+missCounter+" | Hits: "+hitCounter+"\n");
  }

  public static void printHeader(String name, int numberOfFrames) {
    System.out.println(name+" Page Replacement Simulation");
		System.out.println("=====================================================");
    System.out.print("Frame       ");
		for (int i=1; i<=numberOfFrames; i++)
			System.out.print(i+"  ");
		System.out.println();
		System.out.println("-----------------------------------------------------");
  }
}
