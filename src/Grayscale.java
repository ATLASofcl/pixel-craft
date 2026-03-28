import java.awt.image.BufferedImage;

/**
 * Converts an image to grayscale by averaging the red, green, and blue
 * colour channels of each pixel. The alpha channel is preserved.
 *
 * The grayscale value for each pixel is calculated as:
 *   gray = (red + green + blue) / 3
 * This average is then assigned to all three colour channels.
 */
public class Grayscale extends Converter {

    /**
     * Processes the image by converting every pixel to its grayscale equivalent.
     * Iterates over each pixel, computes the average of its RGB values,
     * and sets all three channels to that average.
     *
     * @param image the original input image
     * @return a new BufferedImage in grayscale
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB color = new ARGB(image.getRGB(x, y));
                int gray = (color.red + color.green + color.blue) / 3;
                ARGB grayColor = new ARGB(color.alpha, gray, gray, gray);
                result.setRGB(x, y, grayColor.toInt());
            }
        }

        return result;
    }
}
