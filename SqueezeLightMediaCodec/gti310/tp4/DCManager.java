package gti310.tp4;

import java.util.ArrayList;

public class DCManager {
	

	
	/**Complexité O(N)**/
	public static void encode (ArrayList<int[][]> zigzagMatrix, int layer) {
		Entropy.writeDC(zigzagMatrix.get(0)[layer][0]);
		for (int i = 1; i < zigzagMatrix.size(); i++) {
			Entropy.writeDC(zigzagMatrix.get(i)[layer][0] - zigzagMatrix.get(i-1)[layer][0]);
		}
	}
	
	
	/**Compexité O(N)**/
	public static ArrayList<int[][]> decode(int height, int width, int colorSpace, int layer) {
		ArrayList<int[][]> listToZigZag = new ArrayList<int[][]>();
		int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
		for (int i = 0; i < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); i++){
			zigZagMatrix[layer][0] = Entropy.readDC();
			listToZigZag.add(zigZagMatrix);
		}
		return listToZigZag;
	}
}
