package gti310.tp4;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author François Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int Cb = 1;
	public static final int Cr = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		/**Programme 1 c'est l'encodeur
		 * Configuration:
		 * Programme1
		 * facteur de qualité
		 * .\media-TP4\image.ppm
		 * .\media-TP4\image.szl**/
		
		if (args[0].equals("Programme1")) {
			if ((Integer.parseInt(args[1]) <= 0) || (Integer.parseInt(args[1]) >= 101)) {
				System.out.println("Impossible de procéder avec le facteur de qualité suivant : " +args[1] );
			} 
			else {
				Encoder e = new Encoder(Integer.parseInt(args[1]), args[2], args[3]);
				System.out.println("La compression est terminée");
			}

		}
		
		/**Programme 2 c'est décodeur
		 * Configuration:
		 * Programme2
		 * .\media-TP4\image.szl
		 * .\media-TP4\image.ppm**/
		else if (args[0].equals("Programme2")) {
			Decoder d = new Decoder(args[1], args[2]);
			System.out.println("La décompression est terminée");
		}

		
	}
}
