package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageUtil;
import model.PixelImage;

/**
 * The frame of the graphics for the GUI Image Processor.
 */
public class SwingImageProcessorView extends JFrame implements GUIView {


  private final HistoPanel histogram;

  private JLabel currImage;

  private JSplitPane optionsAndStuff;
  private JSplitPane imgAndStuff;

  private JButton[] buttons;


  private String currImgName;


  /**
   * Constructs our Swing view of the image processor.
   */
  public SwingImageProcessorView(String caption) {
    super(caption);
    setPreferredSize(new Dimension(1600, 900));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    this.add(mainPanel);

    JPanel options = new JPanel();
    options.setLayout(new FlowLayout());
    this.buttons = new JButton[19];
    String[] buttonNames =
            new String[]{"Load", "Save", "Open", "Red Component", "Green Component",
                "Blue Component", "Value", "Intensity", "Luma", "Brighten", "Darken",
                "Flip Horizontal", "Flip Vertical", "Blur", "Sharpen", "Sepia", "Greyscale",
                "Mosaic", "Refresh GUI"};
    for (int ii = 0; ii < this.buttons.length; ii++) {
      JButton currButt = new JButton(buttonNames[ii]);
      currButt.setActionCommand(buttonNames[ii] + " Button");
      options.add(currButt);
      this.buttons[ii] = currButt;
    }

    this.histogram = new HistoPanel();

    // main vert pane
    this.currImage = new JLabel(new ImageIcon());
    JScrollPane imgScroll = new JScrollPane(this.currImage);
    imgScroll.setBackground(Color.lightGray);
    imgScroll.setBorder(BorderFactory.createTitledBorder(null, "Image",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("sans serif", Font.ITALIC,
                    16), Color.BLUE));
    optionsAndStuff = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    options.setBackground(Color.lightGray);
    options.setBorder(BorderFactory.createTitledBorder(null, "Options",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("sans serif", Font.ITALIC,
                    16), Color.BLUE));
    optionsAndStuff.setTopComponent(options);


    // Bottom split into to horiz panes
    imgAndStuff = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    imgAndStuff.setLeftComponent(imgScroll);

    this.histogram.setBackground(Color.lightGray);
    this.histogram.setBorder(BorderFactory.createTitledBorder(null, "Histogram",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("sans serif", Font.ITALIC,
                    16), Color.BLUE));


    imgAndStuff.setRightComponent(this.histogram);
    imgAndStuff.setDividerSize(8);
    imgAndStuff.setContinuousLayout(true);
    imgAndStuff.setOneTouchExpandable(true);

    optionsAndStuff.setBottomComponent(imgAndStuff);
    optionsAndStuff.setDividerSize(8);
    optionsAndStuff.setContinuousLayout(true);
    optionsAndStuff.setOneTouchExpandable(true);
    mainPanel.add(optionsAndStuff, BorderLayout.CENTER);


    pack();
    setVisible(true);
    this.refresh();

  }

  /**
   * Redraws the view.
   */
  @Override
  public void refresh() {
    // display the correct image
    // redraw the histogram
    imgAndStuff.setResizeWeight(0.5);
    optionsAndStuff.setResizeWeight(0.50);


    imgAndStuff.setDividerLocation(0.75);
    optionsAndStuff.setDividerLocation(0.15);
    this.repaint();

  }

  /**
   * Sets the ActionListener of the button commands to the given ActionListener.
   *
   * @param listener the given ActionLister for the button command
   */
  @Override
  public void addActionListener(ActionListener listener) {
    for (int ii = 0; ii < this.buttons.length; ii++) {
      this.buttons[ii].addActionListener(listener);
    }
  }

  /**
   * Shows the current image on the screen.
   *
   * @param image the image to display.
   */
  @Override
  public void setDisplayImage(PixelImage image) {
    this.currImage.setIcon(new ImageIcon(ImageUtil.pixelToBuffered(image)));
  }

  /**
   * Prompts the user to provide a file to load into the img processor.
   *
   * @return a file object
   */
  @Override
  public File takeFileInput() throws IOException {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images",
            "jpg", "gif", "png", "bmp", "ppm", "jpeg");
    fileChooser.setFileFilter(filter);
    int retValue = fileChooser.showOpenDialog(SwingImageProcessorView.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f;
    } else {
      throw new IOException("Invalid File");
    }
  }

  /**
   * Prompts the user for a text box.
   *
   * @return the string the user typed
   */
  @Override
  public String getUserStringInput(String question) {
    return JOptionPane.showInputDialog(question);
  }

  /**
   * Sets the image name so that the view can pass it to the controller
   * to do stuff.
   *
   * @param imgName name of the img
   */
  @Override
  public void setCurrImageName(String imgName) {
    this.currImgName = imgName;
  }

  /**
   * Checks if an image is currently loaded.
   *
   * @return true if yes.
   */
  @Override
  public boolean checkIfLoaded() {
    return (this.currImgName != null);
  }

  /**
   * An observation on the view that observes the name of the image that is being displayed.
   *
   * @return a string representing the image name.
   */
  @Override
  public String currImageName() {
    return this.currImgName;
  }

  /**
   * Redraws the histogram.
   *
   * @param distributions the rgbi channels
   * @param maxValue      max val of the image
   */
  @Override
  public void reDrawHisto(int[][] distributions, int maxValue) {
    this.histogram.setRedDistr(distributions[0]);
    this.histogram.setGreenDistr(distributions[1]);
    this.histogram.setBlueDistr(distributions[2]);
    this.histogram.setIntensityDistr(distributions[3]);
    this.histogram.setMaxVal(maxValue);
    this.histogram.repaint();
    this.histogram.setVisible(true);
  }


  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void transmitMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message);
  }
}
