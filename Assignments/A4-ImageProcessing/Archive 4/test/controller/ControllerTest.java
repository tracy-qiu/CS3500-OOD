package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.ImageImpl;
import model.ImageModel;
import model.ImageModelImpl;
import model.MockModel;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * represents tests for the controller.
 */
public class ControllerTest {

  Pixel pixel1 = new Pixel(10, 20, 33, 255);
  Pixel pixel2 = new Pixel(125, 66, 3, 255);
  Pixel pixel3 = new Pixel(7, 87, 90, 255);
  Pixel pixel4 = new Pixel(55, 44, 66, 255);
  Pixel pixel5 = new Pixel(28, 0, 244, 255);
  Pixel pixel6 = new Pixel(17, 99, 255, 255);
  Pixel nullPixel = null;

  Pixel[][] pixelArray1 = {{pixel1, pixel2}, {pixel3, pixel4}};
  Pixel[][] pixelArraywNull = {{pixel1, pixel2}, {pixel3, pixel5}};
  Pixel[][] pixelArray2 = {{pixel1, pixel2}, {pixel3, pixel4}, {pixel5, pixel6}};

  // value greyscale of image 1
  Pixel pixel1_value = new Pixel(33, 33, 33, 255);
  Pixel pixel2_value = new Pixel(125, 125, 125, 255);
  Pixel pixel3_value = new Pixel(90, 90, 90, 255);
  Pixel pixel4_value = new Pixel(66, 66, 66, 255);
  Pixel[][] pixelArray_value = {{pixel1_value, pixel2_value}, {pixel3_value, pixel4_value}};

  // intensity greyscale of image 1
  Pixel pixel1_intensity = new Pixel(21, 21, 21, 255);
  Pixel pixel2_intensity = new Pixel(65, 65, 65, 255);
  Pixel pixel3_intensity = new Pixel(61, 61, 61, 255);
  Pixel pixel4_intensity = new Pixel(55, 55, 55, 255);
  Pixel[][] pixelArray_intensity =
      {{pixel1_intensity, pixel2_intensity}, {pixel3_intensity, pixel4_intensity}};

  // luma greyscale of image 1
  Pixel pixel1_luma = new Pixel(19, 19, 19, 255);
  Pixel pixel2_luma = new Pixel(74, 74, 74, 255);
  Pixel pixel3_luma = new Pixel(70, 70, 70, 255);
  Pixel pixel4_luma = new Pixel(48, 48, 48, 255);
  Pixel[][] pixelArray_luma = {{pixel1_luma, pixel2_luma}, {pixel3_luma, pixel4_luma}};

  // red greyscale of image 1
  Pixel pixel1_red = new Pixel(10, 10, 10, 255);
  Pixel pixel2_red = new Pixel(125, 125, 125, 255);
  Pixel pixel3_red = new Pixel(7, 7, 7, 255);
  Pixel pixel4_red = new Pixel(55, 55, 55, 255);
  Pixel[][] pixelArray_red = {{pixel1_red, pixel2_red}, {pixel3_red, pixel4_red}};

  // green greyscale of image 1
  Pixel pixel1_green = new Pixel(20, 20, 20, 255);
  Pixel pixel2_green = new Pixel(66, 66, 66, 255);
  Pixel pixel3_green = new Pixel(87, 87, 87, 255);
  Pixel pixel4_green = new Pixel(44, 44, 44, 255);
  Pixel[][] pixelArray_green = {{pixel1_green, pixel2_green}, {pixel3_green, pixel4_green}};

  // blue greyscale of image 1
  Pixel pixel1_blue = new Pixel(33, 33, 33, 255);
  Pixel pixel2_blue = new Pixel(3, 3, 3, 255);
  Pixel pixel3_blue = new Pixel(90, 90, 90, 255);
  Pixel pixel4_blue = new Pixel(66, 66, 66, 255);
  Pixel[][] pixelArray_blue = {{pixel1_blue, pixel2_blue}, {pixel3_blue, pixel4_blue}};

