package view;

import java.awt.event.ActionListener;
import java.io.IOException;

import model.IImage;

/**
 * Class represents the Mock View used for testing the GUI and MVCCommandController.
 */
public class MockView implements GUIView {

  private final Appendable log;

  /**
   * Represents the constuctor of the MockView.
   *
   * @param log appendable used for testing.
   */
  public MockView(Appendable log) {
    if (log == null) {
      throw new IllegalArgumentException("Null Log");
    }
    this.log = log;
  }

  @Override
  public void makeVisible() {
    return;
  }

  @Override
  public void setListener(ActionListener actionEvent) {
    return;
  }

  @Override
  public String getInput(String s) {
    try {
      this.log.append("Getting " + s + " input from GUI ");
    } catch (IOException e) {
      throw new IllegalStateException("Illegal State Exception");
    }
    switch (s) {
      case ("load"):
      case ("save"):
        return "images/stickfigure.jpg";
      case ("brighten"):
        return "10";
      default:
        return "";
    }
  }

  @Override
  public void refresh() {
    return;
  }

  @Override
  public void renderMessage(String s) {
    return;
  }

  @Override
  public void renderImage(IImage image) {
    try {
      log.append("Action Performed");
    } catch (IOException e) {
      throw new IllegalStateException("Illegal State Exception");
    }
  }

  @Override
  public void renderHistogram(int[][] histogramArray) {
    return;
  }
}
