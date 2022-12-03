package controller.runnables;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageProcessor;
import model.ImageUtil;
import view.GUIView;

/**
 * The button for saving an image.
 */
public class SaveButtonAction implements Runnable {

  private final ImageProcessor model;
  private final GUIView view;

  /**
   * The constructor for the button.
   * @param model the model
   * @param view the view
   */
  public SaveButtonAction(ImageProcessor model, GUIView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Runs the save command.
   */
  @Override
  public void run() {
    if (!this.view.checkIfLoaded()) {
      try {
        this.view.transmitMessage("Load an image to process before selecting the command");
        return;
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }

    try {
      File output = view.takeFileInput();
      String imgFormat = this.getImageFormat(output.getName());
      if (!".ppm".equals(output.getName().substring(output.getName().length() - 4))) {
        ImageIO.write(ImageUtil.pixelToBuffered(this.model.getLoadedImg(this.view.currImageName())),
                imgFormat,
                output);

      } else {
        this.model.getLoadedImg(this.view.currImageName()).saveImg(output.getPath());
      }

    } catch (IOException e) {
      try {
        view.transmitMessage(e.getMessage());
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }
    } catch (IllegalArgumentException e) {
      try {
        view.transmitMessage(e.getMessage());
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }
    }
  }


  private String getImageFormat(String fileName) {
    for (int i = fileName.length() - 1; i >= 0; i--) {
      if (fileName.charAt(i) == '.') {
        return fileName.substring(i + 1);
      }
    }
    throw new IllegalArgumentException("Filename doesnt have an extension");
  }
}
