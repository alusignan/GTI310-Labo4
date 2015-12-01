package gti310.tp4;

public class QuantizeManager {
	
	//Matrice de l'énoncé pour quantification Y
	public static final int[][] QY = {
			{16, 40, 40, 40, 40, 40, 51, 61},
			{40, 40, 40, 40, 40, 58, 60, 55},
			{40, 40, 40, 40, 40, 57, 69, 56},
			{40, 40, 40, 40, 51, 87, 80, 62},
			{40, 40, 40, 56, 68, 109, 103, 77},
			{40, 40, 55, 64, 81, 104, 113, 92},
			{49, 64, 78, 87, 103, 121, 120, 101},
			{72, 92, 95, 98, 112, 100, 103, 95}
	};
	
	//Matrice de l'énoncé pour Quantification Cb Cr
	public static final int[][] QCbCr = {
			{17, 40, 40, 95, 95, 95, 95, 95},
			{40, 40, 40, 95, 95, 95, 95, 95},
			{40, 40, 40, 95, 95, 95, 95, 95},
			{40, 40, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
	};
	
	/**Complexité en O(N^2)**/
	public static int[][] quantize(float[][] matrix, int layer, int quality) {
		int[][] F = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		
		//Pour la couche Y
		if (layer == 0) {
			for (int u = 0; u < Main.BLOCK_SIZE; u++) {
				for (int v = 0; v < Main.BLOCK_SIZE; v++) {
					//Facteur entre 1 et 50
					if ((quality >= 1) && (quality <= 50 )) {
						float a = 50 / quality;
						F[u][v] = Math.round((matrix[u][v]) / (a  * QY[u][v]));
					}
					//Facteur entre 51 et 99
					else if ((quality >= 51) && (quality <= 99)) {
						float a = ((200 - 2 * quality) / 100);
						F[u][v] = Math.round((matrix[u][v]) / (a  * QY[u][v]));
					}
					//Facteur 100 loseless JPEG
					else if (quality == 100) {
						F[u][v] = QY[u][v];
					}
				}
			}
		}
		//Pour les couches Cb et Cr
		else {
			for (int u = 0; u < Main.BLOCK_SIZE; u++) {
				for (int v = 0; v < Main.BLOCK_SIZE; v++) {
					//Facteur entre 1 et 50
					if ((quality >= 1) && (quality <= 50 )) {
						float a = 50 / quality;
						F[u][v] = Math.round((matrix[u][v]) / (a  * QCbCr[u][v]));
					}
					//Facteur entre 51 et 99
					else if ((quality >= 51) && (quality <= 99)) {
						float a = ((200 - 2 * quality) / 100);
						F[u][v] = Math.round((matrix[u][v]) / (a  * QCbCr[u][v]));
					}
					//Facteur de 100 loseless JPEG
					else if (quality == 100) {
						F[u][v] = QCbCr[u][v];
					}
				}
			}
		}
		//Retourne la nouvelle matrice 8x8 quantifié
		return F;
	}
	
	
}
