package controller;

import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import model.BasicImageProcessor;
import model.ImageProcessor;
import model.PixelImage;
import view.PixelImageView;
import view.TerminalView;

import static org.junit.Assert.assertEquals;


/**
 * Tests the features of a controller.
 */
public class ImageProcessorControllerImplTest {

  ImageProcessor imgPro1;
  PixelImageView view1;
  ImageProcessorController controller1;

  private void initData() {
    imgPro1 = new BasicImageProcessor(new HashMap<String, PixelImage>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    controller1 = new ImageProcessorControllerImpl(imgPro1, null, new StringReader("deez"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    controller1 = new ImageProcessorControllerImpl(null, view1, new StringReader("deez"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullReader() {
    controller1 = new ImageProcessorControllerImpl(imgPro1, view1, null);
  }


  @Test(expected = IllegalStateException.class)
  public void testControllerCantTransmit() {
    this.initData();
    Appendable out = new MockAppendable();
    Readable in = new StringReader("load res/chess3.ppm ch");
    view1 = new TerminalView(out);
    controller1 = new ImageProcessorControllerImpl(imgPro1, view1, in);
    controller1.startProcessing();
  }

  @Test(expected = IllegalStateException.class)
  public void invalidCommand() {
    this.initData();
    Appendable out = new MockAppendable();
    Readable in = new StringReader("load res/chess3.ppm ch");
    view1 = new TerminalView(out);
    controller1 = new ImageProcessorControllerImpl(new InvalidCommandModel(), view1, in);
    controller1.startProcessing();
  }

  @Test
  public void startProcessing() {
    this.initData();
    StringBuilder out = new StringBuilder();
    view1 = new TerminalView(out);
    StringReader in = new StringReader("load res/modern-art.ppm modart " +
            "red-component modart modart " +
            "green-component modart modart " +
            "blue-component modart modart " +
            "value modart modart " +
            "intensity modart modart " +
            "luma modart modart " +
            "flip-vertical modart modart " +
            "flip-horizontal modart modart " +
            "brighten modart modart 20 " +
            "darken modart modart 10 " +
            "blur modart modart " +
            "sepia modart modart " +
            "sharpen modart modart " +
            "greyscale modart modart " +
            "save res/processed-modern-art.ppm modart ");
    controller1 = new ImageProcessorControllerImpl(imgPro1, view1, in);
    controller1.startProcessing();
    assertEquals("Welcome to Image Processor\n" +
            "load: Success.\n" +
            "red-component: Success.\n" +
            "green-component: Success.\n" +
            "blue-component: Success.\n" +
            "value: Success.\n" +
            "intensity: Success.\n" +
            "luma: Success.\n" +
            "flip-vertical: Success.\n" +
            "flip-horizontal: Success.\n" +
            "brighten: Success.\n" +
            "darken: Success.\n" +
            "blur: Success.\n" +
            "sepia: Success.\n" +
            "sharpen: Success.\n" +
            "greyscale: Success.\n" +
            "save: Success.\n" +
            "End of input\n" +
            "Quit successfully\n" +
            "Thank You for using our Image Processor\n", out.toString());
  }

  @Test
  public void testQuit() {
    this.initData();
    StringBuilder out = new StringBuilder();
    view1 = new TerminalView(out);
    StringReader in = new StringReader("load res/modern-art.ppm modart " +
            "red-component modart modart " +
            "Quit");
    controller1 = new ImageProcessorControllerImpl(imgPro1, view1, in);
    controller1.startProcessing();
    assertEquals("Welcome to Image Processor\n" +
            "load: Success.\n" +
            "red-component: Success.\n" +
            "Quit successfully\n" +
            "Thank You for using our Image Processor\n", out.toString());
  }


  @Test
  public void confirmInput() {
    this.initData();
    StringBuilder doNotCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    view1 = new TerminalView(doNotCareOutput);
    StringReader in = new StringReader("load res/modern-art.ppm modart " +
            "red-component modart modart " +
            "green-component modart modart " +
            "blue-component modart modart " +
            "value modart modart " +
            "intensity modart modart " +
            "luma modart modart " +
            "flip-vertical modart modart " +
            "flip-horizontal modart modart " +
            "brighten modart modart 20 " +
            "darken modart modart 10 " +
            "blur modart modart " +
            "sepia modart modart " +
            "sharpen modart modart " +
            "greyscale modart modart ");
    controller1 = new ImageProcessorControllerImpl(new ConfirmInputsModel(log), view1, in);
    controller1.startProcessing();
    assertEquals("Command: load modern-art.ppm modart\n" +
            "Command: extract-component modart modart red\n" +
            "Command: extract-component modart modart green\n" +
            "Command: extract-component modart modart blue\n" +
            "Command: greyscale modart modart value\n" +
            "Command: greyscale modart modart intensity\n" +
            "Command: greyscale modart modart luma\n" +
            "Command: flip-vertical modart modart\n" +
            "Command: flip-horizontal modart modart\n" +
            "Command: brighten modart modart 20\n" +
            "Command: darken modart modart 10\n" +
            "Command: filter modart modart blur\n" +
            "Command: filter modart modart sepia\n" +
            "Command: filter modart modart sharpen\n" +
            "Command: filter modart modart greyscale\n", log.toString());

  }

}