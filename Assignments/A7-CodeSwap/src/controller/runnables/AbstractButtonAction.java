package controller.runnables;

import java.io.IOException;

import model.ImageProcessor;
import view.GUIView;


/**
 * An abstraction of all the button commands in the GUI.
 */
public abstract class AbstractButtonAction implements Runnable {

  protected final ImageProcessor model;

  protected final GUIView view;

  /**
   * The constructor for the buttons.
   * @param model the model
   * @param view the view
   */
  public AbstractButtonAction(ImageProcessor model, GUIView view) {
    this.model = model;
    this.view = view;
  }


  /**
   * Runs the button action.
   */
  @Override
  public void run() {
    String displayImgName = null;
    try {
      displayImgName = this.specificAction();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    if (displayImgName != null) {
      this.view.setDisplayImage(this.model.getLoadedImg(displayImgName));
      this.view.setCurrImageName(displayImgName);
      this.view.reDrawHisto(this.model.getLoadedImg(displayImgName).computeDistr(),
              this.model.getLoadedImg(displayImgName).getMaxValue());
      this.view.refresh();
    }
  }

  /**
   * The signature features of every button that are different.
   *
   * @return the image to be displayed after the process.
   */
  protected abstract String specificAction() throws IOException;
}
