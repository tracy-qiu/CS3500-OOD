package view;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;


import javax.swing.filechooser.FileNameExtensionFilter;

import model.IImage;

/**
 * Class that represents the GUI view for the Image Processor program. Constructs the actual GUI
 * that the user will interact with (panels, buttons, etc.). Associates buttons with specific
 * actions so that they can be used by the user to achieve some functionality.
 */
public class ImageGraphicsView extends JFrame implements GUIView {
  private JButton loadButton;
  private JButton saveButton;
  private JButton greyscaleButton;
  private JButton redComponentButton;
  private JButton blueComponentButton;
  private JButton greenComponentButton;
  private JButton valueComponentButton;
  private JButton intensityComponentButton;
  private JButton lumaComponentButton;
  private JButton brightenButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton sepiaButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private ImagePanel imagePanel;
  private HistogramPanel histogramPanel;
  private JTextField input;
  private JOptionPane message;

  /**
   * Constructor for the GUI view of the Image Processor program. Builds the GUI design (panels,
   * buttons, etc. that will be displayed). Essentially, formats the GUI.
   */
  public ImageGraphicsView() {
    super();
    this.setTitle("Image Processor");
    this.setSize(1000, 650);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());


    JPanel fullImagePanel = new JPanel();
    fullImagePanel.setPreferredSize(new Dimension(600, 600));
    imagePanel = new ImagePanel();
    imagePanel.setLayout(new FlowLayout());
    imagePanel.setPreferredSize(new Dimension(5000, 5000));
    JLabel imageLabel = new JLabel();
    imageLabel.setText("Image");

    JScrollPane scrollbar = new JScrollPane(imagePanel);
    scrollbar.setPreferredSize(new Dimension(500, 500));
    scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    fullImagePanel.add(scrollbar);
    fullImagePanel.add(imageLabel);
    this.add(fullImagePanel, BorderLayout.CENTER);

    JPanel fullHistogramPanel = new JPanel();
    fullHistogramPanel.setPreferredSize(new Dimension(320, 360));
    histogramPanel = new HistogramPanel();
    histogramPanel.setLayout(new FlowLayout());
    histogramPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    histogramPanel.setPreferredSize(new Dimension(300, 300));
    JLabel histogramLabel = new JLabel();
    histogramLabel.setText("Histogram");
    histogramPanel.add(histogramLabel);

    fullHistogramPanel.add(histogramPanel);
    this.add(fullHistogramPanel, BorderLayout.WEST);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
    JLabel buttonLabel = new JLabel();
    buttonLabel.setText("Operations");
    buttonPanel.add(buttonLabel);
    this.add(buttonPanel, BorderLayout.EAST);

    // load button
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");
    buttonPanel.add(loadButton);

    // save button
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    buttonPanel.add(saveButton);

    // greyscale button
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale");
    buttonPanel.add(greyscaleButton);

    // red-component button
    redComponentButton = new JButton("Red-Component");
    redComponentButton.setActionCommand("Red-Component");
    buttonPanel.add(redComponentButton);

    // blue-component button
    blueComponentButton = new JButton("Blue-Component");
    blueComponentButton.setActionCommand("Blue-Component");
    buttonPanel.add(blueComponentButton);

    // green-component button
    greenComponentButton = new JButton("Green-Component");
    greenComponentButton.setActionCommand("Green-Component");
    buttonPanel.add(greenComponentButton);

    // intensity-component button
    intensityComponentButton = new JButton("Intensity-Component");
    intensityComponentButton.setActionCommand("Intensity-Component");
    buttonPanel.add(intensityComponentButton);

    // value-component button
    valueComponentButton = new JButton("Value-Component");
    valueComponentButton.setActionCommand("Value-Component");
    buttonPanel.add(valueComponentButton);

    // luma-component button
    lumaComponentButton = new JButton("Luma-Component");
    lumaComponentButton.setActionCommand("Luma-Component");
    buttonPanel.add(lumaComponentButton);

    // horizontal flip button
    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setActionCommand("Horizontal Flip");
    buttonPanel.add(horizontalFlipButton);

    // vertical flip button
    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setActionCommand("Vertical Flip");
    buttonPanel.add(verticalFlipButton);

    // Brighten flip button
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    buttonPanel.add(brightenButton);

    input = new JTextField(1);
    input.setPreferredSize(new Dimension(1, 1));
    buttonPanel.add(input);

    // Sepia flip button
    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    buttonPanel.add(sepiaButton);

    // Blur flip button
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    buttonPanel.add(blurButton);

    // Sharpen flip button
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    buttonPanel.add(sharpenButton);

    //quit button

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);
    this.makeVisible();
  }


  /**
   * Method that makes the GUI window visible to the user.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Method that associates the buttons of the GUi with a particular functionality.
   *
   * @param actionEvent represents "action" associated with the button.
   */
  @Override
  public void setListener(ActionListener actionEvent) {
    loadButton.addActionListener(actionEvent);
    saveButton.addActionListener(actionEvent);
    greyscaleButton.addActionListener(actionEvent);
    redComponentButton.addActionListener(actionEvent);
    greenComponentButton.addActionListener(actionEvent);
    blueComponentButton.addActionListener(actionEvent);
    lumaComponentButton.addActionListener(actionEvent);
    intensityComponentButton.addActionListener(actionEvent);
    valueComponentButton.addActionListener(actionEvent);
    horizontalFlipButton.addActionListener(actionEvent);
    verticalFlipButton.addActionListener(actionEvent);
    brightenButton.addActionListener(actionEvent);
    input.addActionListener(actionEvent);
    sepiaButton.addActionListener(actionEvent);
    blurButton.addActionListener(actionEvent);
    sharpenButton.addActionListener(actionEvent);
  }

  /**
   * Method that gets user inputs and sends them to the commands that require additional information
   * to function. Includes load, save and brighten commands.
   *
   * @param s represents user input (string text input).
   * @return String input that will be passed to the command.
   */
  public String getInput(String s) throws IllegalArgumentException {
    switch (s) {
      case ("load"):
      case ("save"):
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG, PPM & BMP Images", "jpg", "png", "ppm", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ImageGraphicsView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          String filename = fchooser.getSelectedFile().toPath().toString();
          return filename;
        }
        break;
      case ("brighten"):
        String command = this.input.getText();
        this.input.setText("");
        return command;
      default:
        break;
    }
    throw new IllegalArgumentException("Invalid input");
  }

  /**
   * Method that updates the GUI if changes have been made.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Method that sends message to the user interacting with the GUI.
   *
   * @param message String message that is rendered
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Method that passes an IImage to the ImagePanel class so that it can be rendered in the GUI.
   *
   * @param image image implementation for the Image Processor.
   */
  @Override
  public void renderImage(IImage image) {
    imagePanel.renderImageHelper(image);
    this.refresh();
  }

  /**
   * Method that passes histogram data from an Image to the GUI so that it can be rendered.
   *
   * @param histogramArray histogram data from an image in the model.
   */
  @Override
  public void renderHistogram(int[][] histogramArray) {
    histogramPanel.renderHistogramHelper(histogramArray);
    this.refresh();
  }

}
