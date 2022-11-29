package model;

import org.junit.Test;

import java.io.IOException;

import static controller.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * tests for the Image Processor model.
 */
public class ModelTest {

  Pixel pixel1 = new Pixel(10, 20, 33, 255);
  Pixel pixel2 = new Pixel(125, 66, 3, 255);
  Pixel pixel3 = new Pixel(7, 87, 90, 255);
  Pixel pixel4 = new Pixel(55, 44, 66, 255);
  Pixel pixel5 = null;

  Pixel pixel6 = new Pixel(28, 0, 244, 255);
  Pixel pixel7 = new Pixel(17, 99, 255, 255);
  Pixel[][] pixelArray = {{pixel1, pixel2}, {pixel3, pixel4}};
  Pixel[][] pixelArray2 = {{pixel1, pixel2}, {pixel3, pixel5}};

  Pixel[][] pixelArray3 = {{pixel1, pixel2}, {pixel3, pixel4}, {pixel6, pixel7}};
  int maxValue = 255;
  int width = 2;
  int height = 2;
  ImageImpl image1 = new ImageImpl(width, height, maxValue, pixelArray);
  ImageImpl image2 = new ImageImpl(2, 3, maxValue, pixelArray3);

  ImageModel model1 = new ImageModelImpl();

  // retrieve image from hashmap (model)
  @Test
  public void testValidAddImage() {

    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("original", twoByThree);

    // check if the image is equal to twoByThree (original)
    ImageImpl test1 = model1.getImageAt("original");
    assertEquals(pixel1, test1.getPixelAt(0, 0));
    assertEquals(pixel2, test1.getPixelAt(0, 1));
    assertEquals(pixel3, test1.getPixelAt(1, 0));
    assertEquals(pixel4, test1.getPixelAt(1, 1));
    assertEquals(pixel6, test1.getPixelAt(2, 0));
    assertEquals(pixel7, test1.getPixelAt(2, 1));
  }

  // key doesn't exist in the hash-map (model)
  @Test(expected = IllegalArgumentException.class)
  public void testFailedGetImageAt() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("original", twoByThree);
    ImageImpl test1 = model1.getImageAt("twoByThree");
  }

  @Test
  public void testGetImageAt() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }
    model1.addImage("twoByThree", twoByThree);
    model1.addImage("threeByTwo", threeByTwo);

    ImageImpl test1 = model1.getImageAt("twoByThree");
    ImageImpl test2 = model1.getImageAt("threeByTwo");

    assertEquals(twoByThree, test1);
    assertEquals(threeByTwo, test2);
  }
}
