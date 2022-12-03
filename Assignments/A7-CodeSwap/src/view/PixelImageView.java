package view;

import java.io.IOException;


/**
 * The view to communicate with the user.
 */
public interface PixelImageView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void transmitMessage(String message) throws IOException;
}
