import java.awt.image.BufferedImage;

/**
 * Flips an image horizontally (mirrors it along the vertical axis).
 * Each pixel at column x is moved to column (width - 1 - x).
 *
 * This converter uses ONLY recursion (no loops) to iterate over every pixel.
 * Two-level recursion is used: one method recurses over rows, and another
 * recurses over columns within each row, keeping the stack depth at O(width + height).
 */
public class FlipHorizontal extends Converter {

    /**
     * Processes the image by flipping it horizontally using recursion.
     *
     * @param image the original input image
     * @return a new BufferedImage that is the horizontal mirror of the original
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        flipRow(image, result, 0, width, height);
        return result;
    }

    /**
     * Recursively processes each row of the image starting from row y.
     * For each row, delegates to {@link #flipColumn} to handle individual pixels.
     * Base case: y reaches the image height.
     *
     * @param source the original image
     * @param result the output image
     * @param y      the current row index
     * @param width  the image width
     * @param height the image height
     */
    private void flipRow(BufferedImage source, BufferedImage result,
                         int y, int width, int height) {
        if (y >= height) {
            return;
        }
        flipColumn(source, result, 0, y, width);
        flipRow(source, result, y + 1, width, height);
    }

    /**
     * Recursively mirrors pixels in a single row, advancing column by column.
     * Each pixel at column x is placed at column (width - 1 - x).
     * Base case: x reaches the image width.
     *
     * @param source the original image
     * @param result the output image
     * @param x      the current column index
     * @param y      the current row index
     * @param width  the image width
     */
    private void flipColumn(BufferedImage source, BufferedImage result,
                            int x, int y, int width) {
        if (x >= width) {
            return;
        }
        int pixel = source.getRGB(x, y);
        result.setRGB(width - 1 - x, y, pixel);
        flipColumn(source, result, x + 1, y, width);
    }
}
