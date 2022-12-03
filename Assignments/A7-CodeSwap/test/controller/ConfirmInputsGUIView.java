package controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import model.PixelImage;
import view.GUIView;

/**
 * A mock that checks to see if the GUI is using the inputs properly.
 */
public class ConfirmInputsGUIView implements GUIView {

  Appendable log;

  public ActionListener listener;


  /**
   * Constructs a confirm inputs model.
   *
   * @param log log
   */
  public ConfirmInputsGUIView(Appendable log) {
    this.log = log;
  }


  /**
   * Redraws the view.
   */
  @Override
  public void refresh() {
    try {
      log.append("refreshed view\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

  }

  /**
   * Sets the ActionListener of the button commands to the given ActionListener.
   *
   * @param listener the given ActionLister for the button command
   */
  @Override
  public void addActionListener(ActionListener listener) {
    this.listener = listener;

    try {
      log.append("added listeners\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Shows the current image on the screen.
   *
   * @param image the image to display.
   */
  @Override
  public void setDisplayImage(PixelImage image) {
    try {
      log.append(String.format("Command: set image: %s\n", image.toString()));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Prompts the user to provide a file to load into the img processor.
   *
   * @return a file object
   */
  @Override
  public File takeFileInput() throws IOException {
    try {
      log.append("asked file input\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return null;
  }

  /**
   * Prompts the user for a text box.
   *
   * @param question the prompt
   * @return the string the user typed
   */
  @Override
  public String getUserStringInput(String question) {
    try {
      log.append(String.format("Command: asked: %s\n", question));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return null;
  }

  /**
   * Sets the image name so that the view can pass it to the controller
   * to do stuff.
   *
   * @param imgName name of the img
   */
  @Override
  public void setCurrImageName(String imgName) {
    try {
      log.append(String.format("Command: set image to: %s\n", imgName));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Checks if an image is currently loaded.
   *
   * @return true if yes.
   */
  @Override
  public boolean checkIfLoaded() {
    return true;
  }

  /**
   * An observation on the view that observes the name of the image that is being displayed.
   *
   * @return a string representing the image name.
   */
  @Override
  public String currImageName() {
    try {
      log.append("got imgname\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return null;
  }

  /**
   * Redraws the histogram.
   *
   * @param distributions the rgbi channels
   * @param maxValue      max val of the image
   */
  @Override
  public void reDrawHisto(int[][] distributions, int maxValue) {
    try {
      log.append("redrew histogram\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void transmitMessage(String message) throws IOException {
    try {
      log.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
