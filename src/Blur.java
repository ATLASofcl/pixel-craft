import java.awt.image.BufferedImage;

/**
 * Applies a box blur effect to an image by averaging each pixel's colour
 * with its surrounding neighbours in a 5x5 kernel.
 *
 * For each pixel, the algorithm samples all pixels within a 5x5 window
 * centred on that pixel. The average of the red, green, blue, and alpha
 * channels of those neighbours becomes the new pixel colour. Pixels near
 * the edges use only the valid neighbours that fall within the image bounds.
 */
public class Blur extends Converter {

    /** The radius of the blur kernel (a radius of 2 gives a 5x5 window). */
    private static final int RADIUS = 2;

    /**
     * Processes the image by applying a box blur with a 5x5 kernel.
     * Each output pixel is the average colour of all pixels within the
     * kernel window centred on the corresponding input pixel.
     *
     * @param image the original input image
     * @return a new BufferedImage with the blur effect applied
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sumA = 0, sumR = 0, sumG = 0, sumB = 0;
                int count = 0;

                // Sample all pixels in the kernel window
                for (int dy = -RADIUS; dy <= RADIUS; dy++) {
                    for (int dx = -RADIUS; dx <= RADIUS; dx++) {
                        int nx = x + dx;
                        int ny = y + dy;

                        // Only include pixels within image bounds
                        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                            ARGB color = new ARGB(image.getRGB(nx, ny));
                            sumA += color.alpha;
                            sumR += color.red;
                            sumG += color.green;
                            sumB += color.blue;
                            count++;
                        }
                    }
                }

                ARGB blurred = new ARGB(sumA / count, sumR / count, sumG / count, sumB / count);
                result.setRGB(x, y, blurred.toInt());
            }
        }

        return result;
    }
}
