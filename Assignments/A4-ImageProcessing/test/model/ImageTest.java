package model;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static controller.ImageUtil.readPPM;
import static controller.ImageUtil.writePPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * tests for the ImageImpl.
 */
public class ImageTest {
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

  @Test(expected = IllegalArgumentException.class)
  public void testWidthNegative() {
    ImageImpl image = new ImageImpl(-10, 20, 255, pixelArray);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeightNegative() {
    ImageImpl image = new ImageImpl(10, -20, 255, pixelArray);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBothNegative() {
    ImageImpl image = new ImageImpl(-10, -20, 255, pixelArray);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxValueNegative() {
    ImageImpl image = new ImageImpl(10, 20, -255, pixelArray);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelArrayNull() {
    ImageImpl image = new ImageImpl(2, 2, 255, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnePixelNull() {
    ImageImpl image = new ImageImpl(2, 2, 255, pixelArray2);
  }

  // 2x2: Image Tests

  @Test
  public void testTotalPixels2x2() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(4, fourByFour.totalPixels());
  }

  @Test
  public void testGetWidth2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(2, fourByFour.getWidth());

  }

  @Test
  public void testGetHeight2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(2, fourByFour.getHeight());
  }

  @Test
  public void testGetMaxValue2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(255, fourByFour.getMaxValue());
  }

  @Test
  public void testGetPixelAt() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(pixel1, fourByFour.getPixelAt(0, 0));
    assertEquals(pixel2, fourByFour.getPixelAt(0, 1));
    assertEquals(pixel3, fourByFour.getPixelAt(1, 0));
    assertEquals(pixel4, fourByFour.getPixelAt(1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    fourByFour.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    fourByFour.getPixelAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot3() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    fourByFour.getPixelAt(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot4() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    fourByFour.getPixelAt(0, 7);
  }

  // 2x2: IImageProcessCommandsTests

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageCommand() {
    ImageImpl nullImage = null;
    IImageProcessCommand redGreyscaleCommand = new GreyscaleImageProcessCommand("red-component");
    ImageImpl redImage1 = redGreyscaleCommand.run(nullImage);
  }

  @Test
  public void testGreyscaleRed2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand redGreyscaleCommand =
            new GreyscaleImageProcessCommand("red-component");
    ImageImpl redImage1 = redGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(10, redImage1.getPixelAt(0, 0).getRed());
    assertEquals(10, redImage1.getPixelAt(0, 0).getGreen());
    assertEquals(10, redImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, redImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, redImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, redImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(7, redImage1.getPixelAt(1, 0).getRed());
    assertEquals(7, redImage1.getPixelAt(1, 0).getGreen());
    assertEquals(7, redImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, redImage1.getPixelAt(1, 1).getRed());
    assertEquals(55, redImage1.getPixelAt(1, 1).getGreen());
    assertEquals(55, redImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGreyscaleBlue2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand blueGreyscaleCommand =
            new GreyscaleImageProcessCommand("blue-component");
    ImageImpl blueImage1 = blueGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(33, blueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(3, blueImage1.getPixelAt(0, 1).getRed());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, blueImage1.getPixelAt(1, 0).getRed());
    assertEquals(90, blueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, blueImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(66, blueImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, blueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(66, blueImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGreyscaleGreen2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand greenGreyscaleCommand =
            new GreyscaleImageProcessCommand("green-component");
    ImageImpl greenImage1 = greenGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(20, greenImage1.getPixelAt(0, 0).getRed());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getGreen());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(66, greenImage1.getPixelAt(0, 1).getRed());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getGreen());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, greenImage1.getPixelAt(1, 0).getRed());
    assertEquals(87, greenImage1.getPixelAt(1, 0).getGreen());
    assertEquals(87, greenImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(44, greenImage1.getPixelAt(1, 1).getRed());
    assertEquals(44, greenImage1.getPixelAt(1, 1).getGreen());
    assertEquals(44, greenImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGreyscaleValue2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand valueGreyscaleCommand =
            new GreyscaleImageProcessCommand("value-component");
    ImageImpl valueImage1 = valueGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(33, valueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, valueImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, valueImage1.getPixelAt(1, 0).getRed());
    assertEquals(90, valueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, valueImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(66, valueImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, valueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(66, valueImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGreyscaleIntensity2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand intensityGreyscaleCommand =
            new GreyscaleImageProcessCommand("intensity-component");
    ImageImpl intensityImage1 = intensityGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getRed());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getGreen());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getRed());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getGreen());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getRed());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getGreen());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getRed());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getGreen());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGreyscaleLuma2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand lumaGreyscaleCommand =
            new GreyscaleImageProcessCommand("luma-component");
    ImageImpl lumaImage1 = lumaGreyscaleCommand.run(fourByFour);

    // pixel 1
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getRed());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getRed());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getRed());
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getRed());
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testSepia2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand sepiaCommand =
            new SepiaImageProcessCommand();
    ImageImpl sepiaImage1 = sepiaCommand.run(fourByFour);

    // pixel 1
    assertEquals(26, sepiaImage1.getPixelAt(0, 0).getRed());
    assertEquals(23, sepiaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(18, sepiaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(100, sepiaImage1.getPixelAt(0, 1).getRed());
    assertEquals(89, sepiaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(70, sepiaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, sepiaImage1.getPixelAt(1, 0).getRed());
    assertEquals(77, sepiaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(60, sepiaImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(68, sepiaImage1.getPixelAt(1, 1).getRed());
    assertEquals(60, sepiaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(47, sepiaImage1.getPixelAt(1, 1).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageCommand2() {
    ImageImpl nullImage = null;
    IImageProcessCommand brightenCommand = new BrightenImageProcessCommand(10);
    ImageImpl redImage1 = brightenCommand.run(nullImage);
  }

  @Test
  public void testBrighten102x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten10Command = new BrightenImageProcessCommand(10);
    ImageImpl brighten10Image1 = brighten10Command.run(fourByFour);

    // pixel 1
    assertEquals(20, brighten10Image1.getPixelAt(0, 0).getRed());
    assertEquals(30, brighten10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(43, brighten10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(135, brighten10Image1.getPixelAt(0, 1).getRed());
    assertEquals(76, brighten10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(13, brighten10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(17, brighten10Image1.getPixelAt(1, 0).getRed());
    assertEquals(97, brighten10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(100, brighten10Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(65, brighten10Image1.getPixelAt(1, 1).getRed());
    assertEquals(54, brighten10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(76, brighten10Image1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testBrightenNeg102x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brightenNeg10Command = new BrightenImageProcessCommand(-10);
    ImageImpl brightenNeg10Image1 = brightenNeg10Command.run(fourByFour);

    // pixel 1
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 0).getRed());
    assertEquals(10, brightenNeg10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(23, brightenNeg10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(115, brightenNeg10Image1.getPixelAt(0, 1).getRed());
    assertEquals(56, brightenNeg10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(0, brightenNeg10Image1.getPixelAt(1, 0).getRed());
    assertEquals(77, brightenNeg10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(80, brightenNeg10Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(45, brightenNeg10Image1.getPixelAt(1, 1).getRed());
    assertEquals(34, brightenNeg10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(56, brightenNeg10Image1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testBrighten2002x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten200Command = new BrightenImageProcessCommand(200);
    ImageImpl brighten200Image1 = brighten200Command.run(fourByFour);

    // pixel 1
    assertEquals(210, brighten200Image1.getPixelAt(0, 0).getRed());
    assertEquals(220, brighten200Image1.getPixelAt(0, 0).getGreen());
    assertEquals(233, brighten200Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(255, brighten200Image1.getPixelAt(0, 1).getRed());
    assertEquals(255, brighten200Image1.getPixelAt(0, 1).getGreen());
    assertEquals(203, brighten200Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(207, brighten200Image1.getPixelAt(1, 0).getRed());
    assertEquals(255, brighten200Image1.getPixelAt(1, 0).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(255, brighten200Image1.getPixelAt(1, 1).getRed());
    assertEquals(244, brighten200Image1.getPixelAt(1, 1).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(1, 1).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageCommand3() {
    ImageImpl nullImage = null;
    IImageProcessCommand flipCommand = new FlipImageProcessCommand(true);
    ImageImpl flipImage1 = flipCommand.run(nullImage);
  }

  @Test
  public void testFlipHorizontal2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IllegalArgumentException | IOException e) {
      fail();
    }

    IImageProcessCommand flipHCommand = new FlipImageProcessCommand(true);
    ImageImpl flipHImage1 = flipHCommand.run(fourByFour);

    // pixel 1
    assertEquals(125, flipHImage1.getPixelAt(0, 0).getRed());
    assertEquals(66, flipHImage1.getPixelAt(0, 0).getGreen());
    assertEquals(3, flipHImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(10, flipHImage1.getPixelAt(0, 1).getRed());
    assertEquals(20, flipHImage1.getPixelAt(0, 1).getGreen());
    assertEquals(33, flipHImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(55, flipHImage1.getPixelAt(1, 0).getRed());
    assertEquals(44, flipHImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, flipHImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(7, flipHImage1.getPixelAt(1, 1).getRed());
    assertEquals(87, flipHImage1.getPixelAt(1, 1).getGreen());
    assertEquals(90, flipHImage1.getPixelAt(1, 1).getBlue());

    // vertical flip after horizontal flip
    IImageProcessCommand flipVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipHVImage1 = flipVCommand.run(flipHImage1);

    // pixel 1
    assertEquals(55, flipHVImage1.getPixelAt(0, 0).getRed());
    assertEquals(44, flipHVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(66, flipHVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(7, flipHVImage1.getPixelAt(0, 1).getRed());
    assertEquals(87, flipHVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(90, flipHVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(125, flipHVImage1.getPixelAt(1, 0).getRed());
    assertEquals(66, flipHVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(3, flipHVImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(10, flipHVImage1.getPixelAt(1, 1).getRed());
    assertEquals(20, flipHVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(33, flipHVImage1.getPixelAt(1, 1).getBlue());

  }

  @Test
  public void testFlipVertical2x2() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand flipVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipVImage1 = flipVCommand.run(fourByFour);

    // pixel 1
    assertEquals(7, flipVImage1.getPixelAt(0, 0).getRed());
    assertEquals(87, flipVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(90, flipVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(55, flipVImage1.getPixelAt(0, 1).getRed());
    assertEquals(44, flipVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(66, flipVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(10, flipVImage1.getPixelAt(1, 0).getRed());
    assertEquals(20, flipVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(33, flipVImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(125, flipVImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, flipVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(3, flipVImage1.getPixelAt(1, 1).getBlue());

    // horizontal flip after vertical flip
    IImageProcessCommand flipHVCommand = new FlipImageProcessCommand(true);
    ImageImpl flipHVImage1 = flipHVCommand.run(flipVImage1);

    //pixel 1
    assertEquals(55, flipHVImage1.getPixelAt(0, 0).getRed());
    assertEquals(44, flipHVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(66, flipHVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(7, flipHVImage1.getPixelAt(0, 1).getRed());
    assertEquals(87, flipHVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(90, flipHVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(125, flipHVImage1.getPixelAt(1, 0).getRed());
    assertEquals(66, flipHVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(3, flipHVImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(10, flipHVImage1.getPixelAt(1, 1).getRed());
    assertEquals(20, flipHVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(33, flipHVImage1.getPixelAt(1, 1).getBlue());
  }


  @Test
  public void testBlur2x2ProcessComamnd() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand blurCommand = new BlurImageProcessCommand();
    ImageImpl blurredImage1 = blurCommand.run(fourByFour);

    //pixel 1
    assertEquals(22, blurredImage1.getPixelAt(0, 0).getRed());
    assertEquals(26, blurredImage1.getPixelAt(0, 0).getGreen());
    assertEquals(24, blurredImage1.getPixelAt(0, 0).getBlue());
//    // pixel 2
    assertEquals(39, blurredImage1.getPixelAt(0, 1).getRed());
    assertEquals(29, blurredImage1.getPixelAt(0, 1).getGreen());
    assertEquals(18, blurredImage1.getPixelAt(0, 1).getBlue());
//    // pixel 3
    assertEquals(17, blurredImage1.getPixelAt(1, 0).getRed());
    assertEquals(33, blurredImage1.getPixelAt(1, 0).getGreen());
    assertEquals(35, blurredImage1.getPixelAt(1, 0).getBlue());
//    //pixel 4
    assertEquals(30, blurredImage1.getPixelAt(1, 1).getRed());
    assertEquals(31, blurredImage1.getPixelAt(1, 1).getGreen());
    assertEquals(30, blurredImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testSharpen2x2ProcessCommand() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand sharpenCommand = new SharpenImageProcessCommand();
    ImageImpl sharpenImage1 = sharpenCommand.run(fourByFour);

    //pixel 1
    assertEquals(56, sharpenImage1.getPixelAt(0, 0).getRed());
    assertEquals(69, sharpenImage1.getPixelAt(0, 0).getGreen());
    assertEquals(72, sharpenImage1.getPixelAt(0, 0).getBlue());
//    // pixel 2
    assertEquals(143, sharpenImage1.getPixelAt(0, 1).getRed());
    assertEquals(103, sharpenImage1.getPixelAt(0, 1).getGreen());
    assertEquals(50, sharpenImage1.getPixelAt(0, 1).getBlue());
//    // pixel 3
    assertEquals(54, sharpenImage1.getPixelAt(1, 0).getRed());
    assertEquals(119, sharpenImage1.getPixelAt(1, 0).getGreen());
    assertEquals(115, sharpenImage1.getPixelAt(1, 0).getBlue());
//    //pixel 4
    assertEquals(90, sharpenImage1.getPixelAt(1, 1).getRed());
    assertEquals(87, sharpenImage1.getPixelAt(1, 1).getGreen());
    assertEquals(97, sharpenImage1.getPixelAt(1, 1).getBlue());
  }

  // 2x3 : Image Tests

  @Test
  public void testTotalPixels() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(6, twoByThree.totalPixels());
  }

  @Test
  public void testGetWidth2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(2, twoByThree.getWidth());
  }

  @Test
  public void testGetHeight2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(3, twoByThree.getHeight());
  }

  @Test
  public void testMaxValue2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(255, twoByThree.getMaxValue());
  }

  @Test
  public void testGetPixelAt2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(pixel1, twoByThree.getPixelAt(0, 0));
    assertEquals(pixel2, twoByThree.getPixelAt(0, 1));
    assertEquals(pixel3, twoByThree.getPixelAt(1, 0));
    assertEquals(pixel4, twoByThree.getPixelAt(1, 1));
    assertEquals(pixel6, twoByThree.getPixelAt(2, 0));
    assertEquals(pixel7, twoByThree.getPixelAt(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    twoByThree.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot2x32() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    twoByThree.getPixelAt(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot2x33() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    twoByThree.getPixelAt(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtInvalidSlot2x34() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    twoByThree.getPixelAt(0, 7);
  }

  // 2x2: IImageProcessCommandsTests

  @Test
  public void testGreyscaleRed2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand redGreyscaleCommand =
            new GreyscaleImageProcessCommand("red-component");
    ImageImpl redImage1 = redGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(10, redImage1.getPixelAt(0, 0).getRed());
    assertEquals(10, redImage1.getPixelAt(0, 0).getGreen());
    assertEquals(10, redImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, redImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, redImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, redImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(7, redImage1.getPixelAt(1, 0).getRed());
    assertEquals(7, redImage1.getPixelAt(1, 0).getGreen());
    assertEquals(7, redImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, redImage1.getPixelAt(1, 1).getRed());
    assertEquals(55, redImage1.getPixelAt(1, 1).getGreen());
    assertEquals(55, redImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(28, redImage1.getPixelAt(2, 0).getRed());
    assertEquals(28, redImage1.getPixelAt(2, 0).getGreen());
    assertEquals(28, redImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(17, redImage1.getPixelAt(2, 1).getRed());
    assertEquals(17, redImage1.getPixelAt(2, 1).getGreen());
    assertEquals(17, redImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscaleBlue2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand blueGreyscaleCommand =
            new GreyscaleImageProcessCommand("blue-component");
    ImageImpl blueImage1 = blueGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(33, blueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(3, blueImage1.getPixelAt(0, 1).getRed());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, blueImage1.getPixelAt(1, 0).getRed());
    assertEquals(90, blueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, blueImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(66, blueImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, blueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(66, blueImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(244, blueImage1.getPixelAt(2, 0).getRed());
    assertEquals(244, blueImage1.getPixelAt(2, 0).getGreen());
    assertEquals(244, blueImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(255, blueImage1.getPixelAt(2, 1).getRed());
    assertEquals(255, blueImage1.getPixelAt(2, 1).getGreen());
    assertEquals(255, blueImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscaleGreen2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand greenGreyscaleCommand =
            new GreyscaleImageProcessCommand("green-component");
    ImageImpl greenImage1 = greenGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(20, greenImage1.getPixelAt(0, 0).getRed());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getGreen());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(66, greenImage1.getPixelAt(0, 1).getRed());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getGreen());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, greenImage1.getPixelAt(1, 0).getRed());
    assertEquals(87, greenImage1.getPixelAt(1, 0).getGreen());
    assertEquals(87, greenImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(44, greenImage1.getPixelAt(1, 1).getRed());
    assertEquals(44, greenImage1.getPixelAt(1, 1).getGreen());
    assertEquals(44, greenImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(0, greenImage1.getPixelAt(2, 0).getRed());
    assertEquals(0, greenImage1.getPixelAt(2, 0).getGreen());
    assertEquals(0, greenImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(99, greenImage1.getPixelAt(2, 1).getRed());
    assertEquals(99, greenImage1.getPixelAt(2, 1).getGreen());
    assertEquals(99, greenImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscaleValue2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand valueGreyscaleCommand =
            new GreyscaleImageProcessCommand("value-component");
    ImageImpl valueImage1 = valueGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(33, valueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, valueImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, valueImage1.getPixelAt(1, 0).getRed());
    assertEquals(90, valueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, valueImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(66, valueImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, valueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(66, valueImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(244, valueImage1.getPixelAt(2, 0).getRed());
    assertEquals(244, valueImage1.getPixelAt(2, 0).getGreen());
    assertEquals(244, valueImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(255, valueImage1.getPixelAt(2, 1).getRed());
    assertEquals(255, valueImage1.getPixelAt(2, 1).getGreen());
    assertEquals(255, valueImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscaleIntensity2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand intensityGreyscaleCommand =
            new GreyscaleImageProcessCommand("intensity-component");
    ImageImpl intensityImage1 = intensityGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getRed());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getGreen());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getRed());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getGreen());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getRed());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getGreen());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getRed());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getGreen());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(91, intensityImage1.getPixelAt(2, 0).getRed());
    assertEquals(91, intensityImage1.getPixelAt(2, 0).getGreen());
    assertEquals(91, intensityImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(124, intensityImage1.getPixelAt(2, 1).getRed());
    assertEquals(124, intensityImage1.getPixelAt(2, 1).getGreen());
    assertEquals(124, intensityImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscaleLuma2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand lumaGreyscaleCommand =
            new GreyscaleImageProcessCommand("luma-component");
    ImageImpl lumaImage1 = lumaGreyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getRed());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getRed());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getRed());
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(70, lumaImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getRed());
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(48, lumaImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(24, lumaImage1.getPixelAt(2, 0).getRed());
    assertEquals(24, lumaImage1.getPixelAt(2, 0).getGreen());
    assertEquals(24, lumaImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(93, lumaImage1.getPixelAt(2, 1).getRed());
    assertEquals(93, lumaImage1.getPixelAt(2, 1).getGreen());
    assertEquals(93, lumaImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testGreyscale2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand greyscaleCommand =
            new GreyscaleImageProcessCommand("greyscale");
    ImageImpl greyscaleImage1 = greyscaleCommand.run(twoByThree);

    // pixel 1
    assertEquals(19, greyscaleImage1.getPixelAt(0, 0).getRed());
    assertEquals(19, greyscaleImage1.getPixelAt(0, 0).getGreen());
    assertEquals(19, greyscaleImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(74, greyscaleImage1.getPixelAt(0, 1).getRed());
    assertEquals(74, greyscaleImage1.getPixelAt(0, 1).getGreen());
    assertEquals(74, greyscaleImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(70, greyscaleImage1.getPixelAt(1, 0).getRed());
    assertEquals(70, greyscaleImage1.getPixelAt(1, 0).getGreen());
    assertEquals(70, greyscaleImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(48, greyscaleImage1.getPixelAt(1, 1).getRed());
    assertEquals(48, greyscaleImage1.getPixelAt(1, 1).getGreen());
    assertEquals(48, greyscaleImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(24, greyscaleImage1.getPixelAt(2, 0).getRed());
    assertEquals(24, greyscaleImage1.getPixelAt(2, 0).getGreen());
    assertEquals(24, greyscaleImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(93, greyscaleImage1.getPixelAt(2, 1).getRed());
    assertEquals(93, greyscaleImage1.getPixelAt(2, 1).getGreen());
    assertEquals(93, greyscaleImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testBrighten102x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten10Command = new BrightenImageProcessCommand(10);
    ImageImpl brighten10Image1 = brighten10Command.run(twoByThree);

    // pixel 1
    assertEquals(20, brighten10Image1.getPixelAt(0, 0).getRed());
    assertEquals(30, brighten10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(43, brighten10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(135, brighten10Image1.getPixelAt(0, 1).getRed());
    assertEquals(76, brighten10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(13, brighten10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(17, brighten10Image1.getPixelAt(1, 0).getRed());
    assertEquals(97, brighten10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(100, brighten10Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(65, brighten10Image1.getPixelAt(1, 1).getRed());
    assertEquals(54, brighten10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(76, brighten10Image1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(38, brighten10Image1.getPixelAt(2, 0).getRed());
    assertEquals(10, brighten10Image1.getPixelAt(2, 0).getGreen());
    assertEquals(254, brighten10Image1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(27, brighten10Image1.getPixelAt(2, 1).getRed());
    assertEquals(109, brighten10Image1.getPixelAt(2, 1).getGreen());
    assertEquals(255, brighten10Image1.getPixelAt(2, 1).getBlue());

  }

  @Test
  public void testBrightenNeg102x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brightenNeg10Command = new BrightenImageProcessCommand(-10);
    ImageImpl brightenNeg10Image1 = brightenNeg10Command.run(twoByThree);

    // pixel 1
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 0).getRed());
    assertEquals(10, brightenNeg10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(23, brightenNeg10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(115, brightenNeg10Image1.getPixelAt(0, 1).getRed());
    assertEquals(56, brightenNeg10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(0, brightenNeg10Image1.getPixelAt(1, 0).getRed());
    assertEquals(77, brightenNeg10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(80, brightenNeg10Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(45, brightenNeg10Image1.getPixelAt(1, 1).getRed());
    assertEquals(34, brightenNeg10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(56, brightenNeg10Image1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(18, brightenNeg10Image1.getPixelAt(2, 0).getRed());
    assertEquals(0, brightenNeg10Image1.getPixelAt(2, 0).getGreen());
    assertEquals(234, brightenNeg10Image1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(7, brightenNeg10Image1.getPixelAt(2, 1).getRed());
    assertEquals(89, brightenNeg10Image1.getPixelAt(2, 1).getGreen());
    assertEquals(245, brightenNeg10Image1.getPixelAt(2, 1).getBlue());

  }

  @Test
  public void testBrighten2002x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten200Command = new BrightenImageProcessCommand(200);
    ImageImpl brighten200Image1 = brighten200Command.run(twoByThree);

    // pixel 1
    assertEquals(210, brighten200Image1.getPixelAt(0, 0).getRed());
    assertEquals(220, brighten200Image1.getPixelAt(0, 0).getGreen());
    assertEquals(233, brighten200Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(255, brighten200Image1.getPixelAt(0, 1).getRed());
    assertEquals(255, brighten200Image1.getPixelAt(0, 1).getGreen());
    assertEquals(203, brighten200Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(207, brighten200Image1.getPixelAt(1, 0).getRed());
    assertEquals(255, brighten200Image1.getPixelAt(1, 0).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(255, brighten200Image1.getPixelAt(1, 1).getRed());
    assertEquals(244, brighten200Image1.getPixelAt(1, 1).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(228, brighten200Image1.getPixelAt(2, 0).getRed());
    assertEquals(200, brighten200Image1.getPixelAt(2, 0).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(217, brighten200Image1.getPixelAt(2, 1).getRed());
    assertEquals(255, brighten200Image1.getPixelAt(2, 1).getGreen());
    assertEquals(255, brighten200Image1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testFlipHorizontal2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand flipHCommand = new FlipImageProcessCommand(true);
    ImageImpl flipHImage1 = flipHCommand.run(twoByThree);

    // pixel 1
    assertEquals(125, flipHImage1.getPixelAt(0, 0).getRed());
    assertEquals(66, flipHImage1.getPixelAt(0, 0).getGreen());
    assertEquals(3, flipHImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(10, flipHImage1.getPixelAt(0, 1).getRed());
    assertEquals(20, flipHImage1.getPixelAt(0, 1).getGreen());
    assertEquals(33, flipHImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(55, flipHImage1.getPixelAt(1, 0).getRed());
    assertEquals(44, flipHImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, flipHImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(7, flipHImage1.getPixelAt(1, 1).getRed());
    assertEquals(87, flipHImage1.getPixelAt(1, 1).getGreen());
    assertEquals(90, flipHImage1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(17, flipHImage1.getPixelAt(2, 0).getRed());
    assertEquals(99, flipHImage1.getPixelAt(2, 0).getGreen());
    assertEquals(255, flipHImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(28, flipHImage1.getPixelAt(2, 1).getRed());
    assertEquals(0, flipHImage1.getPixelAt(2, 1).getGreen());
    assertEquals(244, flipHImage1.getPixelAt(2, 1).getBlue());

    IImageProcessCommand flipHVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipHVImage1 = flipHVCommand.run(flipHImage1);

    // pixel 1
    assertEquals(125, flipHVImage1.getPixelAt(2, 0).getRed());
    assertEquals(66, flipHVImage1.getPixelAt(2, 0).getGreen());
    assertEquals(3, flipHVImage1.getPixelAt(2, 0).getBlue());
    // pixel 2
    assertEquals(10, flipHVImage1.getPixelAt(2, 1).getRed());
    assertEquals(20, flipHVImage1.getPixelAt(2, 1).getGreen());
    assertEquals(33, flipHVImage1.getPixelAt(2, 1).getBlue());
    // pixel 3
    assertEquals(55, flipHVImage1.getPixelAt(1, 0).getRed());
    assertEquals(44, flipHVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, flipHVImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(7, flipHVImage1.getPixelAt(1, 1).getRed());
    assertEquals(87, flipHVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(90, flipHVImage1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(17, flipHVImage1.getPixelAt(0, 0).getRed());
    assertEquals(99, flipHVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(255, flipHVImage1.getPixelAt(0, 0).getBlue());
    //pixel 6
    assertEquals(28, flipHVImage1.getPixelAt(0, 1).getRed());
    assertEquals(0, flipHVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(244, flipHVImage1.getPixelAt(0, 1).getBlue());
  }

  @Test
  public void testVerticalFlip2x3() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand flipVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipVImage1 = flipVCommand.run(twoByThree);

    // pixel 1
    assertEquals(28, flipVImage1.getPixelAt(0, 0).getRed());
    assertEquals(0, flipVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(244, flipVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(17, flipVImage1.getPixelAt(0, 1).getRed());
    assertEquals(99, flipVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(255, flipVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(7, flipVImage1.getPixelAt(1, 0).getRed());
    assertEquals(87, flipVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, flipVImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, flipVImage1.getPixelAt(1, 1).getRed());
    assertEquals(44, flipVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(66, flipVImage1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(10, flipVImage1.getPixelAt(2, 0).getRed());
    assertEquals(20, flipVImage1.getPixelAt(2, 0).getGreen());
    assertEquals(33, flipVImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(125, flipVImage1.getPixelAt(2, 1).getRed());
    assertEquals(66, flipVImage1.getPixelAt(2, 1).getGreen());
    assertEquals(3, flipVImage1.getPixelAt(2, 1).getBlue());

    IImageProcessCommand flipVHCommand = new FlipImageProcessCommand(true);
    ImageImpl flipVHImage1 = flipVHCommand.run(flipVImage1);

    // pixel 1
    assertEquals(125, flipVHImage1.getPixelAt(2, 0).getRed());
    assertEquals(66, flipVHImage1.getPixelAt(2, 0).getGreen());
    assertEquals(3, flipVHImage1.getPixelAt(2, 0).getBlue());
    // pixel 2
    assertEquals(10, flipVHImage1.getPixelAt(2, 1).getRed());
    assertEquals(20, flipVHImage1.getPixelAt(2, 1).getGreen());
    assertEquals(33, flipVHImage1.getPixelAt(2, 1).getBlue());
    // pixel 3
    assertEquals(55, flipVHImage1.getPixelAt(1, 0).getRed());
    assertEquals(44, flipVHImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, flipVHImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(7, flipVHImage1.getPixelAt(1, 1).getRed());
    assertEquals(87, flipVHImage1.getPixelAt(1, 1).getGreen());
    assertEquals(90, flipVHImage1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(17, flipVHImage1.getPixelAt(0, 0).getRed());
    assertEquals(99, flipVHImage1.getPixelAt(0, 0).getGreen());
    assertEquals(255, flipVHImage1.getPixelAt(0, 0).getBlue());
    //pixel 6
    assertEquals(28, flipVHImage1.getPixelAt(0, 1).getRed());
    assertEquals(0, flipVHImage1.getPixelAt(0, 1).getGreen());
    assertEquals(244, flipVHImage1.getPixelAt(0, 1).getBlue());
  }

  @Test
  public void testSepia2x3ProcessCommand() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand sepiaCommand = new SepiaImageProcessCommand();
    ImageImpl sepiaImage1 = sepiaCommand.run(twoByThree);

    // pixel 1
    assertEquals(26, sepiaImage1.getPixelAt(0, 0).getRed());
    assertEquals(23, sepiaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(18, sepiaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(100, sepiaImage1.getPixelAt(0, 1).getRed());
    assertEquals(89, sepiaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(70, sepiaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, sepiaImage1.getPixelAt(1, 0).getRed());
    assertEquals(77, sepiaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(60, sepiaImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(68, sepiaImage1.getPixelAt(1, 1).getRed());
    assertEquals(60, sepiaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(47, sepiaImage1.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(57, sepiaImage1.getPixelAt(2, 0).getRed());
    assertEquals(51, sepiaImage1.getPixelAt(2, 0).getGreen());
    assertEquals(40, sepiaImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(131, sepiaImage1.getPixelAt(2, 1).getRed());
    assertEquals(117, sepiaImage1.getPixelAt(2, 1).getGreen());
    assertEquals(91, sepiaImage1.getPixelAt(2, 1).getBlue());
  }

  // 3x2: IImageProcessCommandsTests

  @Test
  public void testTotalPixels3x2() {

    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(6, threeByTwo.totalPixels());
  }

  // Image 3 Tests: 3x2
  @Test
  public void testGetWidth3x2() {

    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(3, threeByTwo.getWidth());
  }

  // Image 3 Tests: 3x2
  @Test
  public void testGetHeight3x2() {

    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(2, threeByTwo.getHeight());
  }

  @Test
  public void testGetMaxValue3x2() {

    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(200, threeByTwo.getMaxValue());
  }

  @Test
  public void testGetPixelAt3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(new Pixel(10, 20, 33, 200),
            threeByTwo.getPixelAt(0, 0));
    assertEquals(new Pixel(125, 66, 3, 200),
            threeByTwo.getPixelAt(0, 1));
    assertEquals(new Pixel(7, 87, 90, 200),
            threeByTwo.getPixelAt(0, 2));
    assertEquals(new Pixel(55, 44, 66, 200),
            threeByTwo.getPixelAt(1, 0));
    assertEquals(new Pixel(28, 0, 200, 200),
            threeByTwo.getPixelAt(1, 1));
    assertEquals(new Pixel(17, 99, 200, 200),
            threeByTwo.getPixelAt(1, 2));
  }

  @Test
  public void testGreyScaleRed3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand redGreyscaleCommand =
            new GreyscaleImageProcessCommand("red-component");
    ImageImpl redImage1 = redGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(10, redImage1.getPixelAt(0, 0).getRed());
    assertEquals(10, redImage1.getPixelAt(0, 0).getGreen());
    assertEquals(10, redImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, redImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, redImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, redImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(7, redImage1.getPixelAt(0, 2).getRed());
    assertEquals(7, redImage1.getPixelAt(0, 2).getGreen());
    assertEquals(7, redImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(55, redImage1.getPixelAt(1, 0).getRed());
    assertEquals(55, redImage1.getPixelAt(1, 0).getGreen());
    assertEquals(55, redImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(28, redImage1.getPixelAt(1, 1).getRed());
    assertEquals(28, redImage1.getPixelAt(1, 1).getGreen());
    assertEquals(28, redImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(17, redImage1.getPixelAt(1, 2).getRed());
    assertEquals(17, redImage1.getPixelAt(1, 2).getGreen());
    assertEquals(17, redImage1.getPixelAt(1, 2).getBlue());


  }

  @Test
  public void testGreyScaleBlue3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand blueGreyscaleCommand =
            new GreyscaleImageProcessCommand("blue-component");
    ImageImpl blueImage1 = blueGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(33, blueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, blueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(3, blueImage1.getPixelAt(0, 1).getRed());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(3, blueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, blueImage1.getPixelAt(0, 2).getRed());
    assertEquals(90, blueImage1.getPixelAt(0, 2).getGreen());
    assertEquals(90, blueImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(66, blueImage1.getPixelAt(1, 0).getRed());
    assertEquals(66, blueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, blueImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(200, blueImage1.getPixelAt(1, 1).getRed());
    assertEquals(200, blueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(200, blueImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(200, blueImage1.getPixelAt(1, 2).getRed());
    assertEquals(200, blueImage1.getPixelAt(1, 2).getGreen());
    assertEquals(200, blueImage1.getPixelAt(1, 2).getBlue());

  }

  @Test
  public void testGreyScaleGreen3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand greenGreyscaleCommand =
            new GreyscaleImageProcessCommand("green-component");
    ImageImpl greenImage1 = greenGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(20, greenImage1.getPixelAt(0, 0).getRed());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getGreen());
    assertEquals(20, greenImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(66, greenImage1.getPixelAt(0, 1).getRed());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getGreen());
    assertEquals(66, greenImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, greenImage1.getPixelAt(0, 2).getRed());
    assertEquals(87, greenImage1.getPixelAt(0, 2).getGreen());
    assertEquals(87, greenImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(44, greenImage1.getPixelAt(1, 0).getRed());
    assertEquals(44, greenImage1.getPixelAt(1, 0).getGreen());
    assertEquals(44, greenImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(0, greenImage1.getPixelAt(1, 1).getRed());
    assertEquals(0, greenImage1.getPixelAt(1, 1).getGreen());
    assertEquals(0, greenImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(99, greenImage1.getPixelAt(1, 2).getRed());
    assertEquals(99, greenImage1.getPixelAt(1, 2).getGreen());
    assertEquals(99, greenImage1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testGreyScaleValue3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException | IllegalArgumentException e) {
      fail();
    }

    IImageProcessCommand valueGreyscaleCommand =
            new GreyscaleImageProcessCommand("value-component");
    ImageImpl valueImage1 = valueGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(33, valueImage1.getPixelAt(0, 0).getRed());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getGreen());
    assertEquals(33, valueImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, valueImage1.getPixelAt(0, 1).getRed());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getGreen());
    assertEquals(125, valueImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(90, valueImage1.getPixelAt(0, 2).getRed());
    assertEquals(90, valueImage1.getPixelAt(0, 2).getGreen());
    assertEquals(90, valueImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(66, valueImage1.getPixelAt(1, 0).getRed());
    assertEquals(66, valueImage1.getPixelAt(1, 0).getGreen());
    assertEquals(66, valueImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(200, valueImage1.getPixelAt(1, 1).getRed());
    assertEquals(200, valueImage1.getPixelAt(1, 1).getGreen());
    assertEquals(200, valueImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(200, valueImage1.getPixelAt(1, 2).getRed());
    assertEquals(200, valueImage1.getPixelAt(1, 2).getGreen());
    assertEquals(200, valueImage1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testGreyScaleIntensity3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand intensityGreyscaleCommand =
            new GreyscaleImageProcessCommand("intensity-component");
    ImageImpl intensityImage1 = intensityGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getRed());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getGreen());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getRed());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getGreen());
    assertEquals(65, intensityImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(61, intensityImage1.getPixelAt(0, 2).getRed());
    assertEquals(61, intensityImage1.getPixelAt(0, 2).getGreen());
    assertEquals(61, intensityImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(55, intensityImage1.getPixelAt(1, 0).getRed());
    assertEquals(55, intensityImage1.getPixelAt(1, 0).getGreen());
    assertEquals(55, intensityImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(76, intensityImage1.getPixelAt(1, 1).getRed());
    assertEquals(76, intensityImage1.getPixelAt(1, 1).getGreen());
    assertEquals(76, intensityImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(105, intensityImage1.getPixelAt(1, 2).getRed());
    assertEquals(105, intensityImage1.getPixelAt(1, 2).getGreen());
    assertEquals(105, intensityImage1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testGreyScaleLuma3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand lumaGreyscaleCommand =
            new GreyscaleImageProcessCommand("luma-component");
    ImageImpl lumaImage1 = lumaGreyscaleCommand.run(threeByTwo);

    // pixel 1
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getRed());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(19, lumaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getRed());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(74, lumaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(70, lumaImage1.getPixelAt(0, 2).getRed());
    assertEquals(70, lumaImage1.getPixelAt(0, 2).getGreen());
    assertEquals(70, lumaImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(48, lumaImage1.getPixelAt(1, 0).getRed());
    assertEquals(48, lumaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(48, lumaImage1.getPixelAt(1, 0).getBlue());

    // pixel 5
    assertEquals(20, lumaImage1.getPixelAt(1, 1).getRed());
    assertEquals(20, lumaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(20, lumaImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(89, lumaImage1.getPixelAt(1, 2).getRed());
    assertEquals(89, lumaImage1.getPixelAt(1, 2).getGreen());
    assertEquals(89, lumaImage1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testSepia3x2ProcessCommand() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand sepiaCommand = new SepiaImageProcessCommand();
    ImageImpl sepiaImage1 = sepiaCommand.run(threeByTwo);

    // pixel 1
    assertEquals(26, sepiaImage1.getPixelAt(0, 0).getRed());
    assertEquals(23, sepiaImage1.getPixelAt(0, 0).getGreen());
    assertEquals(18, sepiaImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(100, sepiaImage1.getPixelAt(0, 1).getRed());
    assertEquals(89, sepiaImage1.getPixelAt(0, 1).getGreen());
    assertEquals(70, sepiaImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(87, sepiaImage1.getPixelAt(0, 2).getRed());
    assertEquals(77, sepiaImage1.getPixelAt(0, 2).getGreen());
    assertEquals(60, sepiaImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(68, sepiaImage1.getPixelAt(1, 0).getRed());
    assertEquals(60, sepiaImage1.getPixelAt(1, 0).getGreen());
    assertEquals(47, sepiaImage1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(49, sepiaImage1.getPixelAt(1, 1).getRed());
    assertEquals(43, sepiaImage1.getPixelAt(1, 1).getGreen());
    assertEquals(34, sepiaImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(121, sepiaImage1.getPixelAt(1, 2).getRed());
    assertEquals(107, sepiaImage1.getPixelAt(1, 2).getGreen());
    assertEquals(84, sepiaImage1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testBrighten103x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten10Command = new BrightenImageProcessCommand(10);
    ImageImpl brighten10Image1 = brighten10Command.run(threeByTwo);

    // pixel 1
    assertEquals(20, brighten10Image1.getPixelAt(0, 0).getRed());
    assertEquals(30, brighten10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(43, brighten10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(135, brighten10Image1.getPixelAt(0, 1).getRed());
    assertEquals(76, brighten10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(13, brighten10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(17, brighten10Image1.getPixelAt(0, 2).getRed());
    assertEquals(97, brighten10Image1.getPixelAt(0, 2).getGreen());
    assertEquals(100, brighten10Image1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(65, brighten10Image1.getPixelAt(1, 0).getRed());
    assertEquals(54, brighten10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(76, brighten10Image1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(38, brighten10Image1.getPixelAt(1, 1).getRed());
    assertEquals(10, brighten10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(200, brighten10Image1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(27, brighten10Image1.getPixelAt(1, 2).getRed());
    assertEquals(109, brighten10Image1.getPixelAt(1, 2).getGreen());
    assertEquals(200, brighten10Image1.getPixelAt(1, 2).getBlue());

  }

  @Test
  public void testBrightenNeg103x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brightenNeg10Command = new BrightenImageProcessCommand(-10);
    ImageImpl brightenNeg10Image1 = brightenNeg10Command.run(threeByTwo);

    // pixel 1
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 0).getRed());
    assertEquals(10, brightenNeg10Image1.getPixelAt(0, 0).getGreen());
    assertEquals(23, brightenNeg10Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(115, brightenNeg10Image1.getPixelAt(0, 1).getRed());
    assertEquals(56, brightenNeg10Image1.getPixelAt(0, 1).getGreen());
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(0, brightenNeg10Image1.getPixelAt(0, 2).getRed());
    assertEquals(77, brightenNeg10Image1.getPixelAt(0, 2).getGreen());
    assertEquals(80, brightenNeg10Image1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(45, brightenNeg10Image1.getPixelAt(1, 0).getRed());
    assertEquals(34, brightenNeg10Image1.getPixelAt(1, 0).getGreen());
    assertEquals(56, brightenNeg10Image1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(18, brightenNeg10Image1.getPixelAt(1, 1).getRed());
    assertEquals(0, brightenNeg10Image1.getPixelAt(1, 1).getGreen());
    assertEquals(190, brightenNeg10Image1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(7, brightenNeg10Image1.getPixelAt(1, 2).getRed());
    assertEquals(89, brightenNeg10Image1.getPixelAt(1, 2).getGreen());
    assertEquals(190, brightenNeg10Image1.getPixelAt(1, 2).getBlue());

  }

  @Test
  public void testBrighten1003x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand brighten100Command = new BrightenImageProcessCommand(100);
    ImageImpl brighten100Image1 = brighten100Command.run(threeByTwo);

    // pixel 1
    assertEquals(110, brighten100Image1.getPixelAt(0, 0).getRed());
    assertEquals(120, brighten100Image1.getPixelAt(0, 0).getGreen());
    assertEquals(133, brighten100Image1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(200, brighten100Image1.getPixelAt(0, 1).getRed());
    assertEquals(166, brighten100Image1.getPixelAt(0, 1).getGreen());
    assertEquals(103, brighten100Image1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(107, brighten100Image1.getPixelAt(0, 2).getRed());
    assertEquals(187, brighten100Image1.getPixelAt(0, 2).getGreen());
    assertEquals(190, brighten100Image1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(155, brighten100Image1.getPixelAt(1, 0).getRed());
    assertEquals(144, brighten100Image1.getPixelAt(1, 0).getGreen());
    assertEquals(166, brighten100Image1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(128, brighten100Image1.getPixelAt(1, 1).getRed());
    assertEquals(100, brighten100Image1.getPixelAt(1, 1).getGreen());
    assertEquals(200, brighten100Image1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(117, brighten100Image1.getPixelAt(1, 2).getRed());
    assertEquals(199, brighten100Image1.getPixelAt(1, 2).getGreen());
    assertEquals(200, brighten100Image1.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void testFlipHorizonal3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand flipHCommand = new FlipImageProcessCommand(true);
    ImageImpl flipHImage1 = flipHCommand.run(threeByTwo);

    // pixel 1
    assertEquals(7, flipHImage1.getPixelAt(0, 0).getRed());
    assertEquals(87, flipHImage1.getPixelAt(0, 0).getGreen());
    assertEquals(90, flipHImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, flipHImage1.getPixelAt(0, 1).getRed());
    assertEquals(66, flipHImage1.getPixelAt(0, 1).getGreen());
    assertEquals(3, flipHImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(10, flipHImage1.getPixelAt(0, 2).getRed());
    assertEquals(20, flipHImage1.getPixelAt(0, 2).getGreen());
    assertEquals(33, flipHImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(17, flipHImage1.getPixelAt(1, 0).getRed());
    assertEquals(99, flipHImage1.getPixelAt(1, 0).getGreen());
    assertEquals(200, flipHImage1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(28, flipHImage1.getPixelAt(1, 1).getRed());
    assertEquals(0, flipHImage1.getPixelAt(1, 1).getGreen());
    assertEquals(200, flipHImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(55, flipHImage1.getPixelAt(1, 2).getRed());
    assertEquals(44, flipHImage1.getPixelAt(1, 2).getGreen());
    assertEquals(66, flipHImage1.getPixelAt(1, 2).getBlue());

    IImageProcessCommand flipHVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipHVImage1 = flipHVCommand.run(flipHImage1);

    // pixel 1
    assertEquals(17, flipHVImage1.getPixelAt(0, 0).getRed());
    assertEquals(99, flipHVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(200, flipHVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(28, flipHVImage1.getPixelAt(0, 1).getRed());
    assertEquals(0, flipHVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(200, flipHVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(55, flipHVImage1.getPixelAt(0, 2).getRed());
    assertEquals(44, flipHVImage1.getPixelAt(0, 2).getGreen());
    assertEquals(66, flipHVImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(7, flipHVImage1.getPixelAt(1, 0).getRed());
    assertEquals(87, flipHVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, flipHVImage1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(125, flipHVImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, flipHVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(3, flipHVImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(10, flipHVImage1.getPixelAt(1, 2).getRed());
    assertEquals(20, flipHVImage1.getPixelAt(1, 2).getGreen());
    assertEquals(33, flipHVImage1.getPixelAt(1, 2).getBlue());


  }

  @Test
  public void testFlipVertical3x2() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    IImageProcessCommand flipVCommand = new FlipImageProcessCommand(false);
    ImageImpl flipVImage1 = flipVCommand.run(threeByTwo);

    // pixel 1
    assertEquals(55, flipVImage1.getPixelAt(0, 0).getRed());
    assertEquals(44, flipVImage1.getPixelAt(0, 0).getGreen());
    assertEquals(66, flipVImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(28, flipVImage1.getPixelAt(0, 1).getRed());
    assertEquals(0, flipVImage1.getPixelAt(0, 1).getGreen());
    assertEquals(200, flipVImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(17, flipVImage1.getPixelAt(0, 2).getRed());
    assertEquals(99, flipVImage1.getPixelAt(0, 2).getGreen());
    assertEquals(200, flipVImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(10, flipVImage1.getPixelAt(1, 0).getRed());
    assertEquals(20, flipVImage1.getPixelAt(1, 0).getGreen());
    assertEquals(33, flipVImage1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(125, flipVImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, flipVImage1.getPixelAt(1, 1).getGreen());
    assertEquals(3, flipVImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(7, flipVImage1.getPixelAt(1, 2).getRed());
    assertEquals(87, flipVImage1.getPixelAt(1, 2).getGreen());
    assertEquals(90, flipVImage1.getPixelAt(1, 2).getBlue());

    IImageProcessCommand flipVHCommand = new FlipImageProcessCommand(true);
    ImageImpl flipVHImage1 = flipVHCommand.run(flipVImage1);

    // pixel 1
    assertEquals(17, flipVHImage1.getPixelAt(0, 0).getRed());
    assertEquals(99, flipVHImage1.getPixelAt(0, 0).getGreen());
    assertEquals(200, flipVHImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(28, flipVHImage1.getPixelAt(0, 1).getRed());
    assertEquals(0, flipVHImage1.getPixelAt(0, 1).getGreen());
    assertEquals(200, flipVHImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(55, flipVHImage1.getPixelAt(0, 2).getRed());
    assertEquals(44, flipVHImage1.getPixelAt(0, 2).getGreen());
    assertEquals(66, flipVHImage1.getPixelAt(0, 2).getBlue());
    //pixel 4
    assertEquals(7, flipVHImage1.getPixelAt(1, 0).getRed());
    assertEquals(87, flipVHImage1.getPixelAt(1, 0).getGreen());
    assertEquals(90, flipVHImage1.getPixelAt(1, 0).getBlue());
    // pixel 5
    assertEquals(125, flipVHImage1.getPixelAt(1, 1).getRed());
    assertEquals(66, flipVHImage1.getPixelAt(1, 1).getGreen());
    assertEquals(3, flipVHImage1.getPixelAt(1, 1).getBlue());
    //pixel 6
    assertEquals(10, flipVHImage1.getPixelAt(1, 2).getRed());
    assertEquals(20, flipVHImage1.getPixelAt(1, 2).getGreen());
    assertEquals(33, flipVHImage1.getPixelAt(1, 2).getBlue());
  }

  // testing readPPM (load)
  @Test
  public void testReadPPMLoad1() {
    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    // pixel 1
    assertEquals(10, fourByFour.getPixelAt(0, 0).getRed());
    assertEquals(20, fourByFour.getPixelAt(0, 0).getGreen());
    assertEquals(33, fourByFour.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(125, fourByFour.getPixelAt(0, 1).getRed());
    assertEquals(66, fourByFour.getPixelAt(0, 1).getGreen());
    assertEquals(3, fourByFour.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(7, fourByFour.getPixelAt(1, 0).getRed());
    assertEquals(87, fourByFour.getPixelAt(1, 0).getGreen());
    assertEquals(90, fourByFour.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, fourByFour.getPixelAt(1, 1).getRed());
    assertEquals(44, fourByFour.getPixelAt(1, 1).getGreen());
    assertEquals(66, fourByFour.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testReadPPMLoad2() {
    ImageImpl twoByThree = null;
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(pixel1, twoByThree.getPixelAt(0, 0));
    assertEquals(pixel2, twoByThree.getPixelAt(0, 1));
    assertEquals(pixel3, twoByThree.getPixelAt(1, 0));
    assertEquals(pixel4, twoByThree.getPixelAt(1, 1));
    assertEquals(pixel6, twoByThree.getPixelAt(2, 0));
    assertEquals(pixel7, twoByThree.getPixelAt(2, 1));

  }

  @Test
  public void testReadPPMLoad3() {
    ImageImpl threeByTwo = null;
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    assertEquals(new Pixel(10, 20, 33, 200),
            threeByTwo.getPixelAt(0, 0));
    assertEquals(new Pixel(125, 66, 3, 200),
            threeByTwo.getPixelAt(0, 1));
    assertEquals(new Pixel(7, 87, 90, 200),
            threeByTwo.getPixelAt(0, 2));
    assertEquals(new Pixel(55, 44, 66, 200),
            threeByTwo.getPixelAt(1, 0));
    assertEquals(new Pixel(28, 0, 200, 200),
            threeByTwo.getPixelAt(1, 1));
    assertEquals(new Pixel(17, 99, 200, 200),
            threeByTwo.getPixelAt(1, 2));
  }


  @Test
  public void testSave1() {
    ImageImpl fourByFour = null;
    OutputStream out = new ByteArrayOutputStream();

    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    try {
      writePPM(fourByFour, out);
    } catch (IOException e) {
      fail();
    }

    String expectedOutput =
            "P3\n" +
                    "2 2\n" +
                    "255\n" +
                    "10\n" +
                    "20\n" +
                    "33\n" +
                    "125\n" +
                    "66\n" +
                    "3\n" +
                    "7\n" +
                    "87\n" +
                    "90\n" +
                    "55\n" +
                    "44\n" +
                    "66\n";

    assertEquals(expectedOutput, out.toString());
  }

  @Test
  public void testSave2() {
    ImageImpl twoByThree = null;
    OutputStream out = new ByteArrayOutputStream();
    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    try {
      writePPM(twoByThree, out);
    } catch (IOException e) {
      fail();
    }

    String expectedOutput = "P3\n" +
            "2 3\n" +
            "255\n" +
            "10\n" +
            "20\n" +
            "33\n" +
            "125\n" +
            "66\n" +
            "3\n" +
            "7\n" +
            "87\n" +
            "90\n" +
            "55\n" +
            "44\n" +
            "66\n" +
            "28\n" +
            "0\n" +
            "244\n" +
            "17\n" +
            "99\n" +
            "255\n";

    assertEquals(expectedOutput, out.toString());
  }

  @Test
  public void testSave3() {
    ImageImpl threeByTwo = null;
    OutputStream out = new ByteArrayOutputStream();
    try {
      threeByTwo = readPPM("src/3x2.ppm");
    } catch (IOException e) {
      fail();
    }

    try {
      writePPM(threeByTwo, out);
    } catch (IOException e) {
      fail();
    }

    String expectedOutput = "P3\n" +
            "3 2\n" +
            "200\n" +
            "10\n" +
            "20\n" +
            "33\n" +
            "125\n" +
            "66\n" +
            "3\n" +
            "7\n" +
            "87\n" +
            "90\n" +
            "55\n" +
            "44\n" +
            "66\n" +
            "28\n" +
            "0\n" +
            "200\n" +
            "17\n" +
            "99\n" +
            "200\n";

    assertEquals(expectedOutput, out.toString());
  }
}
