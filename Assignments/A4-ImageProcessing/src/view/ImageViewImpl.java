package view;

import java.io.IOException;

import model.ImageModel;

/**
 * Class that represents an implementation of ImageProcessors view. The view renders information
 * based on the model and controller.
 */
public class ImageViewImpl implements ImageView {
  protected ImageModel model;
  protected Appendable output;

  /**
   * Constructor for the image processor's view. The view currently renders messages from the
   * controller and is designed to be able to render using the model.
   * @param model ImageModel currently a placeholder for future functionality to render information
   *              from the model
   * @param output Appendable used to append messages to render in view
   * @throws IllegalArgumentException if the model or output are null
   */
  public ImageViewImpl(ImageModel model, Appendable output) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null image model"));
    }
    this.model = model;

    if (output == null) {
      throw new IllegalArgumentException(String.format("Null image output appendable"));
    }
    this.output = output;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IOException(String.format("Render message failed " + e));
    }
  }
}
