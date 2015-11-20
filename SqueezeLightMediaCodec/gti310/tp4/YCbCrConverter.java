package gti310.tp4;

public class YCbCrConverter {
	
	private static final int INDEX_NUMBER = 0;
	private int inputPPM[][][];
	private int outputPPM[][][];
	private int inputSZL[];
	private int outputSZL[];
	private int qualityFactor;
	
	

	//First constructor for program number 1
	public YCbCrConverter(int quality, String inputPPMFile, String outputFile) {
		inputPPM = PPMReaderWriter.readPPMFile(inputPPMFile);
		this.qualityFactor = quality;
		//PPMReaderWriter.writePPMFile(outputFile, output);
	}
	
	//Second constructor for program number 2
	public YCbCrConverter(String inputPPMFile, String outputFile) {
		inputSZL = SZLReaderWriter.readSZLFile(inputPPMFile);
		//inputSZL.decode();
		
	}
	
	
	public void encode() {
		double[][][] yCbCrArray = new double[Main.COLOR_SPACE_SIZE][inputPPM[INDEX_NUMBER].length][inputPPM[INDEX_NUMBER].length];
	
		for (int i = 0; i < inputPPM[INDEX_NUMBER].length; i++) {
			for (int j = 0; j < inputPPM[INDEX_NUMBER].length; j++) {
				yCbCrArray[Main.Y][i][j] = (double)((inputPPM[Main.R][i][j] * 0.299)+(inputPPM[Main.G][i][j] * 0.587)+(inputPPM[Main.B][i][j] * 0.114));
				yCbCrArray[Main.Cb][i][j] = (double)((inputPPM[Main.R][i][j] * -0.168736)+(inputPPM[Main.G][i][j] * -0.331264)+(inputPPM[Main.B][i][j] * 0.5));
				yCbCrArray[Main.Cr][i][j] = (double)((inputPPM[Main.R][i][j] * 0.5)+(inputPPM[Main.G][i][j] * -0.418688)+(inputPPM[Main.B][i][j] * -0.081312));
			}	
		}
	}
	
	public double[][][] decode() {
		
		return null;
		
	}

}