  // flips of image 1
  Pixel[][] pixelArray_h = {{pixel2, pixel1}, {pixel4, pixel3}};
  Pixel[][] pixelArray_v = {{pixel3, pixel4}, {pixel1, pixel2}};
  Pixel[][] pixelArray_hv = {{pixel4, pixel3}, {pixel2, pixel1}};


  // brighten 10 of image 1
  Pixel pixel1_b10 = new Pixel(20, 30, 43, 255);
  Pixel pixel2_b10 = new Pixel(135, 76, 13, 255);
  Pixel pixel3_b10 = new Pixel(17, 97, 100, 255);
  Pixel pixel4_b10 = new Pixel(65, 54, 76, 255);
  Pixel[][] pixelArray_b10 = {{pixel1_b10, pixel2_b10}, {pixel3_b10, pixel4_b10}};

  // brighten 140 of image 1
  Pixel pixel1_b140 = new Pixel(150, 160, 173, 255);
  Pixel pixel2_b140 = new Pixel(255, 206, 143, 255);
  Pixel pixel3_b140 = new Pixel(147, 227, 230, 255);
  Pixel pixel4_b140 = new Pixel(195, 184, 206, 255);
  Pixel[][] pixelArray_b140 = {{pixel1_b140, pixel2_b140}, {pixel3_b140, pixel4_b140}};

  // darken 2 of image 1
  Pixel pixel1_d2 = new Pixel(8, 18, 31, 255);
  Pixel pixel2_d2 = new Pixel(123, 64, 1, 255);
  Pixel pixel3_d2 = new Pixel(5, 85, 88, 255);
  Pixel pixel4_d2 = new Pixel(53, 42, 64, 255);
  Pixel[][] pixelArray_d2 = {{pixel1_d2, pixel2_d2}, {pixel3_d2, pixel4_d2}};

  // darken 130 of image 1
  Pixel pixel1_d130 = new Pixel(0, 0, 0, 255);
  Pixel pixel2_d130 = new Pixel(0, 0, 0, 255);
  Pixel pixel3_d130 = new Pixel(0, 0, 0, 255);
  Pixel pixel4_d130 = new Pixel(0, 0, 0, 255);
  Pixel[][] pixelArray_d130 = {{pixel1_d130, pixel2_d130}, {pixel3_d130, pixel4_d130}};

  // brighten 10 darken 15 of image 1
  Pixel pixel1_b10_d15 = new Pixel(5, 15, 28, 255);
  Pixel pixel2_b10_d15 = new Pixel(120, 61, 0, 255);
  Pixel pixel3_b10_d15 = new Pixel(2, 82, 85, 255);
  Pixel pixel4_b10_d15 = new Pixel(50, 39, 61, 255);
  Pixel[][] pixelArray_b10_d15 =
      {{pixel1_b10_d15, pixel2_b10_d15}, {pixel3_b10_d15, pixel4_b10_d15}};

  // darken 2 brighten 254 of image 1
  Pixel pixel1_d2_b254 = new Pixel(255, 255, 255, 255);
  Pixel pixel2_d2_b254 = new Pixel(255, 255, 255, 255);
  Pixel pixel3_d2_b254 = new Pixel(255, 255, 255, 255);
  Pixel pixel4_d2_b254 = new Pixel(255, 255, 255, 255);
  Pixel[][] pixelArray_d2_b254 =
      {{pixel1_d2_b254, pixel2_d2_b254}, {pixel3_d2_b254, pixel4_d2_b254}};

  // blue greyscale darken 10 of image 1
  Pixel pixel1_blue_d30 = new Pixel(3, 3, 3, 255);
  Pixel pixel2_blue_d30 = new Pixel(0, 0, 0, 255);
  Pixel pixel3_blue_d30 = new Pixel(60, 60, 60, 255);
  Pixel pixel4_blue_d30 = new Pixel(36, 36, 36, 255);
  Pixel[][] pixelArray_blue_d30 =
      {{pixel1_blue_d30, pixel2_blue_d30}, {pixel3_blue_d30, pixel4_blue_d30}};

