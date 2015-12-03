package gti310.tp4;

/**Source : http://rosettacode.org/wiki/Zig-zag_matrix#Java**/
public class ZigZagTop {
	
	public int[][] process(int[][] quantizeBlock) {
		int[][] data = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		int i = 1;
		int j = 1;
		for (int element = 0; element < Main.BLOCK_SIZE * Main.BLOCK_SIZE; element++) {
			data[i-1][j-1] = element;
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
	
	public int[][] unprocess(int[][] ACDCMatrix) {
		return null;
	}

}
