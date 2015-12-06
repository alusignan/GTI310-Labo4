package gti310.tp4;

import java.util.ArrayList;

public class Decoder {
	
	private int[] inputSZL;
	private ArrayList<int[][]> iDC_Y = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cb = new ArrayList<int[][]>();
	private ArrayList<int[][]> iDC_Cr = new ArrayList<int[][]>();
	private ArrayList<int[][][]> iZigZagList = new ArrayList<int[][][]>();
	private ArrayList<float[][][]> dequantizeList = new ArrayList<float[][][]>();
	
	private int[][][] matrix;
	private float[][][] dequantizeMatrix;
	private float[][][] iDCTMatrix;
	private ArrayList<float[][][]> iDCTMatrixList = new ArrayList<float[][][]>();
	private float[][][] YCbCrImage;
	private int[][][] RGBImage;
 
	
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
		
		
//		matrix = ZigZagManager.gazgiz(iDC_Y, colorSpace, Main.Y);
//		matrix = ZigZagManager.gazgiz(iDC_Cb, colorSpace, Main.Cb);
//		matrix = ZigZagManager.gazgiz(iDC_Cr, colorSpace, Main.Cr);
		
		ZigZagManager.gazgiz(iDC_Y, Main.Y);
		ZigZagManager.gazgiz(iDC_Cb, Main.Cb);
		ZigZagManager.gazgiz(iDC_Cr, Main.Cr);
		
		iZigZagList = ZigZagManager.getIZigZagList();
		
			
		for (int i = 0; i < iZigZagList.size(); i++) {
			dequantizeMatrix = QuantizeManager.dequantize(iZigZagList.get(i), qualityFactor);
			iDCTMatrix = DCTManager.reverseDCT(dequantizeMatrix);
			iDCTMatrixList.add(iDCTMatrix);
		}
			
		YCbCrImage = BlockManager.mergeBlocks(iDCTMatrixList, height, width);

		
		RGBImage = ColorManager.decode(YCbCrImage);
		PPMReaderWriter.writePPMFile(outputFile, RGBImage);
	}

}
