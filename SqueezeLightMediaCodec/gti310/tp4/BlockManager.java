package gti310.tp4;

import java.util.ArrayList;

public class BlockManager {

	static float [][][] blockArray = new float[Main.COLOR_SPACE_SIZE][Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	
	/**On va appeler cette méthode plusieurs fois lors de la conversion
	 * O(N^2) **/
	//Divide image in single 8x8 blocks
	public static float[][][] splitImage(float[][][] image, int height, int width) {
		for (int i = 0; i < Main.BLOCK_SIZE; i++) {
			for (int j = 0; j < Main.BLOCK_SIZE; j++) {
				blockArray[Main.Y][i][j] = image[Main.Y][i+height][j+width];
				blockArray[Main.Cb][i][j] = image[Main.Cb][i+height][j+width];
				blockArray[Main.Cr][i][j] = image[Main.Cr][i+height][j+width];
			}
		}
		return blockArray;
	}
	
	/**Complexité O(N^4)
	 * Prend les blocs 8x8 et les replace afin de recréer l'image originale**/
	public static float[][][] mergeBlocks(ArrayList<float[][][]> blockList, int height, int width) {
		float[][][] YCbCrImage = new float[Main.COLOR_SPACE_SIZE][height][width];
		int position = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i%8 == 0) && (j%8 == 0)) {
					for (int k = 0; k < Main.BLOCK_SIZE; k++) {
						for (int l = 0; l < Main.BLOCK_SIZE; l++) {
							YCbCrImage[Main.Y][i + k][j + l] = blockList.get(position)[Main.Y][k][l];
							YCbCrImage[Main.Cb][i + k][j + l] = blockList.get(position)[Main.Cb][k][l];
							YCbCrImage[Main.Cr][i + k][j + l] = blockList.get(position)[Main.Cr][k][l];
						}
					}
					position++;
				}
			}
		}

		
		return YCbCrImage;
	}




	
	

}
