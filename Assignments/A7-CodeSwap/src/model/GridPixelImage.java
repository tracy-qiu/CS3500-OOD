package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

import model.filters.BlurMatrix;
import model.filters.GreyscaleMatrix;
import model.filters.IMosaic;
import model.filters.ImageMosaic;
import model.filters.MatrixFunction;
import model.filters.SepiaMatrix;
import model.filters.SharpenMatrix;


/**
 * An implementation of a PixelImage with a 2D Pixel array.
 * A 2d array design choice seemed optimal to us as a grid is the most basic representation
 * of an image and the runtime of array operations make them perfect for handling large scale
 * images.
 */
public class GridPixelImage implements PixelImage {

  private final Pixel[][] imageGrid;

  private final int height;

  private final int width;


  private final Map<String, Supplier<MatrixFunction>> filMap;


  /**
   * Constructs a GridPixelImage.
   *
   * @param imageGrid 2d array
   * @param height    height of the image
   * @param width     width of the image
   */
  public GridPixelImage(Pixel[][] imageGrid, int height, int width) {
    if (imageGrid == null) {
      throw new IllegalArgumentException("null 2d array");
    }
    this.imageGrid = imageGrid;
    this.height = height;
    this.width = width;

    filMap = new HashMap<>();
    filMap.put("blur", () -> new MatrixFunction(this, new BlurMatrix()));
    filMap.put("sharpen", () -> new MatrixFunction(this, new SharpenMatrix()));
    filMap.put("greyscale", () -> new MatrixFunction(this, new GreyscaleMatrix()));
    filMap.put("sepia", () -> new MatrixFunction(this, new SepiaMatrix()));
  }

  /**
   * Returns the value of the pixel at the given row and column.
   *
   * @param row the row value of the pixel
   * @param col the column value of the pixel
   * @return the Pixel at the given row and column values
   */
  @Override
  public Pixel getPixelAt(int row, int col) {
    if (this.isValidPxl(row, col)) {
      return this.imageGrid[row][col];
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns the number of rows of pixels in this image.
   *
   * @return the height
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the number of columns of pixels in this image.
   *
   * @return the width
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the maximum numeric value a pixel in this PixelImage can have.
   *
   * @return an integer representing the max value.
   */
  @Override
  public int getMaxValue() {
    return this.getPixelAt(0, 0).getMaxVal();
  }

  /**
   * Visualizes the image as a color-scaled image.
   *
   * @param color the color to grayscale
   * @return a new PixelImage in blue scale.
   */
  @Override
  public PixelImage getComponent(String color) {
    Pixel[][] newGrid = new Pixel[height][width];
    int colVal = 0;
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        switch (color.toLowerCase()) {
          case "red":
            colVal = this.getPixelAt(row, col).getRed();
            break;
          case "green":
            colVal = this.getPixelAt(row, col).getGreen();
            break;
          case "blue":
            colVal = this.getPixelAt(row, col).getBlue();
            break;
          default:
            throw new IllegalArgumentException("Invalid component to visualize");
        }
        Pixel p = new StdPixel(colVal, colVal, colVal, this.getMaxValue());
        newGrid[row][col] = p;
      }
    }
    return new GridPixelImage(newGrid, this.height, this.width);
  }


