package model;

import java.util.Objects;

/**
 * Class that represents a single pixel within an image. Contains all the methods used to determine
 * the state of the pixel, as well as functionality used to change the pixel.
 */
public class Pixel implements IPixel {
  private final int red;
  private final int green;
  private final int blue;
  private final int maxValue;

  /**
   * Represents the constructor for a pixel.
   *
   * @param red      component.
   * @param green    component.
   * @param blue     component.
   * @param maxValue of the pixel color component.
   * @throws IllegalArgumentException when the pixel color is greater than the maxValue,
   *                                  the pixel color is less than zero,
   *                                  the maxValue is less than zero.
   */
  public Pixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (maxValue < 0) {
      throw new IllegalArgumentException("Invalid max pixel value");
    }
    this.maxValue = maxValue;
    this.red = this.validPixel(red);
    this.green = this.validPixel(green);
    this.blue = this.validPixel(blue);
  }

  /**
   * Helper method that check that the color value for the pixel is valid.
   *
   * @param colorValue represents the color value of a particular color of pixel.
   * @return color value if it is valid.
   * @throws IllegalArgumentException if the color value is greater than the maxValue or less
   *                                  than zero.
   */
  private int validPixel(int colorValue) throws IllegalArgumentException {
    if (colorValue < 0) {
      return 0;
    }
    else if (colorValue > maxValue) {
      return maxValue;
    }
    return colorValue;
//    if (colorValue > maxValue || colorValue < 0) {
//      throw new IllegalArgumentException("Invalid Pixel Color Value");
//    }
//    return colorValue;
  }

  /**
   * Method that gets the red component of a pixel.
   *
   * @return integer that represents the red component.
   */
  @Override
  public int getRed() {
    return this.red;
  }

  /**
   * Method that gets the green component of a pixel.
   *
   * @return integer that represents the green component.
   */
  @Override
  public int getGreen() {
    return this.green;
  }

  /**
   * Method that gets the blue component of a pixel.
   *
   * @return integer that represents the blue component.
   */
  @Override
  public int getBlue() {
    return this.blue;
  }

  /**
   * Method that calculates the intensity value (average of colors) of a particular pixel.
   *
   * @return integer that represents intensity value of pixel.
   */
  private int intensity() {
    double intensity = Math.round((this.red + this.green + this.blue) / 3.0);
    return (int)intensity;
  }

  /**
   * Method that calculates the value-component of a particular pixel. Greater int value out of
   * all three colors.
   *
   * @return integer value-component of pixel.
   */
  private int value() {
    if (this.red > this.green && this.red > this.blue) {
      return this.red;
    } else if (this.green > this.red && this.green > this.blue) {
      return this.green;
    } else {
      return this.blue;
    }
  }

  /**
   * Methods that calculates the luma-component of particular pixel.
   *
   * @return integer that represents the luma-component of a pixel.
   */
  private int luma() {
    double luma = (0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue);
    return (int) Math.round(luma);
  }

  /**
   * Methods that changes all the color components of a pixel by a particular increment.
   *
   * @param change int that represents change in colors of pixel.
   * @return new Pixel whose color have been changed by increment.
   */
  protected Pixel brighten(int change) {
    int red = this.checkBrightenChange(this.red, change);
    int green = this.checkBrightenChange(this.green, change);
    int blue = this.checkBrightenChange(this.blue, change);
    return new Pixel(red, green, blue, maxValue);
  }

  /**
   * Helper method for brighten. If the change results in a color greater than maxValue or color
   * integer less than zero, it will just max the value to maxValue or stop at zero.
   *
   * @param color  particular pixel color being changed.
   * @param change integer increment that pixel color is being changed by.
   * @return color integer value used for the brighten function.
   */
  private int checkBrightenChange(int color, int change) {
    if (color + change > maxValue) {
      color = maxValue;
    } else if (color + change < 0) {
      color = 0;
    } else {
      color += change;
    }
    return color;
  }

  /**
   * Method that applies a particular Greyscale option to the pixel.
   *
   * @param componentType represents particular Greyscale change that is specified to be used
   *                      on the pixel.
   * @return new Pixel with the particular Greyscale method applied.
   */

  protected Pixel greyscale(String componentType) {
    int color;
    switch (componentType) {
      case "value-component":
        color = this.value();
        break;
      case "intensity-component":
        color = this.intensity();
        break;
      case "luma-component":
        color = this.luma();
        break;
      case "red-component":
        color = this.red;
        break;
      case "green-component":
        color = this.green;
        break;
      case "blue-component":
        color = this.blue;
        break;
      case "greyscale":
        int[][] rgb = { {this.red}, {this.green}, {this.blue} };
        double[] transform = new double[]{0.2126, 0.7152, 0.0722};
        double colorDouble = Math.round(transform[0] * (double)rgb[0][0] + transform[1] *
                (double)rgb[1][0] + transform[2] * (double)rgb[2][0]);
        color = (int)colorDouble;
        break;
      default:
        throw new IllegalArgumentException("Invalid component type");

    }
    return new Pixel(color, color, color, maxValue);
  }

  //  protected Pixel greyscale(String componentType) {
//    int[][] rgb = { {this.red}, {this.green}, {this.blue} };
//    double[] transform;
//
//    switch (componentType) {
//      case "value-component":
//        if (this.red == this.value()){
//          transform = new double[]{1, 0, 0};
//        }
//        else if (this.green == this.value()){
//          transform = new double[]{0, 1, 0};
//        }
//        else {
//          transform = new double[]{0, 0, 1};
//        }
//        break;
//      case "intensity-component":
//        transform = new double[]{(0.333), (0.333), (0.333)};
//        break;
//      case "luma-component":
//        transform = new double[]{0.2126, 0.7152, 0.0722};
//        break;
//      case "red-component":
//        transform = new double[]{1, 0, 0};
//        break;
//      case "green-component":
//        transform = new double[]{0, 1, 0};
//        break;
//      case "blue-component":
//        transform = new double[]{0, 0, 1};
//        break;
//      default:
//        throw new IllegalArgumentException("Invalid component type");
//    }
//    int color = this.matrixMult(rgb, transform);
//    return new Pixel(color, color, color, maxValue);
//  }
//
//  private int matrixMult(int[][] rgb, double[] transform){
//    double color = Math.round(transform[0] * (double)rgb[0][0] + transform[1] * (double)rgb[1][0] + transform[2] * (double)rgb[2][0]);
//    return (int)color;
//  }

  protected Pixel sepia() {
    double[][] sepiaTransform = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    int[] rgb = {this.red, this.green, this.blue};
    int[] newPixel = new int[3];
    for (int row = 0; row < 3; row++) {
      double rowTotal = 0;
      for (int i = 0; i < 3; i++) {
        rowTotal += sepiaTransform[row][i] * (double)rgb[i];
      }
      newPixel[row] = (int)Math.round(rowTotal);
    }
    return new Pixel(newPixel[0], newPixel[1], newPixel[2], this.maxValue);
  }


  /**
   * overridden equals function for pixel.
   *
   * @param o other pixel being compared.
   * @return boolean value that indicates if pixels are the same.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }
    Pixel p = (Pixel) o;
    return (this.red == p.red && this.green == p.green && this.blue == p.blue
            && this.maxValue == p.maxValue);
  }

  /**
   * Overridden hashcode method.
   *
   * @return int object hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, this.maxValue);
  }
}