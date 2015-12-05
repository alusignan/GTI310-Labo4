package gti310.tp4;

import java.util.ArrayList;

public class Encoder {
	
	private int[][][] rgbImage;
	private float[][][] yCbCrImage, blocks;
	private float[][] dctY, dctCb, dctCr;
	private int[][] quantifiedY, quantifiedCb, quantifiedCr;
	private ArrayList<int[][]> zigZagList = new ArrayList<int[][]>();

	
	public Encoder(String inputFile, String outputFile, int quality) {
		
		//On lit le fichier image
		rgbImage = PPMReaderWriter.readPPMFile(inputFile);
		
		//On fait la conversion en YCbCr
		yCbCrImage = ColorManager.encode(rgbImage);

		//Longueur et Largeur de l'image pour boucler
		int height = yCbCrImage[0].length;
		int width = yCbCrImage[0][0].length;
		
		System.out.println("Height : " +height);
		System.out.println("Width : " +width);
		System.out.println("Quality factor : " +quality);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i % 8 == 0) && (j % 8 == 0)) {
					
					//Création des blocs
					blocks = BlockManager.splitImage(yCbCrImage, i, j);
					
					//Calculer la DCT sur les blocs YCbCr
					dctY = DCTManager.forwarDCT(blocks, Main.Y);
					dctCb = DCTManager.forwarDCT(blocks, Main.Cb);
					dctCr = DCTManager.forwarDCT(blocks, Main.Cr);
					
					//Quantification
					quantifiedY = QuantizeManager.quantize(dctY, Main.Y, quality);
					quantifiedCb = QuantizeManager.quantize(dctCb, Main.Cb, quality);
					quantifiedCr = QuantizeManager.quantize(dctCr, Main.Cr, quality);
					
					//Création de tableau pour stocker liste zig zag
					int[] zigZagY = new int[Main.BLOCK_SIZE * Main.BLOCK_SIZE];
					int[] zigZagCb = new int[Main.BLOCK_SIZE * Main.BLOCK_SIZE];
					int[] zigZagCr = new int[Main.BLOCK_SIZE * Main.BLOCK_SIZE];
					
					int[][] zigZagArray = new int[Main.COLOR_SPACE_SIZE][Main.BLOCK_SIZE * Main.BLOCK_SIZE];
					
					zigZagArray[Main.Y] = ZigZagManager.zigzag(quantifiedY);
					zigZagArray[Main.Cb] = ZigZagManager.zigzag(quantifiedCb);
					zigZagArray[Main.Cr] = ZigZagManager.zigzag(quantifiedCr);
					
					zigZagList.add(zigZagArray);
				}
			}
		}
		
		Entropy.loadBitstream(Entropy.getBitstream());
		
		//Effectue opération AC-DC
		for (int layer = 0; layer < Main.COLOR_SPACE_SIZE; layer++) {
			DCManager.encode(zigZagList, layer);
			ACManager.encode(zigZagList, layer);
			System.out.println(layer);
		}
		
		
		//Écrit le fichier SZL
		SZLReaderWriter.writeSZLFile(outputFile, height, width, quality);
	}
}
