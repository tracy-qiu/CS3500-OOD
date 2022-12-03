package view;

import java.io.IOException;


/**
 * An implementation of the PixelImageView with an output.
 */
public class TerminalView implements PixelImageView {

  Appendable output;


  /**
   * Constructs a Terminal View.
   *
   * @param output the output to append to.
   */
  public TerminalView(Appendable output) {
    if (output == null) {
      throw new IllegalArgumentException("Invalid view");
    }
    this.output = output;
  }

  /**
   * Constructs a Terminal VIew with stdout.
   */
  public TerminalView() {
    this.output = System.out;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void transmitMessage(String message) throws IOException {
    this.output.append(message);
  }
}
