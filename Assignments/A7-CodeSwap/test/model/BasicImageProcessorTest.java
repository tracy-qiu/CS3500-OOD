package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;


/**
 * Tests the functionality of the img processor model.
 */
public class BasicImageProcessorTest {

  ImageProcessor imgpro1;


  /**
   * Inits the data.
   */
  @Before
  public void initData() throws IOException {
    imgpro1 = new BasicImageProcessor(new HashMap<String, PixelImage>());
    imgpro1.loadImage(new File("res/masterTester.ppm"), "master");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException() {
    imgpro1 = new BasicImageProcessor(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnsureKey() throws IOException {
    this.initData();
    imgpro1.getLoadedImg("deez");
  }


  @Test
  public void loadImage() throws IOException {
    this.initData();
    imgpro1.loadImage(new File("res/chess3.ppm"), "simple chess");
    PixelImage chess3 = imgpro1.getLoadedImg("simple chess");

    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3.getPixelAt(2, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3.getPixelAt(2, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3.getPixelAt(2, 2));

    this.initData();
    imgpro1.loadImage(new File("res/chess3.png"), "simple chess");
    PixelImage chess3png = imgpro1.getLoadedImg("simple chess");

    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3png.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3png.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3png.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3png.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3png.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3png.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3png.getPixelAt(2, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3png.getPixelAt(2, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3png.getPixelAt(2, 2));

    //In jpeg and jpg, there is slight data loss due to the conversion.
    this.initData();
    imgpro1.loadImage(new File("res/chess3.jpg"), "simple chess");
    PixelImage chess3jpg = imgpro1.getLoadedImg("simple chess");

    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpg.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3jpg.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpg.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3jpg.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpg.getPixelAt(1, 1));
    assertEquals(new StdPixel(246, 246, 246, 255),
            chess3jpg.getPixelAt(1, 2));
    assertEquals(new StdPixel(4, 4, 4, 255),
            chess3jpg.getPixelAt(2, 0));
    assertEquals(new StdPixel(241, 241, 241, 255),
            chess3jpg.getPixelAt(2, 1));
    assertEquals(new StdPixel(6, 6, 6, 255),
            chess3jpg.getPixelAt(2, 2));

    this.initData();
    imgpro1.loadImage(new File("res/chess3.jpeg"), "simple chess");
    PixelImage chess3jpeg = imgpro1.getLoadedImg("simple chess");

    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpeg.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3jpeg.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpeg.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            chess3jpeg.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            chess3jpeg.getPixelAt(1, 1));
    assertEquals(new StdPixel(246, 246, 246, 255),
            chess3jpeg.getPixelAt(1, 2));
    assertEquals(new StdPixel(4, 4, 4, 255),
            chess3jpeg.getPixelAt(2, 0));
    assertEquals(new StdPixel(241, 241, 241, 255),
            chess3jpeg.getPixelAt(2, 1));
    assertEquals(new StdPixel(6, 6, 6, 255),
            chess3jpeg.getPixelAt(2, 2));

  }

  @Test
  public void redComponent() throws IOException {
    this.initData();
    imgpro1.extractComponent("master", "master red", "red");
    PixelImage masterRed = imgpro1.getLoadedImg("master red");

    assertEquals(new StdPixel(255, 255, 255, 255),
            masterRed.getPixelAt(0, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterRed.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterRed.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterRed.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterRed.getPixelAt(1, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterRed.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterRed.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterRed.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterRed.getPixelAt(2, 2));

  }

  @Test
  public void greComponent() throws IOException {
    this.initData();
    imgpro1.extractComponent("master", "master green", "green");
    PixelImage masterGreen = imgpro1.getLoadedImg("master green");

    assertEquals(new StdPixel(0, 0, 0, 255),
            masterGreen.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterGreen.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterGreen.getPixelAt(0, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterGreen.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterGreen.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterGreen.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterGreen.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterGreen.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterGreen.getPixelAt(2, 2));
  }

  @Test
  public void bluComponent() throws IOException {
    this.initData();
    imgpro1.extractComponent("master", "master blue", "blue");
    PixelImage masterBlue = imgpro1.getLoadedImg("master blue");

    assertEquals(new StdPixel(0, 0, 0, 255),
            masterBlue.getPixelAt(0, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterBlue.getPixelAt(0, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterBlue.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterBlue.getPixelAt(1, 0));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterBlue.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterBlue.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterBlue.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterBlue.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterBlue.getPixelAt(2, 2));
  }

  @Test
  public void visValue() throws IOException {
    this.initData();
    imgpro1.visGreyscale("master", "master value", "value");
    PixelImage masterValue = imgpro1.getLoadedImg("master value");

    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(0, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(0, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterValue.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterValue.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterValue.getPixelAt(2, 2));
  }

  @Test
  public void visIntensity() throws IOException {
    this.initData();
    imgpro1.visGreyscale("master", "master intensity", "intensity");
    PixelImage masterIntensity = imgpro1.getLoadedImg("master intensity");

    assertEquals(new StdPixel(85, 85, 85, 255),
            masterIntensity.getPixelAt(0, 0));
    assertEquals(new StdPixel(85, 85, 85, 255),
            masterIntensity.getPixelAt(0, 1));
    assertEquals(new StdPixel(85, 85, 85, 255),
            masterIntensity.getPixelAt(0, 2));
    assertEquals(new StdPixel(170, 170, 170, 255),
            masterIntensity.getPixelAt(1, 0));
    assertEquals(new StdPixel(170, 170, 170, 255),
            masterIntensity.getPixelAt(1, 1));
    assertEquals(new StdPixel(170, 170, 170, 255),
            masterIntensity.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterIntensity.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterIntensity.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterIntensity.getPixelAt(2, 2));
  }

  @Test
  public void visLuma() throws IOException {
    this.initData();
    imgpro1.visGreyscale("master", "master luma", "luma");
    PixelImage masterLuma = imgpro1.getLoadedImg("master luma");


    assertEquals(new StdPixel(54, 54, 54, 255),
            masterLuma.getPixelAt(0, 0));
    assertEquals(new StdPixel(182, 182, 182, 255),
            masterLuma.getPixelAt(0, 1));
    assertEquals(new StdPixel(18, 18, 18, 255),
            masterLuma.getPixelAt(0, 2));
    assertEquals(new StdPixel(73, 73, 73, 255),
            masterLuma.getPixelAt(1, 0));
    assertEquals(new StdPixel(237, 237, 237, 255),
            masterLuma.getPixelAt(1, 1));
    assertEquals(new StdPixel(201, 201, 201, 255),
            masterLuma.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterLuma.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterLuma.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterLuma.getPixelAt(2, 2));
  }

  @Test
  public void flipImageHoriz() throws IOException {
    this.initData();
    imgpro1.flipImage("master", "master horiz", "horizontal");
    PixelImage masterHoriz = imgpro1.getLoadedImg("master horiz");


    assertEquals(new StdPixel(0, 0, 255, 255),
            masterHoriz.getPixelAt(0, 0));
    assertEquals(new StdPixel(0, 255, 0, 255),
            masterHoriz.getPixelAt(0, 1));
    assertEquals(new StdPixel(255, 0, 0, 255),
            masterHoriz.getPixelAt(0, 2));
    assertEquals(new StdPixel(0, 255, 255, 255),
            masterHoriz.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 0, 255),
            masterHoriz.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 0, 255, 255),
            masterHoriz.getPixelAt(1, 2));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterHoriz.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterHoriz.getPixelAt(2, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterHoriz.getPixelAt(2, 2));
  }

  @Test
  public void flipImageVert() throws IOException {
    this.initData();
    imgpro1.flipImage("master", "master vert", "vertical");
    PixelImage masterVert = imgpro1.getLoadedImg("master vert");


    assertEquals(new StdPixel(0, 0, 0, 255),
            masterVert.getPixelAt(0, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterVert.getPixelAt(0, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterVert.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 0, 255, 255),
            masterVert.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 0, 255),
            masterVert.getPixelAt(1, 1));
    assertEquals(new StdPixel(0, 255, 255, 255),
            masterVert.getPixelAt(1, 2));
    assertEquals(new StdPixel(255, 0, 0, 255),
            masterVert.getPixelAt(2, 0));
    assertEquals(new StdPixel(0, 255, 0, 255),
            masterVert.getPixelAt(2, 1));
    assertEquals(new StdPixel(0, 0, 255, 255),
            masterVert.getPixelAt(2, 2));
  }

  @Test
  public void flipTwice() throws IOException {
    this.initData();
    imgpro1.flipImage("master", "master vert", "vertical");
    imgpro1.flipImage("master vert", "master flip2", "horizontal");
    PixelImage masterVert = imgpro1.getLoadedImg("master flip2");


    assertEquals(new StdPixel(255, 255, 255, 255),
            masterVert.getPixelAt(0, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterVert.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterVert.getPixelAt(0, 2));
    assertEquals(new StdPixel(0, 255, 255, 255),
            masterVert.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 0, 255),
            masterVert.getPixelAt(1, 1));
    assertEquals(new StdPixel(255, 0, 255, 255),
            masterVert.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 255, 255),
            masterVert.getPixelAt(2, 0));
    assertEquals(new StdPixel(0, 255, 0, 255),
            masterVert.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 0, 0, 255),
            masterVert.getPixelAt(2, 2));
  }

  @Test
  public void imageBrighten() throws IOException {
    this.initData();
    imgpro1.adjustBrightness("master", 10, "master bright");
    PixelImage masterBright = imgpro1.getLoadedImg("master bright");

    assertEquals(new StdPixel(255, 10, 10, 255),
            masterBright.getPixelAt(0, 0));
    assertEquals(new StdPixel(10, 255, 10, 255),
            masterBright.getPixelAt(0, 1));
    assertEquals(new StdPixel(10, 10, 255, 255),
            masterBright.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 10, 255, 255),
            masterBright.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 10, 255),
            masterBright.getPixelAt(1, 1));
    assertEquals(new StdPixel(10, 255, 255, 255),
            masterBright.getPixelAt(1, 2));
    assertEquals(new StdPixel(10, 10, 10, 255),
            masterBright.getPixelAt(2, 0));
    assertEquals(new StdPixel(135, 135, 135, 255),
            masterBright.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterBright.getPixelAt(2, 2));
  }

  @Test
  public void imageDarken() throws IOException {
    this.initData();
    imgpro1.adjustBrightness("master", -10, "master dark");
    PixelImage masterDark = imgpro1.getLoadedImg("master dark");

    assertEquals(new StdPixel(245, 0, 0, 255),
            masterDark.getPixelAt(0, 0));
    assertEquals(new StdPixel(0, 245, 0, 255),
            masterDark.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 0, 245, 255),
            masterDark.getPixelAt(0, 2));
    assertEquals(new StdPixel(245, 0, 245, 255),
            masterDark.getPixelAt(1, 0));
    assertEquals(new StdPixel(245, 245, 0, 255),
            masterDark.getPixelAt(1, 1));
    assertEquals(new StdPixel(0, 245, 245, 255),
            masterDark.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterDark.getPixelAt(2, 0));
    assertEquals(new StdPixel(115, 115, 115, 255),
            masterDark.getPixelAt(2, 1));
    assertEquals(new StdPixel(245, 245, 245, 255),
            masterDark.getPixelAt(2, 2));
  }


  @Test
  public void filterImage() throws IOException {
    this.initData();
    imgpro1.filterImage("master", "master blur", "blur");
    PixelImage masterBlur = imgpro1.getLoadedImg("master blur");
    assertEquals(new StdPixel(112, 48, 32, 255),
            masterBlur.getPixelAt(0, 0));
    assertEquals(new StdPixel(80, 112, 64, 255),
            masterBlur.getPixelAt(0, 1));
    assertEquals(new StdPixel(16, 80, 96, 255),
            masterBlur.getPixelAt(0, 2));
    assertEquals(new StdPixel(135, 56, 72, 255),
            masterBlur.getPixelAt(1, 0));
    assertEquals(new StdPixel(143, 159, 111, 255),
            masterBlur.getPixelAt(1, 1));
    assertEquals(new StdPixel(72, 151, 135, 255),
            masterBlur.getPixelAt(1, 2));
    assertEquals(new StdPixel(63, 32, 48, 255),
            masterBlur.getPixelAt(2, 0));
    assertEquals(new StdPixel(111, 111, 95, 255),
            masterBlur.getPixelAt(2, 1));
    assertEquals(new StdPixel(95, 127, 111, 255),
            masterBlur.getPixelAt(2, 2));

    this.initData();
    imgpro1.filterImage("master", "master sharpen", "sharpen");
    PixelImage masterSharpen = imgpro1.getLoadedImg("master sharpen");
    assertEquals(new StdPixel(255, 48, 0, 255),
            masterSharpen.getPixelAt(0, 0));
    assertEquals(new StdPixel(144, 255, 144, 255),
            masterSharpen.getPixelAt(0, 1));
    assertEquals(new StdPixel(0, 144, 239, 255),
            masterSharpen.getPixelAt(0, 2));
    assertEquals(new StdPixel(255, 95, 191, 255),
            masterSharpen.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterSharpen.getPixelAt(1, 1));
    assertEquals(new StdPixel(95, 255, 255, 255),
            masterSharpen.getPixelAt(1, 2));
    assertEquals(new StdPixel(95, 0, 0, 255),
            masterSharpen.getPixelAt(2, 0));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterSharpen.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterSharpen.getPixelAt(2, 2));

    this.initData();
    imgpro1.filterImage("master", "master greyscale", "greyscale");
    PixelImage masterGreyscale = imgpro1.getLoadedImg("master greyscale");
    assertEquals(new StdPixel(54, 54, 54, 255),
            masterGreyscale.getPixelAt(0, 0));
    assertEquals(new StdPixel(182, 182, 182, 255),
            masterGreyscale.getPixelAt(0, 1));
    assertEquals(new StdPixel(18, 18, 18, 255),
            masterGreyscale.getPixelAt(0, 2));
    assertEquals(new StdPixel(73, 73, 73, 255),
            masterGreyscale.getPixelAt(1, 0));
    assertEquals(new StdPixel(237, 237, 237, 255),
            masterGreyscale.getPixelAt(1, 1));
    assertEquals(new StdPixel(201, 201, 201, 255),
            masterGreyscale.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterGreyscale.getPixelAt(2, 0));
    assertEquals(new StdPixel(125, 125, 125, 255),
            masterGreyscale.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 255, 255),
            masterGreyscale.getPixelAt(2, 2));

    this.initData();
    imgpro1.filterImage("master", "master sepia", "sepia");
    PixelImage masterSepia = imgpro1.getLoadedImg("master sepia");
    assertEquals(new StdPixel(100, 89, 69, 255),
            masterSepia.getPixelAt(0, 0));
    assertEquals(new StdPixel(196, 175, 136, 255),
            masterSepia.getPixelAt(0, 1));
    assertEquals(new StdPixel(48, 43, 33, 255),
            masterSepia.getPixelAt(0, 2));
    assertEquals(new StdPixel(148, 132, 103, 255),
            masterSepia.getPixelAt(1, 0));
    assertEquals(new StdPixel(255, 255, 206, 255),
            masterSepia.getPixelAt(1, 1));
    assertEquals(new StdPixel(244, 218, 170, 255),
            masterSepia.getPixelAt(1, 2));
    assertEquals(new StdPixel(0, 0, 0, 255),
            masterSepia.getPixelAt(2, 0));
    assertEquals(new StdPixel(169, 150, 117, 255),
            masterSepia.getPixelAt(2, 1));
    assertEquals(new StdPixel(255, 255, 239, 255),
            masterSepia.getPixelAt(2, 2));

  }
}