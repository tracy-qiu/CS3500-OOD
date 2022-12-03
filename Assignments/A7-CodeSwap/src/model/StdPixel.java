package model;

import java.util.Objects;

/**
 * An implementation of Pixel with RGB and max pixel value.
 * Representing a Pixel with its 3 primary color values was the best design we could think of
 * as it made most of the operations on our image processor easier.
 */
public class StdPixel implements Pixel {
  private final int redVal;
  private final int greVal;
  private final int bluVal;
  private final int maxVal;

  /**
   * Constructs an StdPixel.
   *
   * @param redVal red
   * @param greVal greed
   * @param bluVal blue
   * @param maxVal max
   */
  public StdPixel(int redVal, int greVal, int bluVal, int maxVal) {
    if (redVal > maxVal || bluVal > maxVal || greVal > maxVal) {
      throw new IllegalArgumentException("Color value should not be greater than max value");
    } else if (redVal < 0 || greVal < 0 || bluVal < 0) {
      throw new IllegalArgumentException("Color value cannot be negative");
    }
    this.redVal = redVal;
    this.greVal = greVal;
    this.bluVal = bluVal;
    this.maxVal = maxVal;
  }

  /**
   * An observer on a pixel that returns the red component.
   *
   * @return int between 0 and MaxVal
   */
  @Override
  public int getRed() {
    return this.redVal;
  }

  /**
   * An observer on a pixel that returns the green component.
   *
   * @return int between 0 and MaxVal
   */
  @Override
  public int getGreen() {
    return this.greVal;
  }

  /**
   * An observer on a pixel that returns the blue component.
   *
   * @return int between 0 and MaxVal
   */
  @Override
  public int getBlue() {
    return this.bluVal;
  }

  /**
   * An observer on a pixel that returns the maximum possible value of a component.
   *
   * @return an integer
   */
  @Override
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns a pixel representing the greyscale version of the pixel.
   *
   * @return a greyscale version of the pixel
   */
  @Override
  public Pixel greyscale(String type) {
    int colVal = 0;
    switch (type.toLowerCase()) {
      case "value":
        colVal = Math.max(Math.max(this.getRed(), this.getGreen())
                , this.getBlue());
        break;
      case "intensity":
        colVal = this.getBlue() + this.getGreen() + this.getRed();
        colVal /= 3;
        break;
      case "luma":
        colVal = (int) Math.round(
                0.2126 * this.getRed() +
                        0.7152 * this.getGreen() + 0.0722 * this.getBlue());
        break;
      default:
        throw new IllegalArgumentException("Invalid way to visualize greyscale: " + type);
    }
    return new StdPixel(colVal, colVal, colVal, this.getMaxVal());
  }

  /**
   * Returns a pixel that is representing the pixel brightened by the given factor.
   *
   * @param factor factor to brighten
   * @return a brightened version of the pixel
   */
  @Override
  public Pixel brighten(int factor) {
    int newRed = this.redVal + factor;
    int newGreen = this.greVal + factor;
    int newBlue = this.bluVal + factor;
    newRed = this.normalize(newRed);
    newGreen = this.normalize(newGreen);
    newBlue = this.normalize(newBlue);
    return new StdPixel(newRed, newGreen, newBlue, this.getMaxVal());
  }

  /**
   * Adjusts the brightness to the closes valid value.
   *
   * @param colorVal the color to adjust
   * @return adjusted val
   */
  public int normalize(int colorVal) {
    if (colorVal < 0) {
      return 0;
    } else if (colorVal > this.getMaxVal()) {
      return this.getMaxVal();
    } else {
      return colorVal;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StdPixel)) {
      return false;
    }
    StdPixel stdPixel = (StdPixel) o;
    return redVal == stdPixel.redVal && greVal == stdPixel.greVal && bluVal == stdPixel.bluVal
            && maxVal == stdPixel.maxVal;
  }

  @Override
  public int hashCode() {
    return Objects.hash(redVal, greVal, bluVal, maxVal);
  }


  @Override
  public String toString() {
    return "StdPixel{" +
            "redVal=" + redVal +
            ", greVal=" + greVal +
            ", bluVal=" + bluVal +
            ", maxVal=" + maxVal +
            '}';
  }
}
