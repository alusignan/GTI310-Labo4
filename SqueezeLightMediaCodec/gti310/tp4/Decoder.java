package gti310.tp4;

import java.util.ArrayList;

public class Decoder {
	
	private int[] inputSZL;
	private ArrayList<int[][]> iDC_Y = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cb = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cr = new ArrayList<int[][]>();
	private ArrayList<int[][][]> iZigZagList = new ArrayList<int[][][]>();
	private float[][][] dequantizeMatrix;
	private float[][][] iDCTMatrix;
	private ArrayList<float[][][]> iDCTMatrixList = new ArrayList<float[][][]>();
	private float[][][] YCbCrImage;
	private int[][][] RGBImage;
 
	
	/**Classe pour décoder un fichier SZL vers PPM.
	 * Constitue le programme 2 de l'applicaton
	 * @param intputFile
	 * @param outputFile**/
	public Decoder (String inputFile, String outputFile) {
	
		
		//On lit le fichier SZL
		inputSZL = SZLReaderWriter.readSZLFile(inputFile);
		
		//Valeur longueur, hauteur, espace de couleur, facteur de qualité tel que décrit dans l'énoncé
		int height = inputSZL[0];
		int width = inputSZL[1];
		int colorSpace = inputSZL[2];
		int qualityFactor = inputSZL[3];
		
		System.out.println("Height : " +height);
		System.out.println("Width : " +width);
		System.out.println("Quality factor : " +qualityFactor);

		//On calcul le AC DC inverse sur chacune des composantes de l'applicaiton
		iDC_Y = DCManager.decode(height, width, colorSpace, Main.Y);
		ACManager.decode(iDC_Y, Main.Y);
		iDC_Cb = DCManager.decode(height, width, colorSpace, Main.Cb);
		ACManager.decode(iDC_Cb, Main.Cb);
		iDC_Cr = DCManager.decode(height, width, colorSpace, Main.Cr);
		ACManager.decode(iDC_Cr, Main.Cr);
		
		
		//On refait des blocs 8x8 avec le zigZag sur chacune des composantes
		ZigZagManager.gazgiz(iDC_Y, Main.Y);
		ZigZagManager.gazgiz(iDC_Cb, Main.Cb);
		ZigZagManager.gazgiz(iDC_Cr, Main.Cr);
		
		//Ajoute à la liste de bloc 8x8
		iZigZagList = ZigZagManager.getIZigZagList();
		
		
		/**Complexité O(N^6)**/
		for (int i = 0; i < iZigZagList.size(); i++) {
			//On déquantifie les blocs 8x8
			dequantizeMatrix = QuantizeManager.dequantize(iZigZagList.get(i), qualityFactor);
			//On applique la DCT inverse sur les blocs 8x8
			iDCTMatrix = DCTManager.reverseDCT(dequantizeMatrix);
			//On ajoute à une liste avec les autres blocs 8x8
			iDCTMatrixList.add(iDCTMatrix);
		}
		
		//On merge les blocs ensembles pour recréer une image YCBCR de la bonne longueur x largeur
		YCbCrImage = BlockManager.mergeBlocks(iDCTMatrixList, height, width);

		//On convertie en image RGB
		RGBImage = ColorManager.decode(YCbCrImage);
		
		//On écrit le fichier PPM décodé
		PPMReaderWriter.writePPMFile(outputFile, RGBImage);
	}

}
