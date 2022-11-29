package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sepia;
import model.ImageModel;
import view.ImageView;

/**
 * This class represents the controller for the ImageProcessor. It allows for
 * the user to interact with the model and view of the ImageProcessor. These
 * interactions are transmitted to the model and the view then acts accordingly. It allows the
 * user to actually use ImageProcessor. Tells the model what to do and the view
 * what to display.
 */
public class ImageControllerImpl implements ImageController {
  ImageModel model;
  ImageView view;
  Readable input;

  /**
   * Constructor for the controller of the ImageProcessor program. Allows the
   * controller to interact with the ImageProcessor Model,ImageProcessor View and the input
   * from the User (which alters the model).
   *
   * @param model Image model.
   * @param view  Image view.
   * @param input represents input to the controller.
   * @throws IllegalArgumentException when either the
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable input)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null image model"));
    }
    this.model = model;
    if (view == null) {
      throw new IllegalArgumentException(String.format("Null image view"));
    }
    this.view = view;
    if (input == null) {
      throw new IllegalArgumentException(String.format("Null image input"));
    }
    this.input = input;
  }

  // go should only take in the model
  // streamline so don't have to add new functionality each time

  /**
   * Method that allows for the user to input commands to ImageProcessor program that then results
   * in alterations made to images loaded and stored in the program.
   *
   * @throws IllegalStateException when controller unable to successfully read input or sent output.
   */
  @Override
  public void execute() throws IllegalStateException {
    Scanner scanner = new Scanner(this.input);
    Stack<ImageCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImageCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {
      return new Load(s.next(), s.next());
    });
    knownCommands.put("save", (Scanner s) -> {
      return new Save(s.next(), s.next());
    });
    knownCommands.put("red-component", (Scanner s) -> {
      return new Greyscale("red-component", s.next(), s.next());
    });
    knownCommands.put("green-component", (Scanner s) -> {
      return new Greyscale("green-component", s.next(), s.next());
    });
    knownCommands.put("blue-component", (Scanner s) -> {
      return new Greyscale("blue-component", s.next(), s.next());
    });
    knownCommands.put("value-component", (Scanner s) -> {
      return new Greyscale("value-component", s.next(), s.next());
    });
    knownCommands.put("luma-component", (Scanner s) -> {
      return new Greyscale("luma-component", s.next(), s.next());
    });
//    knownCommands.put("greyscale", (Scanner s) -> {
//      return new Greyscale("luma-component", s.next(), s.next());
//    });
    knownCommands.put("intensity-component", (Scanner s) -> {
      return new Greyscale("intensity-component", s.next(), s.next());
    });
    knownCommands.put("brighten", (Scanner s) -> {
      return new Brighten(Integer.parseInt(s.next()), s.next(), s.next());
    });
    knownCommands.put("horizontal-flip", (Scanner s) -> {
      return new Flip(true, s.next(), s.next());
    });
    knownCommands.put("vertical-flip", (Scanner s) -> {
      return new Flip(false, s.next(), s.next());
    });
    knownCommands.put("sepia", (Scanner s) -> {
      return new Sepia(s.next(), s.next());
    });
    knownCommands.put("blur", (Scanner s) -> {
      return new Blur(s.next(), s.next(), s.next());
    });

    while (scanner.hasNext()) {
      ImageCommand c;
      String in = scanner.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        this.renderMessageHelper("Quit program\n");
        return;
      }
      try {
        Function<Scanner, ImageCommand> cmd = knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          this.renderMessageHelper("Invalid command, please try again\n");
        } else {
          c = cmd.apply(scanner);
          commands.add(c);
          try {
            c.goCommand(model);
            this.renderMessageHelper(in + " command successful\n");
          } catch (IllegalArgumentException | IOException e) {
            this.renderMessageHelper(in + " command failed: " + e.getMessage() + "\n");
          }
        }
      } catch (NumberFormatException e) {
        this.renderMessageHelper("Invalid brighten change value, please enter a number\n");
      }
    }
    throw new IllegalStateException("No more inputs");
  }

  /**
   * helper method to render messages in the controller.
   *
   * @param message message to be sent out.
   * @throws IllegalStateException when the view can't properly render message.
   */
  protected void renderMessageHelper(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(String.format("Failed to render message"));
    }
  }
}

