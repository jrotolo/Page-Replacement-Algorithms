/*
  Name:       Rotolo, Jarrod
  Project:    PA-2 (Page Replacement Algorithms)
  File:       Main.java
  Instructor: Feng Chen
  Class:      cs4103-sp15
  LogonID:    cs410331
*/

package prog2;
import prog2.PageReplacement;

/* Main Runner Class */
public class Main {

	public static void main(String[] args) {
		PageReplacement prSim = new PageReplacement();
		String prAlgorithm = null;
		int numberOfFrames = 3;	// Set number of frames to 3 by default

		if (args.length != 4)
			System.err.println("Usage: java Main -s <algo> -n <numberOfFrames>");
		else {
			prAlgorithm = args[1];
			numberOfFrames = Integer.parseInt(args[3]);
		}

		prSim.pageReplacement(prAlgorithm, numberOfFrames);
	}
}
