package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.cmds.BlueCompCmd;
import controller.cmds.BlurCmd;
import controller.cmds.BrightenCmd;
import controller.cmds.DarkenCmd;
import controller.cmds.GreenCompCmd;
import controller.cmds.GreyscaleCmd;
import controller.cmds.HorizFlipCmd;
import controller.cmds.ImageProcessorCmds;
import controller.cmds.IntensityCmd;
import controller.cmds.LoadCmd;
import controller.cmds.LumaCmd;
import controller.cmds.RedCompCmd;
import controller.cmds.SaveCmd;
import controller.cmds.SepiaCmd;
import controller.cmds.SharpenCmd;
import controller.cmds.ValueCmd;
import controller.cmds.VertFlipCmd;
import model.ImageProcessor;
import view.PixelImageView;

/**
 * An implementation of the controller with a model, view and a readable input.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {

  private final ImageProcessor model;
  private final PixelImageView view;
  private final Readable input;

  private final Map<String, Function<Scanner, ImageProcessorCmds>> knownCommands;


  /**
   * Constructs a ImageProcessorControllerImpl.
   *
   * @param model the model to process
   * @param view  the view to render on
   * @param input the input
   */
  public ImageProcessorControllerImpl(ImageProcessor model, PixelImageView view, Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Need non null values to construct a controller");
    }
    this.model = model;
    this.view = view;
    this.input = input;
    this.knownCommands = new HashMap<String, Function<Scanner, ImageProcessorCmds>>();
    this.knownCommands.put("load", scanner -> new LoadCmd(scanner));
    this.knownCommands.put("red-component", scanner -> new RedCompCmd(scanner));
    this.knownCommands.put("green-component", scanner -> new GreenCompCmd(scanner));
    this.knownCommands.put("blue-component", scanner -> new BlueCompCmd(scanner));
    this.knownCommands.put("flip-vertical", scanner -> new VertFlipCmd(scanner));
    this.knownCommands.put("flip-horizontal", scanner -> new HorizFlipCmd(scanner));
    this.knownCommands.put("brighten", scanner -> new BrightenCmd(scanner));
    this.knownCommands.put("darken", scanner -> new DarkenCmd(scanner));
    this.knownCommands.put("value", scanner -> new ValueCmd(scanner));
    this.knownCommands.put("intensity", scanner -> new IntensityCmd(scanner));
    this.knownCommands.put("luma", scanner -> new LumaCmd(scanner));
    this.knownCommands.put("save", scanner -> new SaveCmd(scanner));
    this.knownCommands.put("blur", scanner -> new BlurCmd(scanner));
    this.knownCommands.put("sharpen", scanner -> new SharpenCmd(scanner));
    this.knownCommands.put("sepia", scanner -> new SepiaCmd(scanner));
    this.knownCommands.put("greyscale", scanner -> new GreyscaleCmd(scanner));
  }


  /**
   * Start the imgprocessor and wait for user input.
   * Execute if the input is valid
   *
   * @throws IllegalStateException if the input is invalid or impossible right now.
   */
  @Override
  public void startProcessing() throws IllegalStateException {
    Scanner inputScan = new Scanner(this.input);

    try {
      this.view.transmitMessage("Welcome to Image Processor\n");
      while (true) {
        while (inputScan.hasNext()) {
          ImageProcessorCmds cmd;
          String userInput = inputScan.next();
          if (userInput.equalsIgnoreCase("q")
                  || userInput.equalsIgnoreCase("quit")) {
            this.view.transmitMessage("Quit successfully\nThank You for using our Image " +
                    "Processor\n");
            return;
          }
          Function<Scanner, ImageProcessorCmds> fetchCmd =
                  this.knownCommands.getOrDefault(userInput, null);
          if (fetchCmd == null) {
            this.view.transmitMessage("Invalid Command: " + userInput + ", Try again.\n");
            continue;
          } else {
            cmd = fetchCmd.apply(inputScan);
            try {
              cmd.execute(this.model);
              this.view.transmitMessage(String.format("%s: Success.\n", userInput));
            } catch (IllegalArgumentException e) {
              this.view.transmitMessage(e.getMessage());
              this.view.transmitMessage("\n");
            }
          }
        }
        this.view.transmitMessage("End of input\n" +
                "Quit successfully\nThank You for using our Image " +
                "Processor\n");
        break;
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not transmit to the view for some reason.");
    }
  }
}
