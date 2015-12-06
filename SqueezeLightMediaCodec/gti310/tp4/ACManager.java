package gti310.tp4;

import java.util.ArrayList;

/**Source : http://stackoverflow.com/questions/20979312/rle-compression-algorithm-java**/
public class ACManager {
	
	
	/**Complexité O(N^2)
	 * Lit la matrice zigzag afin d'écrire les coefficients AC dans le fichier**/
	public static void encode(ArrayList<int[][]> zigzagList, int layer) {
		
		for (int i = 0; i < zigzagList.size(); i++) {
			int value = 0;
			int runlength = 0;
			for (int j = 1; j < Main.BLOCK_SIZE * Main.BLOCK_SIZE; j++) {
				if (zigzagList.get(i)[layer][j] == 0) {
					if (j == 63) {
						Entropy.writeAC(runlength, value);
					}
					runlength += 1;
				}
				else {
					value = zigzagList.get(i)[layer][j];
					Entropy.writeAC(runlength, value);
					runlength = 0;
					value = 0;
				}
			}
		}
	}
	
	/**Complexité O(N^3)
	 * Lit les coefficients AC pour les placer dans une liste**/
	public static void decode(ArrayList<int[][]> zigZagList, int layer) {
		
		for (int i = 0; i < zigZagList.size(); i++) {
			int runlength = 1;
			while (runlength < 64) {
				int[] acData = Entropy.readAC();
				if (acData[0] != 0) {
					for (int k = 0; k < acData[0]; k++) {
						zigZagList.get(i)[layer][runlength] = 0;
						runlength += 1;
					}
					zigZagList.get(i)[layer][runlength] = acData[1];
					runlength += 1;
				}
				else if ((acData[0] == 0) && (acData[1] == 0)) {
					for (int k = runlength; k < Main.BLOCK_SIZE * Main.BLOCK_SIZE; k++) {
						zigZagList.get(i)[layer][runlength] = 0;
						runlength += 1;
					}
				}
				else {
					zigZagList.get(i)[layer][runlength] = acData[1];
					runlength += 1;
				}
			}	
		}	
	}

}
