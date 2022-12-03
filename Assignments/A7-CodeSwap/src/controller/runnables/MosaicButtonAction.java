package controller.runnables;

import java.io.IOException;

import model.ImageProcessor;
import view.GUIView;

public class MosaicButtonAction extends AbstractButtonAction {

  public MosaicButtonAction(ImageProcessor model, GUIView view) {
    super(model, view);
  }

  @Override
  protected String specificAction() throws IOException {
    if (!this.view.checkIfLoaded()) {
      this.view.transmitMessage("Load an image to process before selecting the command");
      return null;
    }
    String numSeeds = view.getUserStringInput("Enter the number of mosaic seeds");
    if (numSeeds == null) {
      return null;
    }

    String destName = view.getUserStringInput("Name the modified version of the image:");
    if (destName == null) {
      return null;
    }
    this.model.mosaicImage(this.view.currImageName(), destName, Integer.parseInt(numSeeds));
    return destName;
  }
}
