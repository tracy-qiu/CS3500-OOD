package view;

import java.awt.event.ActionListener;

import model.IImage;

/**
 * Interface the represents the GUI view for the Image Processor program. Allows the user of the
 * program to make changes to an image in the Image Processor through the functionality/design
 * of the GUI view. It essentially provides information for how the program should behave.
 */
public interface GUIView extends ImageView {

  /**
   * Method that makes the GUI window visible to the user.
   */
  void makeVisible();

  /**
   * Method that associates the buttons of the GUi with a particular functionality.
   *
   * @param actionEvent represents "action" associated with the button.
   */
  void setListener(ActionListener actionEvent);

  // String getImageProcessCommand();

  /**
   * Method that gets user inputs and sends them to the commands that require additional informaion
   * to function. Includes load, save and brighten commands.
   *
   * @param s represents user input (string text input).
   * @return String input that will be passed to the command.
   */
  String getInput(String s);

  /**
   * Method that updates the GUI if changes have been made.
   */
  void refresh();

  /**
   * Method that sends message to the user interacting with the GUI.
   *
   * @param s String message that is rendered
   */
  void renderMessage(String s);

  /**
   * Method that passes an IImage to the ImagePanel class so that it can be rendered in the GUI.
   *
   * @param image image implementation for the Image Processor.
   */
  void renderImage(IImage image);

  /**
   * Method that passes histogram data from an Image to the GUI so that it can be rendered.
   *
   * @param histogramArray histogram data from an image in the model.
   */
  void renderHistogram(int[][] histogramArray);

}
