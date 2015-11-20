package gti310.tp4;

public class YCbCrConverter {
	
	private int input[][][];
	private int output[][][];
	private int qualityFactor;
	
	//First constructor for program number 1
	public void YCbCrConverter(int quality, String inputFile, String outputFile) {
		input = PPMReaderWriter.readPPMFile(inputFile);
		this.qualityFactor = quality;
		//input.encode();
		//PPMReaderWriter.writePPMFile(outputFile, output);
	}
	
	//Second constructor for program number 2
	public void YCbCrConverter(String inputFile, String outputFile) {
		input = PPMReaderWriter.readPPMFile(inputFile);
		//input.decode();
		//PPMReaderWriter.writePPMFile(outputFile, output);
	}
	
	public int[][][] encode() {
		int[][][] yCbCrArray = null;
		
		
		
		
		return yCbCrArray;
		
	}

}
