package model;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * class that represents all the methods used to retrieve the state of the image.
 */
public class ImageImpl implements IImage {
  private final int width;
  private final int height;
  private final int maxValue;
  private Pixel[][] pixelArray;

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
          default:
            throw new IllegalArgumentException("Invalid channel color");
        }
      }
    }
    return imageChannel;
  }

  protected int[][] makeHistogram() {
    int[][] historgram = null;

    return historgram;
  }

}
