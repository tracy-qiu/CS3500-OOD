import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.MVCCommandController;
import model.ImageModel;
import model.ImageModelImpl;
import view.GUIView;
import view.ImageGraphicsView;

/**
 * Class that represents the Image Processor Program with GUI. Contains main method which utilizes
 * the model, view and controller interfaces/classes. This allows for the program to be used
 * on the console.
 */
public class MainGUI {

  /**
   * Represents the main method for the Image Processor Program with GUI. It takes in an array of
   * Strings that instruct the program what to do regarding image loading, saving and editing.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    GUIView view = new ImageGraphicsView();

    ImageController controller = null;
    ActionListener guiController = null;
    Readable userInput = null;

    int num_parameters = args.length;
    if (num_parameters > 0) {
      if (args[0].equals("-file")) {
        try {
          userInput = new FileReader(args[1]);
          controller = new ImageControllerImpl(model, view, userInput);
        } catch (FileNotFoundException e) {
          view.renderMessage("File not found, failed to run program");
        }
        try {
          controller.execute();
        } catch (IllegalStateException e) {
          view.renderMessage("No more inputs\n");
        }
      } else if (args[0].equals("-text")) {
        userInput = new InputStreamReader(System.in);
        controller = new ImageControllerImpl(model, view, userInput);
        try {
          controller.execute();
        } catch (IllegalStateException e) {
          view.renderMessage("No more inputs\n");
        }
      }
    } else {
      guiController = new MVCCommandController(model, view);
    }
  }
}
