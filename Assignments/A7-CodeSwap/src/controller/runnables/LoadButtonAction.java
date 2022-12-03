package controller.runnables;

import java.io.File;
import java.io.IOException;

import model.ImageProcessor;
import view.GUIView;

/**
 * The button for loading an image.
 */
public class LoadButtonAction extends AbstractButtonAction {

  /**
   * The constructor for the button.
   * @param model the model
   * @param view the view
   */
  public LoadButtonAction(ImageProcessor model, GUIView view) {
    super(model, view);
  }


  /**
   * The signature features of every button that are different.
   *
   * @return the image to be displayed after the process.
   */
  @Override
  protected String specificAction() throws IOException {
    try {
      File imgFile = view.takeFileInput();
      String imgName = view.getUserStringInput("Enter the name of the image: ");
      if (imgName == null) {
        return null;
      }
      this.model.loadImage(imgFile, imgName);
      return imgName;
    } catch (IOException e) {
      view.transmitMessage(e.getMessage());
      return null;
    } catch (IllegalArgumentException e) {
      view.transmitMessage(e.getMessage());
      return null;
    }
  }
}
