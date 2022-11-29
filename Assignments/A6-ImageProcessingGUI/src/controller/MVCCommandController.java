package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import model.ImageModel;
import view.GUIView;

/**
 * Class that represents the controller that facilitates communication from the GUI view and the
 * model. Responds to action events caused by user interactions in the GUI, and relays these
 * changes to the model. Allows for the program to make a user's desired changes to an image and
 * then sends information back to the GUi view to display.
 */
public class MVCCommandController implements GUIController, ActionListener {
  private final ImageModel model;
  private final GUIView view;

  /**
   * Constructor for GUI controller class. Takes in the model and the GUI view.
   *
   * @param model model of the Image Processor.
   * @param view  GUI view of the Image Processor.
   * @throws IllegalArgumentException if either the model or GUI view are null.
   */
  public MVCCommandController(ImageModel model, GUIView view) {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null image model"));
    }
    this.model = model;
    if (view == null) {
      throw new IllegalArgumentException(String.format("Null image view"));
    }
    this.view = view;
    view.setListener(this);
    view.makeVisible();
  }

  /**
   * Applies a given command to the model, resulting in a change in an image in the Image Processor.
   *
   * @param command command (function object) being applied the model.
   */
  @Override
  public void execute(ImageCommand command) {
    try {
      command.goCommand(model);
    } catch (IOException e) {
      throw new IllegalStateException("Command failed");
    }
  }

  /**
   * Retrieves an action event as defined in the GUI view, and performs the associated command
   * on the model. Results in changes to the image.
   *
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input text field
      case "Load":
        try {
          this.execute(new Load(view.getInput("load"), "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException loadError) {
          this.view.renderMessage("Please pick a valid image to load");
        }
        break;
      case "Save":
        try {
          this.execute(new Save(view.getInput("save"), "image"));
        } catch (IllegalArgumentException sError) {
          this.view.renderMessage("Please pick a valid image to save");
        }
        break;
      case "Greyscale":
        try {
          this.execute(new Greyscale("greyscale", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException greyscaleError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");
        }
        break;
      case "Red-Component":
        try {
          this.execute(new Greyscale("red-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException rError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Blue-Component":
        try {
          this.execute(new Greyscale("blue-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException bError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Green-Component":
        try {
          this.execute(new Greyscale("green-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException gError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Intensity-Component":
        try {
          this.execute(new Greyscale("intensity-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException iError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Value-Component":
        try {
          this.execute(new Greyscale("value-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException vError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Luma-Component":
        try {
          this.execute(new Greyscale("luma-component", "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException lError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Horizontal Flip":
        try {
          this.execute(new Flip(true, "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException hError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Vertical Flip":
        try {
          this.execute(new Flip(false, "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException vError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");
        }
        break;
      case "Brighten":
        int brightenChange = 0;
        try {
          brightenChange = Integer.parseInt(view.getInput("brighten"));
        } catch (NumberFormatException n) {
          // maybe render a message instead
          this.view.renderMessage("Please enter an integer as the brightness change");
        }
        // need to get brighten change value
        try {
          this.execute(new Brighten(brightenChange, "image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException bError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Sepia":
        try {
          this.execute(new Sepia("image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException sError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Blur":
        try {
          this.execute(new Blur("image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException bError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Sharpen":
        try {
          this.execute(new Sharpen("image", "image"));
          this.updateViewImage();
        } catch (IllegalArgumentException sError) {
          this.view.renderMessage("Error: Invalid Image / No Image Selected");

        }
        break;
      case "Exit Button":
        System.exit(0);
        break;
      default:
        break;
    }
  }

  /**
   * Helper function that updates the current image and histogram being displayed in the GUI.
   */
  private void updateViewImage() {
    this.view.renderImage(this.model.getImageAt("image"));
    this.view.renderHistogram(this.model.getHistogramAt("image"));
  }
}
