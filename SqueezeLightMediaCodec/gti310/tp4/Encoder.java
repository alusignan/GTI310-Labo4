package gti310.tp4;

public class Encoder {
	
	private int[][][] rgbImage, testColorManager;
	private float[][][] yCbCrImage, blocks;
	
	public Encoder(String inputFile, String outputFile, int quality) {
		
		//On lit le fichier image
		rgbImage = PPMReaderWriter.readPPMFile(inputFile);
		
		//On fait la conversion en YCbCr
		yCbCrImage = ColorManager.encode(rgbImage);
		
		//Ici on teste le décodeur
		testColorManager = ColorManager.decode(yCbCrImage);
		
		//Ici on enregistre le fichier qui sort du décodeur
		PPMReaderWriter.writePPMFile(outputFile, testColorManager);
		
		
		//Longueur et Largeur de l'image pour boucler
		int height = yCbCrImage[0].length;
		int width = yCbCrImage[0][0].length;
		
		
		
		for (int i = 1; i <= height; i++) {
			for (int j = 1; i <= width; i++) {
				
				//Création des blocs
				blocks = BlockManager.splitImage(yCbCrImage, i, j);
				
				//Calculer la DCT sur les blocs
			}
		}
				

		
	}

}
