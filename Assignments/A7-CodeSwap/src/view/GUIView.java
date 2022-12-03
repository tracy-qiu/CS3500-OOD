package view;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import model.PixelImage;

/**
 * The graphical interface implemented for the Image-Processor program.
 */
public interface GUIView extends PixelImageView {

  /**
   * Redraws the view.
   */
  void refresh();

  /**
   * Sets the ActionListener of the button commands to the given ActionListener.
   *
   * @param listener the given ActionLister for the button command
   */
  void addActionListener(ActionListener listener);


  /**
   * Shows the current image on the screen.
   *
   * @param image the image to display.
   */
  void setDisplayImage(PixelImage image);

  /**
   * Prompts the user to provide a file to load into the img processor.
   *
   * @return a file object
   */
  File takeFileInput() throws IOException;

  /**
   * Prompts the user for a text box.
   *
   * @param question the prompt
   * @return the string the user typed
   */
  String getUserStringInput(String question);


  /**
   * Sets the image name so that the view can pass it to the controller
   * to do stuff.
   *
   * @param imgName name of the img
   */
  void setCurrImageName(String imgName);

  /**
   * Checks if an image is currently loaded.
   *
   * @return true if yes.
   */
  boolean checkIfLoaded();

  /**
   * An observation on the view that observes the name of the image that is being displayed.
   *
   * @return a string representing the image name.
   */
  String currImageName();

  /**
   * Redraws the histogram.
   *
   * @param distributions the rgbi channels
   * @param maxValue      max val of the image
   */
  void reDrawHisto(int[][] distributions, int maxValue);

}
