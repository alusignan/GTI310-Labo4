package gti310.tp4;

public class YCbCrConverter {
	
	private static final int INDEX_NUMBER = 0;
	private int inputPPM[][][];
	private int[][][] outputPPM;
	private int inputSZL[];
	private int outputSZL[];
	private int qualityFactor;
	
	

	//First constructor for program number 1
	public YCbCrConverter(int quality, String inputPPMFile, String outputFile) {
		inputPPM = PPMReaderWriter.readPPMFile(inputPPMFile);
		this.qualityFactor = quality;
		//PPMReaderWriter.writePPMFile(outputFile, output);
	}
	
	/**Il va falloir ajuster pour que ce soit en fonction d'un fichier SZL plus tard**/
	//Second constructor for program number 2
	public YCbCrConverter(String inputPPMFile, String outputFile) {
		inputPPM = PPMReaderWriter.readPPMFile(inputPPMFile);
		encode();
		outputPPM = decode();
		PPMReaderWriter.writePPMFile(outputFile, outputPPM);
		//inputSZL = SZLReaderWriter.readSZLFile(inputPPMFile);
	}
	
	
	public void encode() {
		double[][][] yCbCrImage = new double[Main.COLOR_SPACE_SIZE][inputPPM[INDEX_NUMBER].length][inputPPM[INDEX_NUMBER].length];
	
		for (int i = 0; i < inputPPM[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < inputPPM[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
				yCbCrImage[Main.Y][i][j] = (int)(16+(inputPPM[Main.R][i][j] * 0.299)+(inputPPM[Main.G][i][j] * 0.587)+(inputPPM[Main.B][i][j] * 0.114));
				yCbCrImage[Main.Cb][i][j] = (int)(128+((inputPPM[Main.R][i][j] * -0.168736)+(inputPPM[Main.G][i][j] * -0.331264)+(inputPPM[Main.B][i][j] * 0.5)));
				yCbCrImage[Main.Cr][i][j] = (int)(128+((inputPPM[Main.R][i][j] * 0.5)+(inputPPM[Main.G][i][j] * -0.418688)+(inputPPM[Main.B][i][j] * -0.081312)));
				
			}	
		}
	}
	/**Source : https://en.wikipedia.org/wiki/YUV**/
	public int[][][] decode() {
		int[][][] RGBimage = new int[Main.COLOR_SPACE_SIZE][inputPPM[INDEX_NUMBER].length][inputPPM[INDEX_NUMBER].length];
		int R = 0;
		int G = 0;
		int B = 0;
		for (int i = 0; i < inputPPM[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < inputPPM[INDEX_NUMBER][INDEX_NUMBER].length; j++) {
			
				/*Trop de rouge ? bleu ? image mauve*/
//				R = (int)(1.140250855*(inputPPM[Main.Cr][i][j]+0.877*inputPPM[Main.Y][i][j]));
//              G = (int)(-0.3947313749*(inputPPM[Main.Cb][i][j]+1.471403709*(inputPPM[Main.Cr][i][j]-1.721795786*inputPPM[Main.Y][i][j])));
//              B = (int)(2.032520325*(inputPPM[Main.Cb][i][j]+0.492*inputPPM[Main.Y][i][j]));
				
				/*Trop de vert ??*/
				R = (int)(inputPPM[Main.Y][i][j] + 1.402 * (inputPPM[Main.Cr][i][j] - 128));
				G = (int)(inputPPM[Main.Y][i][j] - 0.344 * (inputPPM[Main.Cb][i][j] - 128) - 0.714 * (inputPPM[Main.Cr][i][j] - 128));
				B = (int)(inputPPM[Main.Y][i][j] + 1.772 * (inputPPM[Main.Cb][i][j] - 128));
				
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
