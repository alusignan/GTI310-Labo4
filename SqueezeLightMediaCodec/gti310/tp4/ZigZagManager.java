package gti310.tp4;

import java.util.ArrayList;

/**Source : http://rosettacode.org/wiki/Zig-zag_matrix#Java**/
public class ZigZagManager {
	
	/**Complexité O(N)**/
	public static int[] zigzag(int[][] quantizeBlock) {
		int[] data = new int[Main.BLOCK_SIZE * Main.BLOCK_SIZE];
		int i = 1;
		int j = 1;
		for (int element = 0; element < Main.BLOCK_SIZE * Main.BLOCK_SIZE; element++) {
			data[element] = quantizeBlock[i-1][j-1];
			if ((i + j) % 2 == 0) {
				//Even stripes
				if (j < Main.BLOCK_SIZE) {
					j++;
				}
				else {
					i += 2;
				}
				if (i > 1) {
					i--;
				}
			}
			else {
				//Odd stripes
				if (i < Main.BLOCK_SIZE) {
					i++;
				}
				else {
					j += 2;
				}
				if (j > 1) {
					j--;
				}
			}
		}
		
		return data;
	}
	
	public static int[][][] gazgiz(ArrayList<int[][]> zigZagList, int colorSpace) {
		for (int layer = 0; layer < zigZagList.size(); layer++) {
			int[][][] data = new int[colorSpace][Main.BLOCK_SIZE][Main.BLOCK_SIZE];
			int i = 1;
			int j = 1;
			for (int element = 0; element < Main.BLOCK_SIZE * Main.BLOCK_SIZE; element++) {
				data[layer][i-1][j-1] = zigZagList.get(layer)[layer][element];
				if ((i + j) % 2 == 0) {
					//Even stripes
					if (j < Main.BLOCK_SIZE) {
						j++;
					}
					else {
						i += 2;
					}
					if (i > 1) {
						i--;
					}
				}
				else {
					//Odd stripes
					if (i < Main.BLOCK_SIZE) {
						i++;
					}
					else {
						j += 2;
					}
					if (j > 1) {
						j--;
					}
				}
			}
			
			return data;
		}
		return null;
	}

}
