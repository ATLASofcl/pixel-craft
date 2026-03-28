/**
 * Represents a pixel's colour in the ARGB (Alpha, Red, Green, Blue) colour model.
 * Provides methods to extract individual colour channels from a packed integer
 * and to pack them back into a single integer.
 */
public class ARGB {
    public int alpha, red, green, blue;

    /**
     * Constructs an ARGB object by extracting colour channels from a packed integer.
     * The integer stores colours in the format: bits 31-24 = alpha, 23-16 = red,
     * 15-8 = green, 7-0 = blue.
     *
     * @param pixel the packed ARGB integer value
     */
    public ARGB(int pixel) {
        // extract different bits from pixel that stores the ARGB values
        this.alpha = (pixel >> 24) & 0xff;
        this.red = (pixel >> 16) & 0xff;
        this.green = (pixel >> 8) & 0xff;
        this.blue = pixel & 0xff;
    }

    /**
     * Constructs an ARGB object from individual colour channel values.
     *
     * @param a the alpha (transparency) value (0-255)
     * @param r the red value (0-255)
     * @param g the green value (0-255)
     * @param b the blue value (0-255)
     */
    public ARGB(int a, int r, int g, int b) {
        this.alpha = a;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    /**
     * Packs the ARGB colour channels back into a single integer.
     *
     * @return the packed ARGB integer value
     */
    public int toInt() {
        // encode the ARGB values into a single integer
        return (this.alpha << 24) | (this.red << 16) | (this.green << 8) | blue;
    }
}
