package gti310.tp4;

import java.util.ArrayList;

public class DCManager {
	

	
	/**Complexit� O(N)
	 * �crit les coefficient DC dans le fichier**/
	public static void encode (ArrayList<int[][]> zigzagMatrix, int layer) {
		Entropy.writeDC(zigzagMatrix.get(0)[layer][0]);
		for (int i = 1; i < zigzagMatrix.size(); i++) {
			Entropy.writeDC(zigzagMatrix.get(i)[layer][0] - zigzagMatrix.get(i-1)[layer][0]);
		}
	}
	
	
	/**Compexit� O(N)
	 * Lit les coefficients DC du fichier SZL**/
	public static ArrayList<int[][]> decode(int height, int width, int colorSpace, int layer) {
		ArrayList<int[][]> listToZigZag = new ArrayList<int[][]>();
		int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
		zigZagMatrix[layer][0] = Entropy.readDC();
		listToZigZag.add(zigZagMatrix);
		for (int i = 1; i < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); i++){
			listToZigZag.add(new int[colorSpace][Main.BLOCK_SIZE * Main.BLOCK_SIZE]);
			listToZigZag.get(i)[layer][0] = Entropy.readDC() + listToZigZag.get(i-1)[layer][0];
		}
		return listToZigZag;
	}
}