  int maxValue = 255;
  int width = 2;
  int height = 2;
  ImageImpl image1 = new ImageImpl(width, height, maxValue, pixelArray1);
  ImageImpl image1_value = new ImageImpl(width, height, maxValue, pixelArray_value);
  ImageImpl image1_intensity = new ImageImpl(width, height, maxValue, pixelArray_intensity);
  ImageImpl image1_luma = new ImageImpl(width, height, maxValue, pixelArray_luma);
  ImageImpl image1_red = new ImageImpl(width, height, maxValue, pixelArray_red);
  ImageImpl image1_green = new ImageImpl(width, height, maxValue, pixelArray_green);
  ImageImpl image1_blue = new ImageImpl(width, height, maxValue, pixelArray_blue);
  ImageImpl image1_h = new ImageImpl(width, height, maxValue, pixelArray_h);
  ImageImpl image1_v = new ImageImpl(width, height, maxValue, pixelArray_v);
  ImageImpl image1_hv = new ImageImpl(width, height, maxValue, pixelArray_hv);
  ImageImpl image1_b10 = new ImageImpl(width, height, maxValue, pixelArray_b10);
  ImageImpl image1_b140 = new ImageImpl(width, height, maxValue, pixelArray_b140);
  ImageImpl image1_d2 = new ImageImpl(width, height, maxValue, pixelArray_d2);
  ImageImpl image1_d130 = new ImageImpl(width, height, maxValue, pixelArray_d130);
  ImageImpl image1_b10_d15 = new ImageImpl(width, height, maxValue, pixelArray_b10_d15);
  ImageImpl image1_d2_b254 = new ImageImpl(width, height, maxValue, pixelArray_d2_b254);
  ImageImpl nullImage = null;

  ImageImpl image1_blue_d30 = new ImageImpl(width, height, maxValue, pixelArray_blue_d30);
  ImageImpl image2 = new ImageImpl(2, 3, maxValue, pixelArray2);

  int maxValue2 = 200;

