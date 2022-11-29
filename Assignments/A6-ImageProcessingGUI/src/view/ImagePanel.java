package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.ImageUtil;
import model.IImage;


/**
 * Class represents the image panel section of the GUI of the ImageProcessor. Actually
 * displays the image that the user can interact with.
 */
public class ImagePanel extends JPanel {
  private BufferedImage image;

  /**
   * Constructor of the ImagePanel class. Initialized to be empty.
   */
  public ImagePanel() {
    super();
    this.image = null;
  }

  /**
   * Method that draws the image on the GUI.
   *
   * @param g the <code>Graphics</code> object to protect.
   */
  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.image, 10, 10, this);
  }

  /**
   * Helper method that initializes the image in the Image Panel. Converts ImageImpl to a buffered
   * image to be displayed.
   *
   * @param inputImage image implementation to be displayed in te GUI.
   */
  protected void renderImageHelper(IImage inputImage) {
    this.image = ImageUtil.writeImageFile(inputImage);
  }
}
