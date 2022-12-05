package model;

import org.junit.Test;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


/**
 * Tests the functionality of PixelImage.
 */
public class GridPixelImageTest {

  PixelImage pI1;
  PixelImage pI2;
  PixelImage pI3;
  PixelImage pI4;
  Pixel[][] pA1;
  Pixel[][] pA2;
  Pixel[][] pA3;
  Pixel[][] pA4;


  /**
   * Inits the data.
   */
  private void initData() {
    pA1 = new Pixel[][]{{new StdPixel(10, 20, 30, 255),
            new StdPixel(255, 100, 10, 255)}, {
            new StdPixel(0, 10, 20, 255),
            new StdPixel(50, 30, 10, 255)}};
    pA2 = new Pixel[][]{{new StdPixel(0, 100, 200, 255)}, {
            new StdPixel(100, 200, 255, 255)}};
    pA3 = new Pixel[][]{{new StdPixel(0, 0, 0, 500),
            new StdPixel(255, 255, 255, 500)}};
    pA4 = new Pixel[][]{{new StdPixel(10, 20, 30, 255),
            new StdPixel(255, 100, 10, 255)}, {
            new StdPixel(0, 10, 20, 255),
            new StdPixel(50, 30, 10, 255)}, {
            new StdPixel(15, 5, 50, 255),
            new StdPixel(100, 20, 60, 255)}, {
            new StdPixel(10, 20, 30, 255),
            new StdPixel(255, 100, 10, 255)}, {
            new StdPixel(10, 20, 30, 255),
            new StdPixel(255, 100, 10, 255)}};
    pI1 = new GridPixelImage(pA1, 2, 2);
    pI2 = new GridPixelImage(pA2, 2, 1);
    pI3 = new GridPixelImage(pA3, 1, 2);
    pI4 = new GridPixelImage(pA4, 5, 2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorTest() {
    pI1 = new GridPixelImage(null, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelException() {
    this.initData();
    pI1.getPixelAt(499, -590);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCompException() {
    this.initData();
    pI1.getComponent("agfasdhfjks");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipException() {
    this.initData();
    pI1.flipImage("agfasdhfjks");
  }


  @Test
  public void getPixelAt() {
    this.initData();
    assertEquals(new StdPixel(255, 100, 10, 255),
            pI1.getPixelAt(0, 1));
    assertEquals(new StdPixel(100, 200, 255, 255),
            pI2.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 500),
            pI3.getPixelAt(0, 0));
  }

  @Test
  public void getHeight() {
    this.initData();
    assertEquals(2, pI1.getHeight());
    assertEquals(2, pI2.getHeight());
    assertEquals(1, pI3.getHeight());
  }

  @Test
  public void getWidth() {
    this.initData();
    assertEquals(2, pI1.getWidth());
    assertEquals(1, pI2.getWidth());
    assertEquals(2, pI3.getWidth());
  }

  @Test
  public void getMaxValue() {
    this.initData();
    assertEquals(255, pI1.getMaxValue());
    assertEquals(255, pI2.getMaxValue());
    assertEquals(500, pI3.getMaxValue());
  }

  @Test
  public void getComponent() {
    this.initData();
    assertEquals(new GridPixelImage(
            new Pixel[][]{{new StdPixel(10, 10, 10, 255),
                    new StdPixel(255, 255, 255, 255)}, {
                    new StdPixel(0, 0, 0, 255),
                    new StdPixel(50, 50, 50, 255)}},
            2, 2), pI1.getComponent("red"));
    assertEquals(new GridPixelImage(
                    new Pixel[][]{{new StdPixel(100, 100, 100, 255)}, {
                            new StdPixel(200, 200, 200, 255)}},
                    2, 1),
            pI2.getComponent("green"));
    assertEquals(new GridPixelImage(
            new Pixel[][]{{new StdPixel(0, 0, 0, 500),
                    new StdPixel(255, 255, 255, 500)}},
            1, 2), pI3.getComponent("blue"));
  }

  @Test
  public void visGreyscale() {
    this.initData();
    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
                    30, 30, 30, 255),
            new StdPixel(255, 255, 255, 255)}, {
            new StdPixel(20, 20, 20, 255),
            new StdPixel(50, 50, 50, 255)}},
                    2, 2),
            pI1.visGreyscale("value"));
    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
            100, 100, 100, 255)}, {
            new StdPixel(185, 185, 185, 255)}},
            2, 1), pI2.visGreyscale("intensity"));
    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
                    0, 0, 0, 500), new StdPixel(
                    255, 255, 255, 500)}}, 1, 2),
            pI3.visGreyscale("luma"));
  }

  @Test
  public void flipImage() {
    this.initData();
    assertEquals(new GridPixelImage(new Pixel[][]{{
            new StdPixel(0, 10, 20, 255), new StdPixel(
                    50, 30, 10, 255)}, {
            new StdPixel(10, 20, 30, 255), new StdPixel(
                    255, 100, 10, 255)}}, 2, 2),
            pI1.flipImage("vertical"));
    assertEquals(pI2, pI2.flipImage("horizontal"));
    assertEquals(pI3, pI3.flipImage("vertical"));
  }

  @Test
  public void adjustBrightness() {
    this.initData();
    assertEquals(pI1, pI1.adjustBrightness(0));
    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
            10, 110, 210, 255)}, {
            new StdPixel(110, 210, 255, 255)}},
            2, 1), pI2.adjustBrightness(10));
    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
                    0, 0, 0, 500), new StdPixel(
                    245, 245, 245, 500)}}, 1, 2),
            pI3.adjustBrightness(-10));
  }

  @Test
  public void saveImg() {
    this.initData();
    PixelImage pI1Darker = pI1.adjustBrightness(-10);
    pI1Darker.saveImg("res/pI1-dark.ppm");
    Scanner image = ImageUtil.readPPM(new File("res/pI1-dark.ppm"));
    assertEquals("P3", image.next());
    assertEquals("2", image.next());
    assertEquals("2", image.next());
    assertEquals("255", image.next());
    assertEquals("0", image.next());
    assertEquals("10", image.next());
    assertEquals("20", image.next());
    assertEquals("245", image.next());
    assertEquals("90", image.next());
    assertEquals("0", image.next());
    assertEquals("0", image.next());
    assertEquals("0", image.next());
    assertEquals("10", image.next());
    assertEquals("40", image.next());
    assertEquals("20", image.next());
    assertEquals("0", image.next());
  }

  @Test
  public void filter() {
    this.initData();

    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(
                    32, 32, 32, 500), new StdPixel(
                    64, 64, 64, 500)}}, 1, 2),
            pI3.filter("blur"));

  }

  @Test
  public void mosaic() {
    this.initData();

    assertEquals(new GridPixelImage(new Pixel[][]{{new StdPixel(78, 40, 17, 255),
                    new StdPixel(78, 40, 17, 255)}, {
                    new StdPixel(78, 40, 17, 255),
                    new StdPixel(78, 40, 17, 255)},{
                    new StdPixel(15, 5, 50, 255),
                    new StdPixel(126, 52, 28, 255)},{
                    new StdPixel(126, 52, 28, 255),
                    new StdPixel(126, 52, 28, 255)}, {
                    new StdPixel(126, 52, 28, 255),
                            new StdPixel(126, 52, 28, 255)}}, 5, 2),
            pI4.mosaicRandomTesting(3, 37));
  }
}