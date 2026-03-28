import java.awt.image.BufferedImage;

/**
 * Applies a pixelation (mosaic) effect to an image by dividing it into
 * square blocks and filling each block with the average colour of its pixels.
 *
 * The block size determines the level of pixelation. A larger block size
 * produces a more coarsely pixelated result. The default block size is 10 pixels.
 */
public class Pixelate extends Converter {

    /** The side length of each pixelation block in pixels. */
    private static final int BLOCK_SIZE = 10;

    /**
     * Processes the image by dividing it into blocks and replacing each block
     * with its average colour.
     *
     * @param image the original input image
     * @return a new BufferedImage with the pixelation effect applied
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Process each block
        for (int blockY = 0; blockY < height; blockY += BLOCK_SIZE) {
            for (int blockX = 0; blockX < width; blockX += BLOCK_SIZE) {
                // Determine the actual block boundaries (may be smaller at edges)
                int blockW = Math.min(BLOCK_SIZE, width - blockX);
                int blockH = Math.min(BLOCK_SIZE, height - blockY);

                // Compute the average colour for this block
                int sumA = 0, sumR = 0, sumG = 0, sumB = 0;
                int count = blockW * blockH;

                for (int dy = 0; dy < blockH; dy++) {
                    for (int dx = 0; dx < blockW; dx++) {
                        ARGB color = new ARGB(image.getRGB(blockX + dx, blockY + dy));
                        sumA += color.alpha;
                        sumR += color.red;
                        sumG += color.green;
                        sumB += color.blue;
                    }
                }

                ARGB avg = new ARGB(sumA / count, sumR / count, sumG / count, sumB / count);
                int avgPixel = avg.toInt();

                // Fill the entire block with the average colour
                for (int dy = 0; dy < blockH; dy++) {
                    for (int dx = 0; dx < blockW; dx++) {
                        result.setRGB(blockX + dx, blockY + dy, avgPixel);
                    }
                }
            }
        }

        return result;
    }
}
