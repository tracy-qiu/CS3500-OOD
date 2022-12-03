package controller.runnables;

import org.junit.Test;

import java.awt.event.ActionEvent;

import controller.ConfirmInputsGUIView;
import controller.ConfirmInputsModel;
import controller.GUIController;

import static org.junit.Assert.assertEquals;

/**
 * The abstract class for testing whether the controller acts
 * as an action listener to the view.
 */
public abstract class AbstractButtonActionTest {

  String command;

  String output;


  /**
   * Constructs a button test.
   * @param command the command running
   * @param output the expected output
   */
  public AbstractButtonActionTest(String command, String output) {
    this.command = command;
    this.output = output;
  }

  @Test
  public void run() {
    Appendable log = new StringBuilder();
    ConfirmInputsGUIView mockView = new ConfirmInputsGUIView(log);
    GUIController controller = new GUIController(new ConfirmInputsModel(log),
            mockView);
    controller.startProcessing();
    mockView.listener.actionPerformed(new ActionEvent(mockView, 1, command));
    assertEquals(output, log.toString());
  }

}