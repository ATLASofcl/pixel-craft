# PixelCraft — Project Report

**Name:** Arshia Azarbad — **Student Number:** 222474530
**Name:** Gleb Gulid — **Student Number:** 222193965

---

## Implemented Converters

### 1. Grayscale

Converts a colour image to grayscale. For each pixel, the red, green, and blue channel values are averaged to produce a single intensity value: `gray = (red + green + blue) / 3`. This average is then assigned to all three colour channels, while the alpha channel is preserved unchanged. The result is a monochrome version of the original image.

### 2. Rotate

Rotates the image 90 degrees clockwise. The output image has its width and height swapped compared to the original. Each pixel at position (x, y) in the source image is placed at position (height - 1 - y, x) in the result. This mapping shifts every column of the original into a row of the new image, producing a quarter-turn rotation.

### 3. Blur

Applies a box blur effect using a 5×5 averaging kernel (radius of 2). For each pixel, the algorithm samples all pixels within a 5×5 window centred on that pixel, computes the average of their red, green, blue, and alpha values, and assigns that average as the new pixel colour. Pixels near the image edges use a smaller window, averaging only the neighbours that fall within bounds. This softens sharp details and reduces noise.

### 4. Invert

Produces a photographic negative by subtracting each RGB channel value from 255: `newValue = 255 - oldValue`. The alpha channel is preserved. This converter is implemented using **only recursion** with no loops. A two-level recursive structure is used: one method recurses over rows, and for each row a second method recurses over columns. This keeps the maximum stack depth at O(width + height) rather than O(width × height), preventing stack overflow on large images.

### 5. FlipHorizontal

Mirrors the image along the vertical axis by placing each pixel at column x into column (width - 1 - x) in the output. This converter is also implemented using **only recursion** with no loops, using the same two-level recursive approach as Invert: the outer method recurses over rows and the inner method recurses over columns within each row.

### 6. Sepia

Applies a warm, vintage sepia tone using a standard weighted matrix transformation of the RGB channels:

- newRed = 0.393 × R + 0.769 × G + 0.189 × B
- newGreen = 0.349 × R + 0.686 × G + 0.168 × B
- newBlue = 0.272 × R + 0.534 × G + 0.131 × B

Each resulting value is clamped to the 0–255 range to prevent overflow. The alpha channel is preserved. The specific weights emphasize red and green tones to produce the characteristic brownish tint of aged photographs.

### 7. Pixelate

Creates a mosaic effect by dividing the image into 10×10 pixel blocks. For each block, the average colour of all pixels within the block is computed, and every pixel in that block is then set to that average colour. Blocks at the image edges may be smaller than 10×10 if the dimensions are not evenly divisible. The result is a coarsely quantized version of the image that obscures fine detail while preserving overall colour and composition.

### 8. EdgeDetect

Detects edges using the Sobel operator. The image is first interpreted as grayscale intensities (averaging RGB channels). Two 3×3 convolution kernels are applied at each pixel — one detecting horizontal gradients (Gx) and one detecting vertical gradients (Gy). The gradient magnitude is computed as `sqrt(Gx² + Gy²)` and clamped to 0–255. The output is a grayscale image where bright pixels indicate strong edges (areas of high contrast) and dark pixels indicate uniform regions. Border pixels are set to black since the 3×3 kernel cannot be fully applied there.

---

## Self-Assessment

| Standard | Points | Achieved? | Justification                                                                                                                                                                                                                                                                                                                                                              |
| -------- | ------ | --------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| S1.1     | 3      | Yes       | All three required converters are implemented and functional: Grayscale, Rotate (90° clockwise), and Blur (5×5 box blur).                                                                                                                                                                                                                                                  |
| S1.2     | 1      | Yes       | Six or more converters are implemented. The project contains 8 converters in total: Grayscale, Rotate, Blur, Invert, FlipHorizontal, Sepia, Pixelate, and EdgeDetect.                                                                                                                                                                                                      |
| S1.3     | 1      | Yes       | Eight converters are implemented in total, each with a significantly different algorithm (colour transformation, geometric transformation, spatial filtering, edge detection, and mosaic effect).                                                                                                                                                                          |
| S2.1     | 1      | Yes       | The Invert converter is implemented using only recursion with no loops whatsoever. A two-level recursive structure processes rows and columns.                                                                                                                                                                                                                             |
| S2.2     | 1      | Yes       | The FlipHorizontal converter is a second converter implemented using only recursion with no loops. It uses the same two-level recursive strategy but with a different transformation (horizontal mirroring vs. colour inversion).                                                                                                                                          |
| S3.1     | 1      | Yes       | All converters inherit from the abstract `Converter` base class, demonstrating proper use of inheritance.                                                                                                                                                                                                                                                                  |
| S3.2     | 1      | Yes       | The `Converter` base class handles all shared logic (reading the input file, writing the output file, printing the status message). Subclasses only implement the `process()` method with their specific transformation. There is no duplicated file I/O code and no conditional branching to select converter behaviour — Java reflection in the main class handles that. |
| S4.1     | 1      | Yes       | Every class and public/protected method has Javadoc comments explaining its purpose, algorithm, and parameters.                                                                                                                                                                                                                                                            |
| S4.2     | 1      | Yes       | Each converter is described above with a clear explanation of its algorithm and implementation approach.                                                                                                                                                                                                                                                                   |
| S4.3     | 1      | Yes       | This self-assessment section.                                                                                                                                                                                                                                                                                                                                              |

**Total: 12 / 12**
