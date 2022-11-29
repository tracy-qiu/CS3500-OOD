package controller;


/**
 * This interface represents the controller for the ImageProcessor. It allows for
 * the user to interact with the model and view of the ImageProcessor.. These
 * interactions are transmitted to the model and the view then acts accordingly. It allows the
 * user to actually use ImageProcessor. Tells the model what to do and the view
 * what to display.
 *
 */
public interface ImageController {

  /**
   * Method that allows for the user to input commands to ImageProcessor program that then results
   * in alterations made to images loaded and stored in the program.
   * @throws IllegalStateException when controller unable to successfully read input or sent output.
   */
  void execute() throws IllegalStateException;
}