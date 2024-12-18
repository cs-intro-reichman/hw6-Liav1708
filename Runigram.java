import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {

		//Color[][] image = read("eyes.ppm");
		//Color[][] image2 = read("escher.ppm");
		//print(image[1][0]);
		//print(flippedHorizontally(image));
		//System.out.println("");
		//print(image);
		//System.out.println("");
		//print(flippedVertically(image));
		//System.out.println("");
		//print(grayScaled(image));
		//print(scaled(image, 100, 150));
		//Color[][] demo = scaled(image, 100, 150);
		//setCanvas(image);
		//morph(image, image2, 3);
		
		
		
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		//Color[][] tinypic = read("tinypic.ppm");
		//print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		//Color[][] image;

		// Tests the horizontal flipping of an image:
		//image = flippedHorizontally(tinypic);
		//System.out.println();
		//print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for(int j= 0;j < numRows;j++){
			for(int i = 0;i < numCols; i++){
				image[j][i] = new Color(in.readInt(), in.readInt(), in.readInt());
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for(int i = 0;i < image.length;i++){
			for(int j = 0;j < image[0].length;j++){
				print(image[i][j]);
			}
			System.out.println("");
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] newImage = new Color[image.length][image[0].length];
		for(int j = 1;j <= newImage[0].length;j++){
			for(int i = 0;i < newImage.length;i++){
				newImage[i][j - 1] = image[i][image[0].length - j];
			}
		}

		return newImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] newImage = new Color[image.length][image[0].length];
		for(int j = 0;j < newImage[0].length;j++){
			for(int i = 1;i <= newImage.length;i++){
				newImage[i - 1][j] = image[image.length - i][j];
			}
		}
		return newImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		int lum = (int) ((0.299 * pixel.getRed()) + (0.587 * pixel.getGreen()) + (0.114 * pixel.getBlue()));
		Color lumColor = new Color(lum, lum, lum);
		return lumColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		Color[][] grayColors = new Color[image.length][image[0].length];
		for(int i = 0;i < image.length;i++){
			for(int j = 0;j < image[0].length;j++){
				grayColors[i][j] = luminance(image[i][j]);
			}
		}
		return grayColors;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] newImage = new Color[height][width];
		int H = image.length;
		int W = image[0].length;
		for (int i = 0;i < height;i++){
			for (int j = 0;j < width;j++){
				newImage[i][j] = image[(int)(i * ((double) H / height))][(int)(j * ((double) W / width))];
			}
		}
		return newImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int newRed = (int) ((c1.getRed() * alpha) + (c2.getRed() * (1 - alpha)));
		int newGreen = (int) ((c1.getGreen() * alpha) + (c2.getGreen() * (1 - alpha)));
		int newBlue = (int) ((c1.getBlue() * alpha) + (c2.getBlue() * (1 - alpha)));
		Color resultColor = new Color(newRed, newGreen, newBlue);
		return resultColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		
		Color[][] blendColors = new Color[image1.length][image1[0].length];
		for(int i = 0;i < image1.length;i++){
			for(int j = 0;j < image1[0].length;j++){
				blendColors[i][j] = blend(image1[i][j] , image2[i][j] , alpha);
			}
		}
		return blendColors;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		target = scaled(target, source[0].length, source.length);
		double N = n;
		
		for(int i = 0;i <= n;i++){
			Color[][] middleColors = blend(source, target, (N - i) / N);
			Runigram.display(middleColors);
			StdDraw.pause(500);

		}		
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

