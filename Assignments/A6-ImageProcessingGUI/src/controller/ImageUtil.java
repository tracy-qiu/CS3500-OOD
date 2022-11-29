package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IImage;
import model.ImageImpl;
import model.Pixel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Contains method readPPM which converts a PPM file to an ImageImpl class of our design.
 * ALso contains method writePPM which converts an ImageImpl back to the PPM file format and
 * saves it to the user's computer.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ImageImpl readPPM(String filename) throws IOException, IllegalArgumentException {
    Pixel[][] image;
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IOException(String.format("File " + filename + " not found!"));
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

    String token;

    token = sc.next();
    FileWriter myWriter = new FileWriter("ppmFileInfo.txt");
    if (!token.equals("P3")) {
      myWriter.write("Invalid PPM file: plain RAW file should begin with P3\n");
    }
    int width = sc.nextInt();
    myWriter.write("Width of image: " + width + "\n");
    int height = sc.nextInt();
    myWriter.write("Height of image: " + height + "\n");
    int maxValue = sc.nextInt();
    myWriter.write("Maximum value of a color in this file (usually 255): " + maxValue + "\n");
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid negative width or height");
    }
    image = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = Integer.parseInt(sc.next());
        int g = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());

        image[i][j] = new Pixel(r, g, b, maxValue);
        myWriter.write("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b + "\n");
      }
    }

    myWriter.close();
    ImageImpl imageImpl = new ImageImpl(width, height, maxValue, image);
    return imageImpl;
  }

  /**
   * Method that converts an image to a PPM file and saves it to your computer.
   *
   * @param imageImpl Image being converted and saved.
   * @param out       output stream being saved to the computer
   * @throws IllegalArgumentException Image or OutputStream are null/invalid.
   * @throws IOException              if the file cannot be saved to the computer.
   */
  public static void writePPM(ImageImpl imageImpl, OutputStream out)
          throws IllegalArgumentException, IOException {
    out.write(imageImpl.ppmOutput().getBytes());
    out.close();
  }

  /**
   * read an ImageFile of type JPEG, PNG or BMP.
   *
   * @param fileName name of the file.
   * @return Image that has been read in from the file.
   * @throws IOException              if the file is invalid / not found.
   * @throws IllegalArgumentException if any arguments for creating the image or invalid as
   *                                  specified in the ImageImpl constructor.
   */
  public static ImageImpl readImageFile(String fileName)
          throws IOException, IllegalArgumentException {
    Pixel[][] image;
    int height;
    int width;
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(fileName));
      height = bufferedImage.getHeight();
      width = bufferedImage.getWidth();
      image = new Pixel[height][width];
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          Color newColor = new Color(bufferedImage.getRGB(col, row));
          int red = newColor.getRed();
          int green = newColor.getGreen();
          int blue = newColor.getBlue();
          image[row][col] = new Pixel(red, green, blue, 255);

        }
      }
      ImageImpl imageImpl = new ImageImpl(width, height, 255, image);
      return imageImpl;
    } catch (IOException e) {
      throw new IOException(String.format("File " + fileName + " invalid!"));
    }
  }

  /**
   * converts an ImageImpl to a buffered image of a specific type (JPEG, PNG, BMP).
   *
   * @param image representation of image as image implementation of the program
   * @return Buffered Image that is saved to the user's computer.
   */
  public static BufferedImage writeImageFile(IImage image) {
    BufferedImage outputImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int height;
    int width;
    height = image.getHeight();
    width = image.getWidth();
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color newColor =
                new Color(image.getPixelAt(row, col).getRed(),
                        image.getPixelAt(row, col).getGreen(),
                        image.getPixelAt(row, col).getBlue());
        outputImage.setRGB(col, row, newColor.getRGB());
      }
    }
    return outputImage;
  }

}