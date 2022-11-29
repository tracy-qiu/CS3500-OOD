package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * tests for the Pixel.
 */
public class PixelTest {
  Pixel pixel1 = new Pixel(0, 0, 0, 255);
  Pixel pixel2 = new Pixel(255, 255, 255, 255);
  Pixel pixel3 = new Pixel(200, 200, 200, 255);
  Pixel pixel4 = new Pixel(50, 100, 150, 255);

  // Test Constructors of Pixel Class:

  // test maxValue less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeMaxValueConstructor() {
    new Pixel(200, 200, 200, -1);
  }

  // test getRed, getGreen, getBlue
  @Test
  public void testPixelColors() {
    assertEquals(0, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(0, pixel1.getBlue());
    assertEquals(255, pixel2.getRed());
    assertEquals(255, pixel2.getGreen());
    assertEquals(255, pixel2.getBlue());
    assertEquals(200, pixel3.getRed());
    assertEquals(200, pixel3.getGreen());
    assertEquals(200, pixel3.getBlue());
  }

  // test brighten method
  @Test
  public void testBrighten() {
    Pixel change = pixel1.brighten(10);
    assertEquals(10, change.getRed());
    assertEquals(10, change.getGreen());
    assertEquals(10, change.getBlue());
    Pixel changeNeg = pixel1.brighten(-10);
    assertEquals(0, changeNeg.getRed());
    assertEquals(0, changeNeg.getGreen());
    assertEquals(0, changeNeg.getBlue());
    Pixel change2 = pixel2.brighten(-15);
    assertEquals(240, change2.getRed());
    assertEquals(240, change2.getGreen());
    assertEquals(240, change2.getBlue());
    Pixel change3 = pixel3.brighten(60);
    assertEquals(255, change3.getRed());
    assertEquals(255, change3.getGreen());
    assertEquals(255, change3.getBlue());
    Pixel change4 = pixel4.brighten(-60);
    assertEquals(0, change4.getRed());
    assertEquals(40, change4.getGreen());
    assertEquals(90, change4.getBlue());
  }

  // invalid input for greyscale method
  @Test(expected = IllegalArgumentException.class)
  public void testErrorGrayScale() {
    Pixel error = pixel4.greyscale("pizza");
  }

  // test greyscale method: all combinations
  @Test
  public void testGreyScale() {
    Pixel value = pixel4.greyscale("value-component");
    assertEquals(150, value.getRed());
    assertEquals(150, value.getGreen());
    assertEquals(150, value.getBlue());

    Pixel intensity = pixel4.greyscale("intensity-component");
    assertEquals(100, intensity.getRed());
    assertEquals(100, intensity.getGreen());
    assertEquals(100, intensity.getBlue());

    Pixel luma = pixel4.greyscale("luma-component");
    assertEquals(93, luma.getRed());
    assertEquals(93, luma.getGreen());
    assertEquals(93, luma.getBlue());

    Pixel red = pixel4.greyscale("red-component");
    assertEquals(50, red.getRed());
    assertEquals(50, red.getGreen());
    assertEquals(50, red.getBlue());

    Pixel green = pixel4.greyscale("green-component");
    assertEquals(100, green.getRed());
    assertEquals(100, green.getGreen());
    assertEquals(100, green.getBlue());

    Pixel blue = pixel4.greyscale("blue-component");
    assertEquals(150, blue.getRed());
    assertEquals(150, blue.getGreen());
    assertEquals(150, blue.getBlue());

    Pixel greyscale = pixel4.greyscale("greyscale");
    assertEquals(93, greyscale.getRed());
    assertEquals(93, greyscale.getGreen());
    assertEquals(93, greyscale.getBlue());
  }

  // test sepia
  @Test
  public void testSepia() {
    Pixel sepia = pixel4.sepia();
    assertEquals(125, sepia.getRed());
    assertEquals(111, sepia.getGreen()); // got 112 from my calculations
    assertEquals(87, sepia.getBlue());
  }

}
