package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class contains utility methods to read a PPM image
 * from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param file the path of the file.
   */
  public static Scanner readPPM(File file) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    return sc;

  }

  /**
   * Converts the given buffered image to a pixel image.
   *
   * @param buff the buffered image
   * @return a pixelimage
   */
  public static PixelImage bufferedToPixel(BufferedImage buff) {
    Pixel[][] newGrid = new Pixel[buff.getHeight()][buff.getWidth()];
    for (int row = 0; row < buff.getHeight(); row++) {
      for (int col = 0; col < buff.getWidth(); col++) {
        Color rgb = new Color(buff.getRGB(col, row));
        newGrid[row][col] = new StdPixel(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 255);
      }
    }
    return new GridPixelImage(newGrid, buff.getHeight(), buff.getWidth());
  }


  /**
   * Converts the given pixelimage to a buffered image.
   *
   * @param pixel the pixel image
   * @return a buffered image
   */
  public static BufferedImage pixelToBuffered(PixelImage pixel) {
    BufferedImage toRet = new BufferedImage(pixel.getWidth(), pixel.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < pixel.getHeight(); row++) {
      for (int col = 0; col < pixel.getWidth(); col++) {
        Pixel curPxl = pixel.getPixelAt(row, col);
        Color rgb = new Color(curPxl.getRed(), curPxl.getGreen(), curPxl.getBlue());
        toRet.setRGB(col, row, rgb.getRGB());
      }
    }
    return toRet;
  }
}

