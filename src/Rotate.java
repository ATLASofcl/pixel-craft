import java.awt.image.BufferedImage;

/**
 * Rotates an image 90 degrees clockwise.
 *
 * The transformation maps each pixel at position (x, y) in the original image
 * to position (height - 1 - y, x) in the new image. The output image has its
 * width and height swapped compared to the original.
 */
public class Rotate extends Converter {

    /**
     * Processes the image by rotating it 90 degrees clockwise.
     * A pixel at column x, row y in the original is placed at
     * column (height - 1 - y), row x in the result.
     *
     * @param image the original input image
     * @return a new BufferedImage rotated 90 degrees clockwise
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        // Swapped dimensions: new width = old height, new height = old width
        BufferedImage result = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                result.setRGB(height - 1 - y, x, pixel);
            }
        }

        return result;
    }
}
