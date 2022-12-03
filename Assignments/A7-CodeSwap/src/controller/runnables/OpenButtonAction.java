package controller.runnables;

import java.io.IOException;

import model.ImageProcessor;
import view.GUIView;

/**
 * The button for opening an image in the GUI.
 */
public class OpenButtonAction extends AbstractButtonAction {

  /**
   * The constructor for the button.
   * @param model the model
   * @param view the view
   */
  public OpenButtonAction(ImageProcessor model, GUIView view) {
    super(model, view);
  }


  /**
   * The signature features of every button that are different.
   *
   * @return the image to be displayed after the process.
   */
  @Override
  protected String specificAction() throws IOException {
    String imgName = view.getUserStringInput("Enter the name of the image: ");
    if (imgName == null) {
      return null;
    }
    try {
      this.model.ensureKey(imgName);
    } catch (IllegalArgumentException e) {
      this.view.transmitMessage(e.getMessage());
      return null;
    }
    return imgName;
  }
}
