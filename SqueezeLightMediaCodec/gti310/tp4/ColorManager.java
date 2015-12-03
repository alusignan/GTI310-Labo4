package gti310.tp4;

public class ColorManager {
	
	private static final int INDEX_NUMBER = 0;
	
	
	/**Fonction pour encoder une image RGB en YCbCr
	 * Complexité O(N^2)
	 * **/
	public static float[][][] encode(int[][][] RGBimage) {
		float[][][] yCbCrImage = new float[Main.COLOR_SPACE_SIZE][RGBimage[INDEX_NUMBER].length][RGBimage[INDEX_NUMBER].length];
	
		for (int i = 0; i < RGBimage[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < RGBimage[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
				
				yCbCrImage[Main.Y][i][j] = (float)((RGBimage[Main.R][i][j] * 0.299)+(RGBimage[Main.G][i][j] * 0.587)+(RGBimage[Main.B][i][j] * 0.114));
				yCbCrImage[Main.Cb][i][j] = (float)(128+((RGBimage[Main.R][i][j] * -0.168736)+(RGBimage[Main.G][i][j] * -0.331264)+(RGBimage[Main.B][i][j] * 0.5)));
				yCbCrImage[Main.Cr][i][j] = (float)(128+((RGBimage[Main.R][i][j] * 0.5)+(RGBimage[Main.G][i][j] * -0.418688)+(RGBimage[Main.B][i][j] * -0.081312)));
				
			}	
		}
		return yCbCrImage;
	}
	
	/**Source : https://en.wikipedia.org/wiki/YUV
	 * Fonction pour décoder une image YCbCr en RGB
	 * Complexité O(N^2)**/
	public static int[][][] decode(float[][][] YCbCrImage) {
		int[][][] RGBimage = new int[Main.COLOR_SPACE_SIZE][YCbCrImage[INDEX_NUMBER].length][YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length];
		double R = 0;
		double G = 0;
		double B = 0;
		for (int i = 0; i < YCbCrImage[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
				
				R = ((YCbCrImage[Main.Y][i][j]) + (1.4 * (YCbCrImage[Main.Cr][i][j] - 128)));
				G = ((YCbCrImage[Main.Y][i][j]) + ((YCbCrImage[Main.Cb][i][j]) - 128) * -0.343) + ((YCbCrImage[Main.Cr][i][j] - 128) * -0.711);
				B = ((YCbCrImage[Main.Y][i][j]) + ((YCbCrImage[Main.Cb][i][j]) - 128) * 1.765);

				//Validation inspiré de : http://stackoverflow.com/questions/4041840/function-to-convert-ycbcr-to-rgb
                R = (R<0 ? 0 :( R > 255 ? 255 : R) );
                G = (G<0 ? 0 :( G > 255 ? 255 : G) );
                B = (B<0 ? 0 :( B > 255 ? 255 : B) );
                
				RGBimage[Main.R][i][j] = (int) R;
				RGBimage[Main.G][i][j] = (int) G;	
				RGBimage[Main.B][i][j] = (int) B;
			}
		}
		
		return RGBimage;
		
	}

}
