package view;

import java.io.IOException;

/**
 * Interface that represents the view of the ImageProcessor. The view renders messages based on the
 * state of the model and controller.
 */
public interface ImageView {

  /**
   * Method that renders message to the view. This is used to render the output
   * messages from the controller.
   *
   * @param message String message that is rendered
   * @throws IOException if Appendable fails to append
   */
  void renderMessage(String message) throws IOException;

}
