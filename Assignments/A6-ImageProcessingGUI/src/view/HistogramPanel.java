package view;


import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Class that represents the Histogram section of the GUI displayed to the user. Draws the histogram
 * in the GUI using the histogram data from the model for a particular ImageImpl.
 */
public class HistogramPanel extends JPanel {
  private int[][] histogramArray;

  /**
   * Constructor for the histogram panel. Creates empty 2D array on integers.
   */
  public HistogramPanel() {
    super();
    this.histogramArray = new int[4][256];
  }

  /**
   * Method that actually creates the lines of the histogram. These lines represent the
   * different color channels of pixels for a particular image.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    this.createLine(0, g, Color.RED);
    this.createLine(1, g, Color.GREEN);
    this.createLine(2, g, Color.BLUE);
    this.createLine(3, g, Color.BLACK);
  }

  /**
   * Helper method that draws a line for each color channel of an image.
   *
   * @param colorRow specific pixel channel to be used.
   * @param g        Graphics interface that allows lines to be created
   * @param color    particular color to be used for the line in the visualization.
   */
  protected void createLine(int colorRow, Graphics g, Color color) {
    for (int pixelColorValue = 0; pixelColorValue < 255; pixelColorValue++) {
      int frequency1 = this.histogramArray[colorRow][pixelColorValue];
      int frequency2 = this.histogramArray[colorRow][pixelColorValue + 1];
      g.setColor(color);
      g.drawLine(pixelColorValue, frequency1,
              pixelColorValue + 1, frequency2);
    }
  }

  /**
   * Helper method that flattens the 2D histogram array and scales all the color frequency values
   * based on the margins/borders set for the histogram and the maxFrequency within the actual data.
   * This data initializes the histogram array field of the histogram panel, so that it can then
   * be drawn.
   *
   * @param histogramArray raw histogram array data (2D array).
   */
  public void renderHistogramHelper(int[][] histogramArray) {
    // get the maxFrequency of the 2D histogramArray data
    int maxFreq = this.getMaxFreq(histogramArray);

    // scale the array based on the size of the histogram panel
    int[][] scaledHistogramArray = histogramArray;
    double yScale = (this.getHeight() * 1.0) / maxFreq;

    for (int color = 0; color < 4; color++) {
      for (int intensity = 0; intensity < 256; intensity++) {
        scaledHistogramArray[color][intensity] *= yScale;
        scaledHistogramArray[color][intensity] =
                this.getHeight() - scaledHistogramArray[color][intensity];
      }
    }
    this.histogramArray = scaledHistogramArray;
  }

  /**
   * Helper method that gets the maxFrequency value in the 2D array.
   *
   * @param histogramArray 2D histogram array.
   * @return integer that is the max frequency value of the histogram.
   */
  private int getMaxFreq(int[][] histogramArray) {
    int maxFreq = 0;
    for (int color = 0; color < 4; color++) {
      for (int intensity = 0; intensity < 256; intensity++) {
        if (histogramArray[color][intensity] > maxFreq) {
          maxFreq = histogramArray[color][intensity];
        }
      }
    }
    return maxFreq;
  }
}
