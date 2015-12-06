package gti310.tp4;


/**Source : http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java**/
public class DCTManager {
	private static final int N = 8;
	private static float[] C = new float[N];

	/**Complexité O(N^4)
	 * Calcul la DCT selon la formule.. sur les blocks 8x8
	 * @return F**/
	public static float[][] forwarDCT(float[][][] block, int layer) {
		initializeCoefficients();
		float [][] F = new float[N][N];
		for (int u = 0; u < N; u++) {
			for (int v = 0; v < N; v++) {
				float sum = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						sum += Math.cos(((2 * i + 1) * u * Math.PI) / 16) * Math.cos(((2 * j + 1) * v * Math.PI) / 16) * (block[layer][i][j]);
					}
				}
				sum *= ((C[u] * C[v]) / 4);
				F[u][v] = sum;
			}
		}
		return F;
	}
	
	/**Complexité O(N^5)
	 * Effectue le calcul de la DCT inverse sur les blocks quantifié**/
	public static float[][][] reverseDCT(float[][][] block) {
		float[][][] F = new float[Main.COLOR_SPACE_SIZE][N][N];
		initializeCoefficients();
		for (int layer = 0; layer < Main.COLOR_SPACE_SIZE; layer++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					float sum = 0;
					for (int u = 0; u < N; u++) {
						for (int v = 0; v < N; v++) {
							sum += ((C[u] * C[v]) / 4) * Math.cos(((2 * i + 1) * u * Math.PI) / 16) * Math.cos(((2 * j + 1) * v * Math.PI) / 16) * (block[layer][u][v]);
						}
					}
					F[layer][i][j] = Math.round(sum);
				}
			}
		}
		return F;
	}

	/**Complexité O(N)
	 * Rempli le tableau C**/
	public static void initializeCoefficients() {
		for (int i = 1; i < N; i++) {
			C[i] = 1;
		}
		C[0] = (float) (1/Math.sqrt(2));
	} 
}