  /**
   * Visualizes the given image in greyscale according to the specified type.
   *
   * @param type the type of greyscale - value, intensity or luma
   * @return a new Image greyscaled accordingly.
   */
  @Override
  public PixelImage visGreyscale(String type) {
    Pixel[][] newGrid = new Pixel[height][width];
    int colVal = 0;
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Pixel currPixel = this.getPixelAt(row, col);
        newGrid[row][col] = currPixel.greyscale(type);
      }
    }
    return new GridPixelImage(newGrid, this.height, this.width);
  }

  /**
   * Visualizes this image as the mirror image in the given direction of the original.
   *
   * @param direction the direction to flip.
   * @return a new Image flipped horizontally.
   */
  @Override
  public PixelImage flipImage(String direction) {
    Pixel[][] newGrid = new Pixel[height][width];
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Pixel currPixel = this.getPixelAt(row, col);
        Pixel p = new StdPixel(currPixel.getRed(), currPixel.getGreen(),
                currPixel.getBlue(), this.getMaxValue());
        switch (direction.toLowerCase()) {
          case "vertical":
            newGrid[this.height - 1 - row][col] = p;
            break;
          case "horizontal":
            newGrid[row][this.width - 1 - col] = p;
            break;
          default:
            throw new IllegalArgumentException("Invalid direction to flip: " + direction);
        }
      }
    }
    return new GridPixelImage(newGrid, this.height, this.width);
  }

  /**
   * Visualizes the image as a brighter/darker version of
   * the original image by a certain factor.
   *
   * @param factor the factor to brighten
   * @return a new PixelImage that is brightened.
   */
  @Override
  public PixelImage adjustBrightness(int factor) {
    Pixel[][] newGrid = new Pixel[height][width];
    int colVal = 0;
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Pixel currPixel = this.getPixelAt(row, col);
        newGrid[row][col] = currPixel.brighten(factor);
      }
    }
    return new GridPixelImage(newGrid, this.height, this.width);
  }

  /**
   * Applies mosaic effect to a given image based on number of seeds passed in by the user.
   * @param numSeeds integer that represents number of seeds.
   * @return new image that has had mosaic effect applied to it.
   */
  @Override
  public PixelImage mosaic(int numSeeds) {
    IMosaic mosaic = new ImageMosaic(this, numSeeds);
    return mosaic.createMosaic();
  }

  /**
   *
   * @param numSeeds
   * @param random
   * @return
   */
  @Override
  public PixelImage mosaicRandomTesting(int numSeeds, int random) {

    IMosaic mosaic = new ImageMosaic(this, numSeeds, random);
    return mosaic.createMosaic();
  }

  /**
   * Saves this image to the given path.
   *
   * @param path the path to save to.
   */
  @Override
  public void saveImg(String path) {
    try {
      FileWriter save = new FileWriter(path);
      save.write("P3\n");
      save.write(String.format("%d %d\n", this.width, this.height));
      save.write(String.format("%d\n", this.getMaxValue()));
      for (int row = 0; row < this.getHeight(); row++) {
        for (int col = 0; col < this.getWidth(); col++) {
          Pixel currPixel = this.getPixelAt(row, col);
          save.write(String.format("%d\n", currPixel.getRed()));
          save.write(String.format("%d\n", currPixel.getGreen()));
          save.write(String.format("%d\n", currPixel.getBlue()));
        }
      }
      save.close();

    } catch (IOException e) {
      throw new IllegalStateException("Couldn't write to file");
    }
  }

  /**
   * Filters this image according to the type.
   *
   * @param type the type of filter
   * @return a new pixel image that is filtered
   */
  @Override
  public PixelImage filter(String type) {
    this.ensureKey(filMap, type);
    return filMap.get(type).get().filterify();
  }

  /**
   * Ensures that the filtering method called actually exists in the hashmap.
   * @param filMap hashmap that contains all filtering coommands.
   * @param type type of filtering specified.
   * @param <T> general return type.
   */
  private <T> void ensureKey(Map<String, T> filMap, String type) {
    if (!filMap.containsKey(type)) {
      throw new IllegalArgumentException("invalid filtering operation");
    }
  }


  /**
   * Is the given posn within the valid boundaries of the image.
   *
   * @param row row
   * @param col col
   * @return true if valid.
   */
  @Override
  public boolean isValidPxl(int row, int col) {
    return (row >= 0 && row < this.height) && (col >= 0 && col < this.width);
  }

  /**
   * Computes the frequency of rgb values and the intensity of each pixel.
   *
   * @return an array of 4 maxval sized arrays representing rgb and intensity
   */
  @Override
  public int[][] computeDistr() {
    int[][] toRet = new int[4][this.getMaxValue() + 1];
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Pixel currPxl = this.getPixelAt(row, col);
        toRet[0][currPxl.getRed()] += 1;
        toRet[1][currPxl.getGreen()] += 1;
        toRet[2][currPxl.getBlue()] += 1;
        toRet[3][currPxl.greyscale("intensity").getRed()] += 1;
      }
    }
    return toRet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GridPixelImage)) {
      return false;
    }
    GridPixelImage that = (GridPixelImage) o;
    return height == that.height
            && width == that.width
            && this.checkSameGrid(that);
  }

  /**
   * checks if this pixel and that GridPixelImage have the same 2d array.
   *
   * @param that the pixel image to compare to
   * @return true if equal
   */
  private boolean checkSameGrid(GridPixelImage that) {
    for (int row = 0; row < this.getHeight(); row++) {
      if (!(Arrays.equals(this.imageGrid[row], that.imageGrid[row]))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(height, width);
    result = 31 * result + Arrays.hashCode(imageGrid);
    return result;
  }
}