  //  ImageImpl image2_value = new ImageImpl(width, height, maxValue2, pixelArray_value);
  //  ImageImpl image2_intensity = new ImageImpl(width, height, maxValue2, pixelArray_intensity);
  //  ImageImpl image2_luma = new ImageImpl(width, height, maxValue2, pixelArray_luma);
  //  ImageImpl image2_red = new ImageImpl(width, height, maxValue2, pixelArray_red);
  //  ImageImpl image2_green = new ImageImpl(width, height, maxValue2, pixelArray_green);
  //  ImageImpl image2_blue = new ImageImpl(width, height, maxValue2, pixelArray_blue);
  //  ImageImpl image2_h = new ImageImpl(width, height, maxValue2, pixelArray_h);
  //  ImageImpl image2_v = new ImageImpl(width, height, maxValue2, pixelArray_v);
  //  ImageImpl image2_hv = new ImageImpl(width, height, maxValue2, pixelArray_hv);
  //  ImageImpl image2_b10 = new ImageImpl(width, height, maxValue2, pixelArray_b10);
  //  ImageImpl image2_b140 = new ImageImpl(width, height, maxValue2, pixelArray_b140);
  //  ImageImpl image2_d2 = new ImageImpl(width, height, maxValue2, pixelArray_d2);
  //  ImageImpl image2_d130 = new ImageImpl(width, height, maxValue2, pixelArray_d130);
  //  ImageImpl image2_b10_d15 = new ImageImpl(width, height, maxValue2, pixelArray_b10_d15);
  //  ImageImpl image2_d2_b254 = new ImageImpl(width, height, maxValue2, pixelArray_d2_b254);
  //
  //  ImageImpl image3 = new ImageImpl(2, 3, maxValue, pixelArray2);

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Appendable input = new StringBuilder("load src/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(null, controllerOutput);
    ImageController controller = new ImageControllerImpl(null, view, userInput);
    controller.execute();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    ImageController controller = new ImageControllerImpl(model, null,
            userInput);
    controller.execute();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    ImageModel model = new ImageModelImpl();
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, null);
    controller.execute();
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyInput() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
  }

  @Test(expected = IllegalStateException.class)
  public void testNoMoreInputs() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/2x2.ppm original brighten 10 original brighten");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
  }

  @Test(expected = IllegalStateException.class)
  public void testNoMoreInputs2() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/2x2.ppm original");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
  }

  @Test
  public void testValidLoad() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\nQuit program\n", controllerOutput.toString());
    assertEquals(image1, model.getImageAt("original"));
  }

  @Test
  public void testOverwriteLoad() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original load src/2x3.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "load command successful\nQuit program\n", controllerOutput.toString());
    assertEquals(image2, model.getImageAt("original"));
  }

  @Test
  public void testValidSave() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original\n "
                    + "load src/testPPMFiles/2x2SmallMaxValue130.ppm original2\n"
                    + "value-component original value-component\n "
                    + "intensity-component original intensity-component\n "
                    + "luma-component original luma-component\n "
                    + "red-component original red-component\n "
                    + "green-component original green-component\n "
                    + "blue-component original blue-component\n "
                    + "luma-component red-component luma-red-component\n"
                    + "value-component green-component value-green-component\n"
                    + "horizontal-flip original horizontal\n"
                    + "brighten 10 original2 brighten10-2\n"
                    + "brighten -50 original2 darken50-2\n"
                    + "save src/res/2x2value.ppm value-component\n"
                    + "save src/res/2x2intensity.ppm intensity-component\n"
                    + "save src/res/2x2luma.ppm luma-component\n"
                    + "save src/res/2x2red.ppm red-component\n"
                    + "save src/res/2x2green.ppm green-component\n"
                    + "save src/res/2x2blue.ppm blue-component\n"
                    + "save src/res/2x2luma-red.ppm luma-red-component\n "
                    + "save src/res/2x2value-green.ppm value-green-component\n"
                    + "save src/res/2x2horizontal.ppm horizontal\n"
                    + "save src/res/2x2brighten10-2.ppm brighten10-2\n "
                    + "save src/res/2x2darken50-2.ppm darken50-2\n Q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "load command successful\n"
            + "value-component command successful\n"
            + "intensity-component command successful\n"
            + "luma-component command successful\n"
            + "red-component command successful\n"
            + "green-component command successful\n"
            + "blue-component command successful\n"
            + "luma-component command successful\n"
            + "value-component command successful\n"
            + "horizontal-flip command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "Quit program\n", controllerOutput.toString());
    try {
      assertEquals(model.getImageAt("value-component"),
              ImageUtil.readPPM("src/res/2x2value.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("intensity-component"),
              ImageUtil.readPPM("src/res/2x2intensity.ppm"));
    } catch (IOException e) {
      fail();
    }
    try {
      assertEquals(model.getImageAt("luma-component"),
              ImageUtil.readPPM("src/res/2x2luma.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("red-component"),
              ImageUtil.readPPM("src/res/2x2red.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("green-component"),
              ImageUtil.readPPM("src/res/2x2green.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("blue-component"),
              ImageUtil.readPPM("src/res/2x2blue.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("luma-red-component"),
              ImageUtil.readPPM("src/res/2x2luma-red.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("value-green-component"),
              ImageUtil.readPPM("src/res/2x2value-green.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("horizontal"),
              ImageUtil.readPPM("src/res/2x2horizontal.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("brighten10-2"),
              ImageUtil.readPPM("src/res/2x2brighten10-2.ppm"));
    } catch (IOException e) {
      fail();
    }

    try {
      assertEquals(model.getImageAt("darken50-2"),
              ImageUtil.readPPM("src/res/2x2darken50-2.ppm"));
    } catch (IOException e) {
      fail();
    }

  }

  @Test
  public void testValidGreyscale() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original\n value-component original value-component\n "
                    + "intensity-component original intensity-component\n "
                    + "luma-component original luma-component\n "
                    + "red-component original red-component\n "
                    + "green-component original green-component\n "
                    + "blue-component original blue-component\n "
                    + "luma-component red-component luma-red-component\n"
                    + "value-component green-component value-green-component\n q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "value-component command successful\n"
            + "intensity-component command successful\n"
            + "luma-component command successful\n"
            + "red-component command successful\n"
            + "green-component command successful\n"
            + "blue-component command successful\n"
            + "luma-component command successful\n"
            + "value-component command successful\n"
            + "Quit program\n", controllerOutput.toString());
    assertEquals(image1, model.getImageAt("original"));
    assertEquals(image1_value, model.getImageAt("value-component"));
    assertEquals(image1_intensity, model.getImageAt("intensity-component"));
    assertEquals(image1_luma, model.getImageAt("luma-component"));
    assertEquals(image1_red, model.getImageAt("red-component"));
    assertEquals(image1_green, model.getImageAt("green-component"));
    assertEquals(image1_blue, model.getImageAt("blue-component"));
    assertEquals(image1_red, model.getImageAt("luma-red-component"));
    assertEquals(image1_green, model.getImageAt("value-green-component"));
  }

  @Test
  public void testValidFlip() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original\n "
                    + "horizontal-flip original horizontal\n "
                    + "vertical-flip original vertical\n "
                    + "vertical-flip horizontal horizontal-vertical\n "
                    + "horizontal-flip vertical vertical-horizontal\n "
                    + "horizontal-flip horizontal horizontal-horizontal\n "
                    + "vertical-flip vertical vertical-vertical\n "
                    + "horizontal-flip horizontal-vertical horizontal-vertical-horizontal\n"
                    + "vertical-flip vertical-horizontal vertical-horizontal-vertical\n q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "horizontal-flip command successful\n"
            + "vertical-flip command successful\n"
            + "vertical-flip command successful\n"
            + "horizontal-flip command successful\n"
            + "horizontal-flip command successful\n"
            + "vertical-flip command successful\n"
            + "horizontal-flip command successful\n"
            + "vertical-flip command successful\n"
            + "Quit program\n", controllerOutput.toString());
    assertEquals(image1, model.getImageAt("original"));
    assertEquals(image1_h, model.getImageAt("horizontal"));
    assertEquals(image1_v, model.getImageAt("vertical"));
    assertEquals(image1_hv, model.getImageAt("horizontal-vertical"));
    assertEquals(image1_hv, model.getImageAt("vertical-horizontal"));
    assertEquals(image1, model.getImageAt("horizontal-horizontal"));
    assertEquals(image1, model.getImageAt("vertical-vertical"));
    assertEquals(image1_v, model.getImageAt("horizontal-vertical-horizontal"));
    assertEquals(image1_h, model.getImageAt("vertical-horizontal-vertical"));
  }

  @Test
  public void testValidBrighten() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original\n "
                    + "brighten 10 original brighten10\n "
                    + "brighten 140 original brighten140\n "
                    + "brighten -2 original darken2\n "
                    + "brighten -130 original darken130\n "
                    + "brighten -15 brighten10 brighten10-darken15\n"
                    + "brighten 254 darken2 darken2-brighten254\n"
                    + "brighten fdjfkja original invalidBrighten\n q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "brighten command successful\n"
            + "Invalid brighten change value, please enter a number\n"
            + "Invalid command, please try again\n"
            + "Invalid command, please try again\n"
            + "Quit program\n", controllerOutput.toString());
    assertEquals(image1, model.getImageAt("original"));
    assertEquals(image1_b10, model.getImageAt("brighten10"));
    assertEquals(image1_b140, model.getImageAt("brighten140"));
    assertEquals(image1_d2, model.getImageAt("darken2"));
    assertEquals(image1_d130, model.getImageAt("darken130"));
    assertEquals(image1_b10_d15, model.getImageAt("brighten10-darken15"));
    assertEquals(image1_d2_b254, model.getImageAt("darken2-brighten254"));
  }

  @Test
  public void testValidCommandsFromREADME() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder(
            "load src/2x2.ppm original\n "
                    + "vertical-flip original vertical\n"
                    + "brighten 10 original brighten10\n"
                    + "blue-component original blue-greyscale\n"
                    + "horizontal-flip vertical vertical-horizontal\n"
                    + "brighten -30 blue-greyscale blue-greyscale-darken10\n"
                    + "save src/res/vertical-horizontal.ppm vertical-horizontal\n"
                    + "save src/res/blue-greyscale-darken10.ppm blue-greyscale-darken10\n"
                    + "load src/2x3.ppm original\n q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "vertical-flip command successful\n"
            + "brighten command successful\n"
            + "blue-component command successful\n"
            + "horizontal-flip command successful\n"
            + "brighten command successful\n"
            + "save command successful\n"
            + "save command successful\n"
            + "load command successful\n"
            + "Quit program\n", controllerOutput.toString());
    assertEquals(image1_v, model.getImageAt("vertical"));
    assertEquals(image1_b10, model.getImageAt("brighten10"));
    assertEquals(image1_blue, model.getImageAt("blue-greyscale"));
    assertEquals(image1_hv, model.getImageAt("vertical-horizontal"));
    assertEquals(image1_blue_d30, model.getImageAt("blue-greyscale-darken10"));
    assertEquals(image2, model.getImageAt("original"));
  }


  @Test
  public void testQuitProgram() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("Q load src/2x2.ppm original");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testInvalidCommand() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("lad src/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("Invalid command, please try again\n"
            + "Invalid command, please try again\n"
            + "Invalid command, please try again\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testInvalidBrightenChange() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("brighten ten src/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("Invalid brighten change value, please enter a number\n"
            + "Invalid command, please try again\n"
            + "Invalid command, please try again\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testInvalidReadFilePath() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load 2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command failed: File 2x2.ppm not found!\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testLoadPPMWithInvalidWidth() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/testPPMFiles/2x2InvalidWidth.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command failed: Invalid negative width or height\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testLoadPPMWithInvalidHeight() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/testPPMFiles/2x2InvalidHeight.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command failed: Invalid negative width or height\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testLoadPPMWithInvalidMaxValue() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/testPPMFiles/2x2InvalidMaxValue.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command failed: Invalid max pixel value\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testPixelValueGreaterThanMaxValue() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/testPPMFiles/2x2SmallMaxValue30.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command failed: Invalid Pixel Color Value\n"
            + "Quit program\n", controllerOutput.toString());
  }

  //  @Test
  //  public void testNullPixelsInImage() {
  //    ImageModel model = new ImageModelImpl();
  //    Appendable input = new StringBuilder("load src/testPPMFiles/2x2NullPixels.ppm original q");
  //    Readable userInput = new StringReader(input.toString());
  //    Appendable controllerOutput = new StringBuilder();
  //    ImageView view = new ImageViewImpl(model, controllerOutput);
  //    ImageController controller = new ImageControllerImpl(model, view, userInput);
  //    controller.execute();
  //    assertEquals("load command failed: Invalid Pixel Color Value\n"
  //            + "Quit program\n", controllerOutput.toString());
  //  }

  @Test
  public void testInvalidWriteFilePath() {
    ImageModel model = new ImageModelImpl();
    Appendable input =
            new StringBuilder("load src/2x2.ppm original save ppmsss/2x2.ppm original q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "save command failed: ppmsss/2x2.ppm (No such file or directory)\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test
  public void testImageNotFoundInModel() {
    ImageModel model = new ImageModelImpl();
    Appendable input = new StringBuilder("load src/2x2.ppm original "
            + "value-component originall value q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    ImageView view = new ImageViewImpl(model, controllerOutput);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
    assertEquals("load command successful\n"
            + "value-component command failed: File name does not exist, please try again\n"
            + "Quit program\n", controllerOutput.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testFakeAppendable() throws IllegalStateException {
    Appendable fakeAppendable = new FakeAppendableTest();
    Readable input = new StringReader(fakeAppendable.toString());
    MockModel mock = new MockModel(fakeAppendable);
    ImageView view = new ImageViewImpl(mock, fakeAppendable);
    ImageController controller = new ImageControllerImpl(mock, view, input);
    controller.execute();
  }
}
