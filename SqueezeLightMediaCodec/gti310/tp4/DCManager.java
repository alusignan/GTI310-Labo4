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
	
	
	/**Compexité O(N^2)**/
	public static ArrayList<int[][]> decode(int height, int width, int colorSpace, int layer) {
		ArrayList<int[][]> zigZagList = new ArrayList<int[][]>();
		int zigZagMatrix[][] = new int[colorSpace][Main.BLOCK_SIZE*Main.BLOCK_SIZE];
		if (layer == 0) {
			for (int j = 0; j < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); j++) {
				zigZagMatrix[layer][0] = Entropy.readDC();
				zigZagList.add(zigZagMatrix);
			}
		}
		else {
			for (int j = 0; j < (height/Main.BLOCK_SIZE) * (width/Main.BLOCK_SIZE); j++) {
				zigZagList.get(j)[layer][0] = Entropy.readDC() + zigZagList.get(j-1)[layer][0];
			}
		}
		
		return zigZagList;

	}
}
