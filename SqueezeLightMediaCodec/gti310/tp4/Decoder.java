package gti310.tp4;

import java.util.ArrayList;

public class Decoder {
	
	private int[] inputSZL;
	private ArrayList<int[][]> iDC_Y = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cb = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cr = new ArrayList<int[][]>();
	private ArrayList<int[][]> zigZagList = new ArrayList<int[][]>();
	private int[][][] matrix;

	
	public Decoder (String inputFile, String outputFile) {
	
		inputSZL = SZLReaderWriter.readSZLFile(inputFile);
		
		int height = inputSZL[0];
		int width = inputSZL[1];
		int colorSpace = inputSZL[2];
		int qualityFactor = inputSZL[3];
		
		System.out.println("Height : " +height);
		System.out.println("Width : " +width);
		System.out.println("Quality factor : " +qualityFactor);

		iDC_Y = DCManager.decode(height, width, colorSpace, Main.Y);
		ACManager.decode(iDC_Y, Main.Y);
		iDC_Cb = DCManager.decode(height, width, colorSpace, Main.Cb);
		ACManager.decode(iDC_Cb, Main.Cb);
		iDC_Cr = DCManager.decode(height, width, colorSpace, Main.Cr);
		ACManager.decode(iDC_Cr, Main.Cr);
		
		matrix = ZigZagManager.gazgiz(iDC_Y, colorSpace);
		
	}

}
