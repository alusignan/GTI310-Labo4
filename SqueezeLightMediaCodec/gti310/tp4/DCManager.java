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
		for (int i = 0; i < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); i++){
			int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
			zigZagMatrix[layer][0] = Entropy.readDC();
			listToZigZag.add(zigZagMatrix);
		}
	
//			int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
//			listToZigZag.add(zigZagMatrix);
//		listToZigZag.get(0)[layer][0] = Entropy.readDC();
//		for (int j = 1; j < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); j++) {
//			if (layer == 0) {
//				int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
//				listToZigZag.add(zigZagMatrix);
//			}
//			listToZigZag.get(j)[layer][0] = Entropy.readDC() + listToZigZag.get(j-1)[layer][0];
//		}

		return listToZigZag;

	}
}
