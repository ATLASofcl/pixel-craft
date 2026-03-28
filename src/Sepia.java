import java.awt.image.BufferedImage;

/**
 * Applies a sepia tone effect to an image, giving it a warm, vintage appearance.
 *
 * The sepia transformation uses weighted sums of the original RGB channels:
 *   newRed   = 0.393*R + 0.769*G + 0.189*B
 *   newGreen = 0.349*R + 0.686*G + 0.168*B
 *   newBlue  = 0.272*R + 0.534*G + 0.131*B
 * Values are clamped to the 0-255 range. The alpha channel is preserved.
 */
public class Sepia extends Converter {

    /**
     * Processes the image by applying the sepia tone formula to each pixel.
     *
     * @param image the original input image
     * @return a new BufferedImage with the sepia effect applied
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB color = new ARGB(image.getRGB(x, y));

                int newRed   = clamp((int) (0.393 * color.red + 0.769 * color.green + 0.189 * color.blue));
                int newGreen = clamp((int) (0.349 * color.red + 0.686 * color.green + 0.168 * color.blue));
                int newBlue  = clamp((int) (0.272 * color.red + 0.534 * color.green + 0.131 * color.blue));

                ARGB sepia = new ARGB(color.alpha, newRed, newGreen, newBlue);
                result.setRGB(x, y, sepia.toInt());
            }
        }

        return result;
    }

    /**
     * Clamps a colour value to the valid range of 0-255.
     *
     * @param value the colour value to clamp
     * @return the clamped value, between 0 and 255 inclusive
     */
    private int clamp(int value) {
        if (value > 255) {
            return 255;
        }
        if (value < 0) {
            return 0;
        }
        return value;
    }
}
