package gti310.tp4;

public class ColorManager {
	
	private static final int INDEX_NUMBER = 0;
	private int inputPPM[][][];
	private float[][][] testConverter;
	private int[][][] outputPPM;
	private int inputSZL[];
	private int outputSZL[];
	private int qualityFactor;
	
	

	//First constructor for program number 1
	public ColorManager(int quality, String inputPPMFile, String outputFile) {
		inputPPM = PPMReaderWriter.readPPMFile(inputPPMFile);
		this.qualityFactor = quality;
		//PPMReaderWriter.writePPMFile(outputFile, output);
	}
	
	/**Il va falloir ajuster pour que ce soit en fonction d'un fichier SZL plus tard**/
	//Second constructor for program number 2
	public ColorManager(String inputPPMFile, String outputFile) {
		inputPPM = PPMReaderWriter.readPPMFile(inputPPMFile);
		testConverter = encode(inputPPM);
		outputPPM = decode(testConverter);
		PPMReaderWriter.writePPMFile(outputFile, outputPPM);
		//inputSZL = SZLReaderWriter.readSZLFile(inputPPMFile);
	}
	
	/**Fonction pour encoder une image RGB en YCbCr
	 * Complexit� O(N^2)
	 * **/
	public float[][][] encode(int[][][] RGBimage) {
		float[][][] yCbCrImage = new float[Main.COLOR_SPACE_SIZE][RGBimage[INDEX_NUMBER].length][RGBimage[INDEX_NUMBER].length];
	
		for (int i = 0; i < RGBimage[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < RGBimage[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
				yCbCrImage[Main.Y][i][j] = (float)((RGBimage[Main.R][i][j] * 0.299)+(RGBimage[Main.G][i][j] * 0.587)+(RGBimage[Main.B][i][j] * 0.114));
				yCbCrImage[Main.Cb][i][j] = (float)(128+((RGBimage[Main.R][i][j] * -0.168736)+(RGBimage[Main.G][i][j] * -0.331264)+(RGBimage[Main.B][i][j] * 0.5)));
				yCbCrImage[Main.Cr][i][j] = (float)(128+((RGBimage[Main.R][i][j] * 0.5)+(RGBimage[Main.G][i][j] * -0.418688)+(RGBimage[Main.B][i][j] * -0.081312)));
				
//				yCbCrImage[Main.Y][i][j] = (float) ((image[Main.R][i][j] * 0.299)+ (image[Main.G][i][j] * 0.587)+(image[Main.B][i][j] * 0.114));
//				yCbCrImage[Main.Cb][i][j] = (float) (0.492*((image[Main.B][i][j])-(yCbCrImage[Main.Y][i][j])));
//				yCbCrImage[Main.Cr][i][j] = (float) (0.877*((image[Main.R][i][j])-(yCbCrImage[Main.Y][i][j])));
			}	
		}
		return yCbCrImage;
	}
	/**Source : https://en.wikipedia.org/wiki/YUV
	 * Fonction pour d�coder une image YCbCr en RGB
	 * Complexit� O(N^2)**/
	public int[][][] decode(float[][][] YCbCrImage) {
		int[][][] RGBimage = new int[Main.COLOR_SPACE_SIZE][YCbCrImage[INDEX_NUMBER].length][YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length];
		int R = 0;
		int G = 0;
		int B = 0;
		for (int i = 0; i < YCbCrImage[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < YCbCrImage[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
			
				/*Trop de rouge ? bleu ? image mauve*/
				R = (int)(1.140250855*(inputPPM[Main.Cr][i][j]+0.877*inputPPM[Main.Y][i][j]));
                G = (int)(-0.3947313749*(inputPPM[Main.Cb][i][j]+1.471403709*(inputPPM[Main.Cr][i][j]-1.721795786*inputPPM[Main.Y][i][j])));
                B = (int)(2.032520325*(inputPPM[Main.Cb][i][j]+0.492*inputPPM[Main.Y][i][j]));
				
				/*Trop de rouge ??*/
//				R = (int)(YCbCrImage[Main.Y][i][j] + 1.402 * (YCbCrImage[Main.Cr][i][j] - 128));
//				G = (int)(YCbCrImage[Main.Cb][i][j] - (0.344 * (YCbCrImage[Main.Cb][i][j] - 128)) - (0.714 * (YCbCrImage[Main.Cr][i][j] - 128)));
//				B = (int)(YCbCrImage[Main.Cr][i][j] + 1.772 * (YCbCrImage[Main.Cb][i][j] - 128));
				
				//Validation inspir� de : http://stackoverflow.com/questions/4041840/function-to-convert-ycbcr-to-rgb
                R = (R<0 ? 0 :( R > 255 ? 255 : R) );
                G = (G<0 ? 0 :( G > 255 ? 255 : G) );
                B = (B<0 ? 0 :( B > 255 ? 255 : B) );
                
				RGBimage[Main.R][i][j] = R;
				RGBimage[Main.G][i][j] = G;	
				RGBimage[Main.B][i][j] = B;
			}
		}
		
		return RGBimage;
		
	}

}
