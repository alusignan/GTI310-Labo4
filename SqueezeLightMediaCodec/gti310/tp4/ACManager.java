package gti310.tp4;

import java.util.ArrayList;

/**Source : http://stackoverflow.com/questions/20979312/rle-compression-algorithm-java**/
public class ACManager {
	
	
	/**Complexité O(N^2)**/
	public static void encode(ArrayList<int[]> zigzagList) {
		
		for (int i = 0; i < zigzagList.size(); i++) {
			int value = 0;
			int runlength = 0;
			for (int j = 1; j < Main.BLOCK_SIZE * Main.BLOCK_SIZE; j++) {
				if (i == 0) {
					if (j == 63) {
						Entropy.writeAC(runlength, value);
					}
					runlength += 1;
				}
				else {
					value = zigzagList.get(i)[j];
					Entropy.writeAC(runlength, value);
					runlength = 0;
					value = 0;
				}
			}
		}
	}

}
