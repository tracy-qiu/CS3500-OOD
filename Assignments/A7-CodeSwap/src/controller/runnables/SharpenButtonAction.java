package controller.runnables;

import java.io.IOException;

import model.ImageProcessor;
import view.GUIView;

/**
 * The button for sharpening an image.
 */
public class SharpenButtonAction extends AbstractButtonAction {

  /**
   * The constructor for the button.
   * @param model the model
   * @param view the view
   */
  public SharpenButtonAction(ImageProcessor model, GUIView view) {
    super(model, view);
  }


  /**
   * The signature features of every button that are different.
   *
   * @return the image to be displayed after the process.
   */
  @Override
  protected String specificAction() throws IOException {
    if (!this.view.checkIfLoaded()) {
      this.view.transmitMessage("Load an image to process before selecting the command");
      return null;
    }
    String destName = view.getUserStringInput("Name the modified version of the image:");
    if (destName == null) {
      return null;
    }
    this.model.filterImage(this.view.currImageName(), destName, "sharpen");
    return destName;
  }
}
