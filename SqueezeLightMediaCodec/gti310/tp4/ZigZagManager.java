package gti310.tp4;

import java.util.ArrayList;

/**Source : http://rosettacode.org/wiki/Zig-zag_matrix#Java**/
public class ZigZagManager {
	private static ArrayList<int[][][]> listToDequantize = new ArrayList<int[][][]>();
	
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
	
	/**Complexité O(N^2)**/
	public static void gazgiz(ArrayList<int[][]> zigZagList, int layer) {
			for (int k = 0; k < zigZagList.size(); k++) {
				if (layer == 0) {
					int[][][] data = new int[Main.COLOR_SPACE_SIZE][Main.BLOCK_SIZE][Main.BLOCK_SIZE];
					listToDequantize.add(data);
				}
				int i = 1;
				int j = 1;
				for (int element = 0; element < Main.BLOCK_SIZE * Main.BLOCK_SIZE; element++) {
					listToDequantize.get(k)[layer][i-1][j-1] = zigZagList.get(k)[layer][element];
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
			}

	}

	public static ArrayList<int[][][]> getIZigZagList() {
		return listToDequantize;
	}
}
