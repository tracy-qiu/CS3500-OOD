package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import javax.swing.JPanel;

/**
 * The panel containing the histogram for the image.
 */
public class HistoPanel extends JPanel {

  private int[] redDistr;
  private int[] greenDistr;
  private int[] blueDistr;

  private int maxVal;

  private double maxFrequency;

  private int[] intensityDistr;

  /**
   * Constructs the panel for the histogram.
   */
  public HistoPanel() {
    super();
    this.maxVal = 255;
    this.redDistr = new int[this.maxVal + 1];
    this.greenDistr = new int[this.maxVal + 1];
    this.blueDistr = new int[this.maxVal + 1];
    this.intensityDistr = new int[this.maxVal + 1];
    this.maxFrequency = 0.0;
    this.setVisible(false);
  }

  /**
   * Sets the redDistr field to the given int array.
   *
   * @param redDistr the new redDistr
   */
  public void setRedDistr(int[] redDistr) {
    this.redDistr = redDistr;
  }


  /**
   * Sets the greenDistr field to the given int array.
   *
   * @param greenDistr the new greenDistr
   */
  public void setGreenDistr(int[] greenDistr) {
    this.greenDistr = greenDistr;
  }

  /**
   * Sets the blueDistr field to the given int array.
   *
   * @param blueDistr the new blueDistr
   */
  public void setBlueDistr(int[] blueDistr) {
    this.blueDistr = blueDistr;
  }

  /**
   * Sets the intensityDistr field to the given int array.
   *
   * @param intensityDistr the new intensityDistr
   */
  public void setIntensityDistr(int[] intensityDistr) {
    this.intensityDistr = intensityDistr;
  }


  /**
   * Updates the maxVal when needed.
   *
   * @param maxVal new maxVal
   */
  public void setMaxVal(int maxVal) {
    this.maxVal = maxVal;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);


    AffineTransform ogTransform = g2d.getTransform();

    g2d.translate(0, this.getHeight());
    g2d.scale(1, -1);
    Stroke prevStroke = g2d.getStroke();
    g2d.setStroke(new BasicStroke(5));
    // draw x and y axes
    g2d.drawLine(15, 25, this.getWidth() - 15, 25);
    g2d.drawLine(10, 25, 10, this.getHeight() - 15);
    g2d.drawString("0", 10, 20);
    g2d.setStroke(prevStroke);
    double rectWidth = (((double) this.getWidth() - 30) / (double) this.maxVal) / 4.0;
    // Computes the max frequency of all 4 channels to cap it as the height of the panel.
    double maxFreq = Integer.max(Integer.max(Arrays.stream(this.redDistr).max().getAsInt(),
                    Arrays.stream(this.blueDistr).max().getAsInt()),
            Integer.max(Arrays.stream(this.greenDistr).max().getAsInt(),
                    Arrays.stream(this.intensityDistr).max().getAsInt()));
    // update the y axis scale when needed.
    if (this.maxFrequency < maxFreq) {
      this.maxFrequency = maxFreq;
    }
    // scale the length of the bars based on the max frequency
    double heightFactor = ((double) this.getHeight() - 40) / this.maxFrequency;
    String[] graphChannels = new String[]{"red", "green", "blue", "intensity"};
    // A convenient start value
    int channelStart = 15;

    for (String channel : graphChannels) {
      for (int ii = 0; ii < this.maxVal + 1; ii++) {
        switch (channel) {
          case "red":
            g2d.setColor(Color.RED);
            g2d.drawRect((int) (channelStart + (ii * rectWidth)), 25, (int) rectWidth,
                    (int) (this.redDistr[ii] * heightFactor));
            break;
          case "green":
            g2d.setColor(Color.GREEN);
            g2d.drawRect((int) (channelStart + (ii * rectWidth)), 25, (int) rectWidth,
                    (int) (this.greenDistr[ii] * heightFactor));
            break;
          case "blue":
            g2d.setColor(Color.BLUE);
            g2d.drawRect((int) (channelStart + (ii * rectWidth)), 25, (int) rectWidth,
                    (int) (this.blueDistr[ii] * heightFactor));
            break;
          case "intensity":
            g2d.setColor(Color.WHITE);
            g2d.drawRect((int) (channelStart + (ii * rectWidth)), 25, (int) rectWidth,
                    (int) (this.intensityDistr[ii] * heightFactor));
            break;
          default:
            throw new IllegalStateException("WTF");
        }
      }
      channelStart += ((this.maxVal + 1) * rectWidth);
    }

    // reset the graphics settings
    g2d.setColor(Color.BLACK);
    g2d.setTransform(ogTransform);
  }
}
