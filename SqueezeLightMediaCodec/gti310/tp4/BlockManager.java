package gti310.tp4;

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
	
	public static float[][][] mergeBlocks(float[][] block, int height, int width) {
		/*Trouver comment faire un merge des blocs ensemble...*/
		return null;
	}




	
	

}
