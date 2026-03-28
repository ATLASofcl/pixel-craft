import java.awt.image.BufferedImage;

/**
 * Inverts the colours of an image by subtracting each RGB channel value from 255.
 * The alpha channel is preserved. This produces a photographic negative effect.
 *
 * This converter uses ONLY recursion (no loops) to iterate over every pixel.
 * Two-level recursion is used: one method recurses over rows, and another
 * recurses over columns within each row, keeping the stack depth at O(width + height).
 */
public class Invert extends Converter {

    /**
     * Processes the image by inverting all pixel colours using recursion.
     *
     * @param image the original input image
     * @return a new BufferedImage with inverted colours
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        invertRow(image, result, 0, width, height);
        return result;
    }

    /**
     * Recursively processes each row of the image starting from row y.
     * For each row, delegates to {@link #invertColumn} to handle individual pixels.
     * Base case: y reaches the image height.
     *
     * @param source the original image
     * @param result the output image
     * @param y      the current row index
     * @param width  the image width
     * @param height the image height
     */
    private void invertRow(BufferedImage source, BufferedImage result,
                           int y, int width, int height) {
        if (y >= height) {
            return;
        }
        invertColumn(source, result, 0, y, width);
        invertRow(source, result, y + 1, width, height);
    }

    /**
     * Recursively inverts pixels in a single row, advancing column by column.
     * Base case: x reaches the image width.
     *
     * @param source the original image
     * @param result the output image
     * @param x      the current column index
     * @param y      the current row index
     * @param width  the image width
     */
    private void invertColumn(BufferedImage source, BufferedImage result,
                              int x, int y, int width) {
        if (x >= width) {
            return;
        }
        ARGB color = new ARGB(source.getRGB(x, y));
        ARGB inverted = new ARGB(color.alpha, 255 - color.red, 255 - color.green, 255 - color.blue);
        result.setRGB(x, y, inverted.toInt());
        invertColumn(source, result, x + 1, y, width);
    }
}
