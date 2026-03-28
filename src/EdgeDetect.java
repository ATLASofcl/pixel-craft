import java.awt.image.BufferedImage;

/**
 * Detects edges in an image using the Sobel operator, producing a black-and-white
 * image where bright pixels indicate edges (areas of high contrast).
 *
 * The algorithm first converts each pixel to a grayscale intensity, then applies
 * two 3x3 Sobel kernels (horizontal and vertical) to compute the gradient magnitude
 * at each pixel. The magnitude is used as the brightness of the output pixel.
 *
 * Sobel kernels:
 *   Gx = [-1  0  1]    Gy = [-1 -2 -1]
 *        [-2  0  2]          [ 0  0  0]
 *        [-1  0  1]          [ 1  2  1]
 */
public class EdgeDetect extends Converter {

    /**
     * Processes the image by applying Sobel edge detection.
     * The output is a grayscale image highlighting the edges found in the original.
     *
     * @param image the original input image
     * @return a new BufferedImage with detected edges shown in white on black
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Skip border pixels — set them to black
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    result.setRGB(x, y, new ARGB(255, 0, 0, 0).toInt());
                    continue;
                }

                // Apply Sobel kernels to the 3x3 neighbourhood
                int gx = -getGray(image, x - 1, y - 1) + getGray(image, x + 1, y - 1)
                        - 2 * getGray(image, x - 1, y) + 2 * getGray(image, x + 1, y)
                        - getGray(image, x - 1, y + 1) + getGray(image, x + 1, y + 1);

                int gy = -getGray(image, x - 1, y - 1) - 2 * getGray(image, x, y - 1) - getGray(image, x + 1, y - 1)
                        + getGray(image, x - 1, y + 1) + 2 * getGray(image, x, y + 1) + getGray(image, x + 1, y + 1);

                // Compute gradient magnitude and clamp to 0-255
                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                if (magnitude > 255) {
                    magnitude = 255;
                }

                result.setRGB(x, y, new ARGB(255, magnitude, magnitude, magnitude).toInt());
            }
        }

        return result;
    }

    /**
     * Returns the grayscale intensity of the pixel at (x, y), computed as
     * the average of its red, green, and blue channels.
     *
     * @param image the image to sample
     * @param x     the column index
     * @param y     the row index
     * @return the grayscale intensity (0-255)
     */
    private int getGray(BufferedImage image, int x, int y) {
        ARGB color = new ARGB(image.getRGB(x, y));
        return (color.red + color.green + color.blue) / 3;
    }
}
