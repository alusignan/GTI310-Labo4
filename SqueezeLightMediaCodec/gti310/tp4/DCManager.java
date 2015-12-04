package gti310.tp4;

import java.util.ArrayList;

public class DCManager {
	
	/**Complexité O(N)**/
	public static void encode (ArrayList<int[]> zigzagMatrix) {
		for (int i = 0; i < zigzagMatrix.size(); i++) {
			Entropy.writeDC(zigzagMatrix.get(i)[0]);
		}
	}
}
