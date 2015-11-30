package gti310.tp4;

public class Encoder {
	
	private int[][][] rgbImage, testColorManager;
	private float[][][] yCbCrImage, blocks;
	
	public Encoder(String inputFile, String outputFile, int quality) {
		
		//On lit le fichier
		rgbImage = PPMReaderWriter.readPPMFile(inputFile);
		
		//On le convertit en YCbCr
		yCbCrImage = ColorManager.encode(rgbImage);
		
		//Ici on teste le d�codeur
		testColorManager = ColorManager.decode(yCbCrImage);
		
		//Ici on enregistre le fichier qui sort du d�codeur
		PPMReaderWriter.writePPMFile(outputFile, testColorManager);
		
		
		//Longueur et Largeur de l'image pour boucler
		int height = yCbCrImage[0].length;
		int width = yCbCrImage[0][0].length;
		
		
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; i < width; i++) {
				
				//Cr�ation des blocs
				blocks = BlockManager.splitImage(yCbCrImage, i, j);
				
				//Calculer la DCT sur les blocs
			}
		}
				

		
	}

}
