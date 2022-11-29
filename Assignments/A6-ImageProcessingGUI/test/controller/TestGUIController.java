package controller;

import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.GUIView;
import view.MockView;

import static controller.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class that represents tests for the new GUI and MVCCommandController. Verify that they are
 * communicating correctly.
 */
public class TestGUIController {

  // Verify that GUI and MVCCommandController are communicating correctly
  @Test
  public void testLoad() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Load"));
    assertEquals("Getting load input from GUI Action Performed", mockLog.toString());
  }

  @Test
  public void testSave() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Save"));
    assertEquals("Getting save input from GUI ", mockLog.toString());
  }

  @Test
  public void test1Greyscale() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Greyscale"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test2Red() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Red-Component"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test3Blue() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Blue-Component"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test4Green() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Green-Component"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test5Intensity() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Intensity-Component"));
    assertEquals("Action Performed", mockLog.toString());

  }

  @Test
  public void test6Value() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Value-Component"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test7Luma() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Luma-Component"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test7Horizontal() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Horizontal Flip"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void test7Vertical() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Vertical Flip"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void testSepia() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Sepia"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void testBlur() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Blur"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void testSharpen() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Sharpen"));
    assertEquals("Action Performed", mockLog.toString());
  }

  @Test
  public void testBrighten() {

    ImageImpl fourByFour = null;
    try {
      fourByFour = readPPM("src/2x2.ppm");
    } catch (IOException e) {
      fail();
    }

    Appendable mockLog = new StringBuilder();
    GUIView mockView = new MockView(mockLog);
    ImageModel model = new ImageModelImpl();
    model.addImage("image", fourByFour);
    MVCCommandController controller = new MVCCommandController(model, mockView);
    controller.actionPerformed(new FakeActionEvent("Brighten"));
    assertEquals("Getting brighten input from GUI Action Performed", mockLog.toString());
  }


}
