import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract base class for all image converters in the PixelCraft application.
 * Handles the common operations of reading an input image file, delegating the
 * pixel-level processing to a subclass, and writing the result to an output file.
 *
 * Subclasses must implement the {@link #process(BufferedImage)} method to define
 * their specific image transformation logic.
 */
public abstract class Converter {

    /**
     * Reads the input image, applies the converter-specific transformation,
     * and writes the processed image to the output file in PNG format.
     *
     * @param inputFileName  the path to the input PNG image file
     * @param outputFileName the path where the output PNG image will be saved
     * @throws IOException if the input file cannot be read or the output file cannot be written
     */
    public void convert(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        BufferedImage originalImage = ImageIO.read(inputFile);

        BufferedImage processedImage = process(originalImage);

        File outputFile = new File(outputFileName);
        ImageIO.write(processedImage, "PNG", outputFile);

        System.out.println("Output saved to " + outputFileName);
    }

    /**
     * Applies the converter-specific image transformation. Each subclass implements
     * this method with its own processing algorithm.
     *
     * @param image the original input image
     * @return a new BufferedImage containing the processed result
     */
    protected abstract BufferedImage process(BufferedImage image);
}
