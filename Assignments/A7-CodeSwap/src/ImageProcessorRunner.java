import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.GUIController;
import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import model.BasicImageProcessor;
import model.ImageProcessor;
import view.GUIView;
import view.PixelImageView;
import view.SwingImageProcessorView;
import view.TerminalView;

/**
 * The runner class that houses main.
 */
public class ImageProcessorRunner {


  /**
   * Checks if there is a script in the first cmd line argument and parses it into a readable.
   * defaults to System.in.
   *
   * @param args the command line args.
   * @return A readable with the input.
   */
  public static Readable getReadable(String[] args) {
    Readable input;
    if (args.length == 2 && args[0].equals("-file")) {
      try {
        input = new InputStreamReader(new FileInputStream(args[1]));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Couldn't open script");
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      input = new InputStreamReader(System.in);
    } else {
      throw new IllegalArgumentException("Invalid arguments to the program");
    }
    return input;
  }

  /**
   * Main method to run the img processor.
   *
   * @param args enter filepath as first argument if a script needs to be executed.
   */
  public static void main(String[] args) {
    ImageProcessor model = new BasicImageProcessor(new HashMap<>());
    ImageProcessorController controller;
    if (args.length == 0) {
      GUIView view = new SwingImageProcessorView("Image Processor");
      controller = new GUIController(model, view);
    } else {
      PixelImageView view = new TerminalView();
      controller = new ImageProcessorControllerImpl(model,
              view, getReadable(args));
    }
    controller.startProcessing();
  }
}
