package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import controller.runnables.AbstractButtonActionTest;
import controller.runnables.MosaicButtonActionTest;
import model.BasicImageProcessor;
import model.GridPixelImage;
import model.ImageProcessor;
import view.GUIView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests whether the controller configures the view properly.
 */
public class GUIControllerTest {

  @Test
  public void startProcessing() {
    Appendable log = new StringBuilder();
    ImageProcessorController controller = new GUIController(new ConfirmInputsModel(log),
            new ConfirmInputsGUIView(log));
    controller.startProcessing();
    assertEquals("added listeners\n", log.toString());
  }

  @Test
  public void testMosaic() {
    Appendable mockLog = new StringBuilder();
    ImageProcessor model = new ConfirmInputsModel(mockLog);
    try {
      model.loadImage(new File("res/modern-art.jpg"), "image");
    } catch (IOException e) {
      fail();
    }

    GUIView mockView = new ConfirmInputsGUIView(mockLog);
    model.mosaicImage("image", "mosaicImage", 20);
    ImageProcessorController controller = new GUIController(model, mockView);
    controller.startProcessing();
    assertEquals("Command: load modern-art.jpg image\n"
            + "Command: mosaic image mosaicImage 20\n"
            + "added listeners\n", mockLog.toString());
  }

  @Test
  public void testMosaicButton() {
    AbstractButtonActionTest mosaicButtonTest = new MosaicButtonActionTest();
    mosaicButtonTest.run();
  }
}