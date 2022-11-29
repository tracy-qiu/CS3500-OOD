package controller.commands;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import controller.ImageCommand;
import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageImpl;
import model.ImageModel;
import model.ImageModelImpl;
import model.MockModel;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static controller.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * tests for the commands.
 */
public class CommandsTest {

  @Test(expected = IllegalStateException.class)
  public void testValidGreyscale() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/2x2.ppm original");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("", controllerOutput.toString());
  }

  // test commands

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelBrightenCommand() {
    ImageModel model1 = null;
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    ImageCommand brighten =
            new Brighten(10,
                    "twoByThree", "twoByThree-brighten10");

    try {
      brighten.goCommand(model1);
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelFlipCommand() {
    ImageModel model1 = null;
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    ImageCommand flip =
            new Flip(true, "twoByThree", "twoByThree-brighten10");

    try {
      flip.goCommand(model1);
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelGreyscaleCommand() {
    ImageModel model1 = null;
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    ImageCommand greyscale =
            new Greyscale("red-component",
                    "twoByThree", "twoByThree-brighten10");

    try {
      greyscale.goCommand(model1);
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelLoadCommand() {
    ImageModel model1 = null;
    ImageCommand load = new Load("src/twoByThree", "twoByThree");

    try {
      load.goCommand(model1);
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelSaveCommand() {
    ImageModel model1 = null;
    ImageCommand save = new Save("src/twoByThree", "twoByThree");

    try {
      save.goCommand(model1);
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void testBrightenCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand brighten = new Brighten(10,
            "twoByThree", "twoByThree-brighten10");

    try {
      brighten.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl brighten10 = model1.getImageAt("twoByThree-brighten10");

    // pixel 1
    assertEquals(20, brighten10.getPixelAt(0, 0).getRed());
    assertEquals(30, brighten10.getPixelAt(0, 0).getGreen());
    assertEquals(43, brighten10.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(135, brighten10.getPixelAt(0, 1).getRed());
    assertEquals(76, brighten10.getPixelAt(0, 1).getGreen());
    assertEquals(13, brighten10.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(17, brighten10.getPixelAt(1, 0).getRed());
    assertEquals(97, brighten10.getPixelAt(1, 0).getGreen());
    assertEquals(100, brighten10.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(65, brighten10.getPixelAt(1, 1).getRed());
    assertEquals(54, brighten10.getPixelAt(1, 1).getGreen());
    assertEquals(76, brighten10.getPixelAt(1, 1).getBlue());
    // pixel 5
    assertEquals(38, brighten10.getPixelAt(2, 0).getRed());
    assertEquals(10, brighten10.getPixelAt(2, 0).getGreen());
    assertEquals(254, brighten10.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(27, brighten10.getPixelAt(2, 1).getRed());
    assertEquals(109, brighten10.getPixelAt(2, 1).getGreen());
    assertEquals(255, brighten10.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testFlipCommandHorizontal() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand flip =
            new Flip(true, "twoByThree", "twoByThree-horizontal-flip");

    try {
      flip.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl flipHImage1 = model1.getImageAt("twoByThree-horizontal-flip");

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
  }

  @Test
  public void testFlipCommandVertical() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand flip =
            new Flip(false, "twoByThree", "twoByThree-vertical-flip");

    try {
      flip.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl flipVImage1 = model1.getImageAt("twoByThree-vertical-flip");

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
  }

  @Test
  public void testRedGreyscaleCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand redGreyscale =
            new Greyscale("red-component",
                    "twoByThree", "twoByThree-red-greyscale");

    try {
      redGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl redImage1 = model1.getImageAt("twoByThree-red-greyscale");

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
  public void testBlueGreyscaleCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand blueGreyscale =
            new Greyscale("blue-component",
                    "twoByThree", "twoByThree-blue-greyscale");

    try {
      blueGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl blueImage1 = model1.getImageAt("twoByThree-blue-greyscale");

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
  public void testGreenGreyscaleCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand greenGreyscale =
            new Greyscale("green-component",
                    "twoByThree", "twoByThree-green-greyscale");

    try {
      greenGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl greenImage1 = model1.getImageAt("twoByThree-green-greyscale");

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
  public void testValueGreyscaleCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand valueGreyscale =
            new Greyscale("value-component",
                    "twoByThree", "twoByThree-value-greyscale");

    try {
      valueGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl valueImage1 = model1.getImageAt("twoByThree-value-greyscale");

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
  public void testIntensityGreyscaleCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand intensityGreyscale =
            new Greyscale("intensity-component",
                    "twoByThree", "twoByThree-intensity-greyscale");

    try {
      intensityGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl intensityImage1 = model1.getImageAt("twoByThree-intensity-greyscale");

    // pixel 1
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getRed());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getGreen());
    assertEquals(21, intensityImage1.getPixelAt(0, 0).getBlue());
    // pixel 2
    assertEquals(64, intensityImage1.getPixelAt(0, 1).getRed());
    assertEquals(64, intensityImage1.getPixelAt(0, 1).getGreen());
    assertEquals(64, intensityImage1.getPixelAt(0, 1).getBlue());
    // pixel 3
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getRed());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getGreen());
    assertEquals(61, intensityImage1.getPixelAt(1, 0).getBlue());
    //pixel 4
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getRed());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getGreen());
    assertEquals(55, intensityImage1.getPixelAt(1, 1).getBlue());

    // pixel 5
    assertEquals(90, intensityImage1.getPixelAt(2, 0).getRed());
    assertEquals(90, intensityImage1.getPixelAt(2, 0).getGreen());
    assertEquals(90, intensityImage1.getPixelAt(2, 0).getBlue());
    //pixel 6
    assertEquals(123, intensityImage1.getPixelAt(2, 1).getRed());
    assertEquals(123, intensityImage1.getPixelAt(2, 1).getGreen());
    assertEquals(123, intensityImage1.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void testLumaCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    try {
      twoByThree = readPPM("src/2x3.ppm");
    } catch (IOException e) {
      fail();
    }

    model1.addImage("twoByThree", twoByThree);
    ImageCommand lumaGreyscale =
            new Greyscale("luma-component",
                    "twoByThree", "twoByThree-luma-greyscale");

    try {
      lumaGreyscale.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl lumaImage1 = model1.getImageAt("twoByThree-luma-greyscale");

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
  public void testLoadCommand() {
    ImageModel model1 = new ImageModelImpl();
    ImageImpl twoByThree = null;

    ImageCommand load = new Load("src/2x3.ppm", "2x3");

    try {
      load.goCommand(model1);
    } catch (IOException e) {
      fail();
    }

    ImageImpl loadImage1 = model1.getImageAt("2x3");

    // pixels that make up 2x3
    Pixel pixel1 = new Pixel(10, 20, 33, 255);
    Pixel pixel2 = new Pixel(125, 66, 3, 255);
    Pixel pixel3 = new Pixel(7, 87, 90, 255);
    Pixel pixel4 = new Pixel(55, 44, 66, 255);
    Pixel pixel6 = new Pixel(28, 0, 244, 255);
    Pixel pixel7 = new Pixel(17, 99, 255, 255);

    assertEquals(pixel1, loadImage1.getPixelAt(0, 0));
    assertEquals(pixel2, loadImage1.getPixelAt(0, 1));
    assertEquals(pixel3, loadImage1.getPixelAt(1, 0));
    assertEquals(pixel4, loadImage1.getPixelAt(1, 1));
    assertEquals(pixel6, loadImage1.getPixelAt(2, 0));
    assertEquals(pixel7, loadImage1.getPixelAt(2, 1));
  }

  // test MockModel
  @Test
  public void testMockModel() {
    Appendable log = new StringBuilder();
    Appendable viewAP = new StringBuilder();
    Readable readable = new StringReader("load src/2x3.ppm 2x3 q");
    ImageModel mockModel = new MockModel(log);
    ImageView view = new ImageViewImpl(mockModel, viewAP);
    ImageController controller = new ImageControllerImpl(mockModel, view, readable);
    controller.execute();

    // return image name, width and height
    assertEquals("2x3 2 3 ", log.toString());
  }

}
