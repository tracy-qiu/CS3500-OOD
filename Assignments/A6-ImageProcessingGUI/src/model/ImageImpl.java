package model;

import java.util.Objects;

/**
 * class that represents all the methods used to retrieve the state of the image.
 */
public class ImageImpl implements IImage {
  private final int width;
  private final int height;
  private final int maxValue;
  private Pixel[][] pixelArray;
  private int[][] histogram = new int[4][256];

  /**
   * Contructor that represents the Image implementation.
   *
   * @param width      width of the image.
   * @param height     height of the image.
   * @param maxValue   maxValue of color for pixels in the image.
   * @param pixelArray total amount of pixels that represent the image.
   * @throws IllegalArgumentException when width and height are negative, when maxValue is negative,
   *                                  when pixelArray is null and when pixelArray has a null pixel.
   */
  public ImageImpl(int width, int height, int maxValue, Pixel[][] pixelArray)
          throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException(String.format("Invalid image width and/or height"));
    }
    this.width = width;
    this.height = height;
    if (maxValue < 0) {
      throw new IllegalArgumentException(String.format("Invalid max pixel value"));
    }
    this.maxValue = maxValue;

    if (pixelArray == null) {
      throw new IllegalArgumentException("Array is null");
    }
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (pixelArray[row][col] == null) {
          throw new IllegalArgumentException(String.format("Null pixel in image"));
        }
      }
    }
    this.pixelArray = pixelArray;
  }

  /**
   * method used to get the width of the image.
   *
   * @return integer that represents the width of the image.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * method used to get the height of an image.
   *
   * @return integer that represents the height of an image.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * method used to get the maxValue if an image.
   *
   * @return integer that represents the maxValue of an image.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * method used to get the total number of pixels in an image.
   *
   * @return integer that represents the total number of pixels.
   */
  public int totalPixels() {
    return this.width * this.height;
  }

  /**
   * method used to grab a pixel at a particular place in the image.
   *
   * @param row row of the pixel in image.
   * @param col col of pixel in image.
   * @return Pixel at particular image spot
   * @throws IllegalArgumentException when the row and col values are greater than width/height
   *                                  or negative
   */
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row > this.height || col > this.width || row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid row or col value");
    }
    return this.pixelArray[row][col];
  }

  /**
   * equals method usd to compare two images.
   *
   * @param o other image being comapred to.
   * @return boolean that represents if images are equal of not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageImpl)) {
      return false;
    }
    ImageImpl i = (ImageImpl) o;
    return (this.width == i.width && this.height == i.height && this.maxValue == i.maxValue
            && this.equalPixelArray(i));
  }

  /**
   * Helper method for equals that compares/determines if the pixelArrays in both images
   * are equivalent.
   *
   * @param i other image being compared to.
   * @return boolean tat determines if pixel arrays are equal.
   */
  private boolean equalPixelArray(ImageImpl i) {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (!(this.pixelArray[row][col].equals(i.pixelArray[row][col]))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * method for overriding hashcode when comparing images (assists equals method).
   *
   * @return int that is hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, this.maxValue, this.pixelArray);
  }

  @Override
  public String ppmOutput() {
    StringBuilder ppm = new StringBuilder();
    // ASCII PPM file
    ppm.append("P3" + "\n");
    ppm.append(String.format("%d %d\n", this.width, this.height));
    ppm.append(this.maxValue);
    ppm.append("\n");
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        ppm.append(pixelArray[row][col].getRed());
        ppm.append("\n");
        ppm.append(pixelArray[row][col].getGreen());
        ppm.append("\n");
        ppm.append(pixelArray[row][col].getBlue());
        ppm.append("\n");
      }
    }
    return ppm.toString();
  }

  /**
   * Method that calculates all the pixel values of each color.
   *
   * @param channel channel.
   * @return 2D array with pixel color values depending on the channel.
   */
  protected int[][] channelPixels(String channel) {
    int[][] imageChannel = new int[this.height][this.width];

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        switch (channel) {
          case "red":
            imageChannel[row][col] = this.getPixelAt(row, col).getRed();
            break;
          case "green":
            imageChannel[row][col] = this.getPixelAt(row, col).getGreen();
            break;
          case "blue":
            imageChannel[row][col] = this.getPixelAt(row, col).getBlue();
            break;
          case "intensity":
            imageChannel[row][col] = this.getPixelAt(row, col).intensity();
            break;
          default:
            throw new IllegalArgumentException("Invalid channel color");
        }
      }
    }
    return imageChannel;
  }

  /**
   * Method that creates the data representation of the hsistogram for a particular image.
   *
   * @return 2D array that represents the Image's histogram.
   */
  protected int[][] makeHistogram() {
    int[][] redPixels = this.channelPixels("red");
    int[][] greenPixels = this.channelPixels("green");
    int[][] bluePixels = this.channelPixels("blue");
    int[][] intensityPixels = this.channelPixels("intensity");
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        this.histogramRow("red", redPixels, row, col);
        this.histogramRow("green", greenPixels, row, col);
        this.histogramRow("blue", bluePixels, row, col);
        this.histogramRow("intensity", intensityPixels, row, col);
      }
    }
    return histogram;
  }

  /**
   * Creates the data for each individual line on the histogram based on the color.
   *
   * @param color  color of the pixels specified.
   * @param pixels 2D array of pixels for an image.
   * @param row    row of pixel in the 2D array.
   * @param col    col of pixel in the 2D array.
   */
  private void histogramRow(String color, int[][] pixels, int row, int col) {
    int index = pixels[row][col];
    switch (color) {
      case "red":
        this.histogram[0][index] += 1;
        break;
      case "green":
        this.histogram[1][index] += 1;
        break;
      case "blue":
        this.histogram[2][index] += 1;
        break;
      case "intensity":
        this.histogram[3][index] += 1;
        break;
      default:
        break;
    }
  }
}
