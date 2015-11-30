package gti310.tp4;

public class Encoder {
	
	private int[][][] rgbImage, testColorManager;
	private float[][][] yCbCrImage, blocks;
	
	public Encoder(String inputFile, String outputFile, int quality) {
		rgbImage = PPMReaderWriter.readPPMFile(inputFile);
		yCbCrImage = ColorManager.encode(rgbImage);
		testColorManager = ColorManager.decode(yCbCrImage);
		PPMReaderWriter.writePPMFile(outputFile, testColorManager);
		
		int height = yCbCrImage[0].length;
		int width = yCbCrImage[0][0].length;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; i < width; i++) {
				blocks = BlockManager.splitImage(yCbCrImage, i, j);
				
				//Calculer la DCT sur les blocs
			}
		}
				

		
	}

}
