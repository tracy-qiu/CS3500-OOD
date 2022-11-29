import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewImpl;

/**
 * Class that represents the Image Processor Program. Contains main method which utilizes the
 * model, view and controller interfaces/classes. This allows for the program to be used
 * on the console.
 */
public class Main {

  /**
   * Represents the main method for the Image Processor Program. It takes in an array of Strings
   * that instruct the program what to do regarding image loading, saving and editing.
   *
   * @param args arguments.
   */
  public static void main(String []args) {
    int num_parameters = args.length;
    Readable userInput = null;
    if (num_parameters > 0) {
      if (args[0].equals("-file")) {
        try {
          userInput = new FileReader(args[1]);

        } catch (FileNotFoundException e) {
        }
      } else {
        userInput = new InputStreamReader(System.in);
      }
    }
    ImageModel model = new ImageModelImpl();
    Appendable output = System.out;
    ImageView view = new ImageViewImpl(model, output);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    try {
      controller.execute();
    }
    catch (IllegalStateException e) {
      try {
        view.renderMessage("No more inputs\n");
      } catch (IOException ex) {
        throw new IllegalStateException(e.getMessage());
      }
    }
  }
}
