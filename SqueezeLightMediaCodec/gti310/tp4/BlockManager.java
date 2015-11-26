package gti310.tp4;

public class BlockManager {
	private int[][][] imageArray;
	int[][][] blockArray = new int[Main.COLOR_SPACE_SIZE][Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	
	//Variable pour garder en mémoire la position à découper dans l'image (où on est rendu).
	private int actualWidth, actualHeight;
	
	/**Constructeur du BlockManager on va utiliser width height comme la position actual à découper**/
	public BlockManager(int[][][] image, int width, int height) {
		this.imageArray = image;
		if ((width%8 == 0) && (height%8 == 0)) {
			this.actualWidth = width;
			this.actualHeight = height;
		}
	}
	
	/**On va appeler cette méthode plusieurs fois lors de la conversion
	 * O(N^2) **/
	//Divide image in single 8x8 blocks
	public void splitImage() {
		for (int i = 0; i < Main.BLOCK_SIZE; i++) {
			for (int j = 0; j < Main.BLOCK_SIZE; j++) {
				blockArray[Main.Y][i][j] = imageArray[Main.Y][i+actualWidth][j+actualHeight];
				blockArray[Main.Cb][i][j] = imageArray[Main.Cb][i+actualWidth][j+actualHeight];
				blockArray[Main.Cr][i][j] = imageArray[Main.Cr][i+actualWidth][j+actualHeight];
			}
		}	
	}
	
	

}
