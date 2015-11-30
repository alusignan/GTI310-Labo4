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
				
				yCbCrImage[Main.Y][i][j] = 16 + ((float)(
	                      65.738 * RGBimage[Main.R][i][j] + 
	                      129.057 * RGBimage[Main.G][i][j] +
	                       25.064 * RGBimage[Main.B][i][j]
	                    ));
				
				//(int)((inputPPM[Main.R][i][j] * 0.299)+(inputPPM[Main.G][i][j] * 0.587)+(inputPPM[Main.B][i][j] * 0.114));
				
				yCbCrImage[Main.Cb][i][j] = 128 + ((float)(
	                     -37.945 * RGBimage[Main.R][i][j] + 
	                     -74.494 * RGBimage[Main.G][i][j] +
	                      112.439   * RGBimage[Main.B][i][j]
	                   ));
				
				//(int)((128-(inputPPM[Main.R][i][j] * -0.168736)+(inputPPM[Main.G][i][j] * -0.331264)+(inputPPM[Main.B][i][j] * 0.5)));
				
				yCbCrImage[Main.Cr][i][j] = 128 + ((float)(
	                     112.439    * RGBimage[Main.R][i][j] + 
	                     -97.154 * RGBimage[Main.G][i][j] +
	                     -18.285 * RGBimage[Main.B][i][j]
	                   ));
				
				//(int)((128+(inputPPM[Main.R][i][j] * 0.5)+(inputPPM[Main.G][i][j] * -0.418688)+(inputPPM[Main.B][i][j] * -0.081312)));
				
			}
		
				//yCbCrImage[Main.Y][i][j] = (float)((RGBimage[Main.R][i][j] * 0.299)+(RGBimage[Main.G][i][j] * 0.587)+(RGBimage[Main.B][i][j] * 0.114));
				//yCbCrImage[Main.Cb][i][j] = (float)(128+((RGBimage[Main.R][i][j] * -0.168736)+(RGBimage[Main.G][i][j] * -0.331264)+(RGBimage[Main.B][i][j] * 0.5)));
				//yCbCrImage[Main.Cr][i][j] = (float)(128+((RGBimage[Main.R][i][j] * 0.5)+(RGBimage[Main.G][i][j] * -0.418688)+(RGBimage[Main.B][i][j] * -0.081312)));
				
//				yCbCrImage[Main.Y][i][j] = (float) ((RGBimage[Main.R][i][j] * 0.299)+ (RGBimage[Main.G][i][j] * 0.587)+(RGBimage[Main.B][i][j] * 0.114));
//				yCbCrImage[Main.Cb][i][j] = (float) (0.492*((RGBimage[Main.B][i][j])-(yCbCrImage[Main.Y][i][j])));
//				yCbCrImage[Main.Cr][i][j] = (float) (0.877*((RGBimage[Main.R][i][j])-(yCbCrImage[Main.Y][i][j])));	
		}
		return yCbCrImage;
	}
	/**Source : https://en.wikipedia.org/wiki/YUV
	 * Fonction pour décoder une image YCbCr en RGB
	 * Complexité O(N^2)**/
	public static int[][][] decode(float[][][] YCbCrImage) {
		int[][][] RGBimage = new int[Main.COLOR_SPACE_SIZE][YCbCrImage[INDEX_NUMBER].length][YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length];
		int R = 0;
		int G = 0;
		int B = 0;
		for (int i = 0; i < YCbCrImage[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
			
			//Trop de vert...
				R = ((int) ( 298.082 * (YCbCrImage[Main.Y][i][j] - 16)   +
                    408.583 * (YCbCrImage[Main.Cr][i][j] - 128)    )) >> 8;
						
				//(int)(YCbCrImage[Main.Y][i][j] + 1.402 * (YCbCrImage[Main.Cr][i][j] - 128));
				
				G = ((int) ( 298.082 * (YCbCrImage[Main.Y][i][j] - 16)   +
                        -100.291 * (YCbCrImage[Main.Cb][i][j] - 128) +
                        -208.120 * (YCbCrImage[Main.Cr][i][j] - 128)    )) >> 8;
						
				//(int)(YCbCrImage[Main.Y][i][j] - 0.34414 * ((YCbCrImage[Main.Cb][i][j] - 128)) - 0.71414 * ((inputPPM[Main.Cr][i][j] - 128)));
				
				B = ((int) ( 298.082 * (YCbCrImage[Main.Y][i][j] - 16)   +
                        516.411 * (YCbCrImage[Main.Cb][i][j] - 128)    )) >> 8;
						
				//(int)(YCbCrImage[Main.Y][i][j] + 1.772 * (YCbCrImage[Main.Cb][i][j] - 128));
				
				/*Trop de rouge ? bleu ? image mauve*/
				//R = (int)(1.140250855*(YCbCrImage[Main.Cr][i][j]+0.877*YCbCrImage[Main.Y][i][j]));
                //G = (int)(-0.3947313749*(YCbCrImage[Main.Cb][i][j]+1.471403709*(YCbCrImage[Main.Cr][i][j]-1.721795786*inputPPM[Main.Y][i][j])));
                //B = (int)(2.032520325*(YCbCrImage[Main.Cb][i][j]+0.492*YCbCrImage[Main.Y][i][j]));
				
				/*Trop de rouge ??*/
//				R = (int)(YCbCrImage[Main.Y][i][j] + 1.402 * (YCbCrImage[Main.Cr][i][j] - 128));
//				G = (int)(YCbCrImage[Main.Cb][i][j] - (0.344 * (YCbCrImage[Main.Cb][i][j] - 128)) - (0.714 * (YCbCrImage[Main.Cr][i][j] - 128)));
//				B = (int)(YCbCrImage[Main.Cr][i][j] + 1.772 * (YCbCrImage[Main.Cb][i][j] - 128));
				
				//Validation inspiré de : http://stackoverflow.com/questions/4041840/function-to-convert-ycbcr-to-rgb
//                R = (R<0 ? 0 :( R > 255 ? 255 : R) );
//                G = (G<0 ? 0 :( G > 255 ? 255 : G) );
//                B = (B<0 ? 0 :( B > 255 ? 255 : B) );
                
				RGBimage[Main.R][i][j] = R;
				RGBimage[Main.G][i][j] = G;	
				RGBimage[Main.B][i][j] = B;
			}
		}
		
		return RGBimage;
		
	}

}
