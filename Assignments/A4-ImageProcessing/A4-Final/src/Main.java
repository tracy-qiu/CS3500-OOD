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
    ImageModel model = new ImageModelImpl();
    Appendable output = System.out;
    ImageView view = new ImageViewImpl(model, output);
    Readable userInput = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(model, view, userInput);
    controller.execute();
  }
}
