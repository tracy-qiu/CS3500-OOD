package controller;


import java.util.HashMap;
import java.util.Map;

import controller.runnables.BlueButtonAction;
import controller.runnables.BlurButtonAction;
import controller.runnables.BrightenButtonAction;
import controller.runnables.DarkenButtonAction;
import controller.runnables.FlipHButtonAction;
import controller.runnables.FlipVButtonAction;
import controller.runnables.GreenButtonAction;
import controller.runnables.GreyscaleButtonAction;
import controller.runnables.IntensityButtonAction;
import controller.runnables.LoadButtonAction;
import controller.runnables.LumaButtonAction;
import controller.runnables.MosaicButtonAction;
import controller.runnables.OpenButtonAction;
import controller.runnables.RedButtonAction;
import controller.runnables.RefreshButtonAction;
import controller.runnables.SaveButtonAction;
import controller.runnables.SepiaButtonAction;
import controller.runnables.SharpenButtonAction;
import controller.runnables.ValueButtonAction;
import model.ImageProcessor;
import view.GUIView;

/**
 * An asynchronous controller for the GUI view.
 */
public class GUIController implements ImageProcessorController {

  private final ImageProcessor model;

  private final GUIView view;

  /**
   * Constructs a GUI controller with the given model and view.
   *
   * @param model the model
   * @param view  the view
   */
  public GUIController(ImageProcessor model, GUIView view) {
    this.model = model;
    this.view = view;
  }


  private void configureButler() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Load Button", new LoadButtonAction(this.model, this.view));
    buttonClickedMap.put("Save Button", new SaveButtonAction(this.model, this.view));
    buttonClickedMap.put("Open Button", new OpenButtonAction(this.model, this.view));
    buttonClickedMap.put("Red Component Button", new RedButtonAction(this.model, this.view));
    buttonClickedMap.put("Green Component Button", new GreenButtonAction(this.model, this.view));
    buttonClickedMap.put("Blue Component Button", new BlueButtonAction(this.model, this.view));
    buttonClickedMap.put("Value Button", new ValueButtonAction(this.model, this.view));
    buttonClickedMap.put("Intensity Button", new IntensityButtonAction(this.model, this.view));
    buttonClickedMap.put("Luma Button", new LumaButtonAction(this.model, this.view));
    buttonClickedMap.put("Brighten Button", new BrightenButtonAction(this.model, this.view));
    buttonClickedMap.put("Darken Button", new DarkenButtonAction(this.model, this.view));
    buttonClickedMap.put("Flip Horizontal Button", new FlipHButtonAction(this.model, this.view));
    buttonClickedMap.put("Flip Vertical Button", new FlipVButtonAction(this.model, this.view));
    buttonClickedMap.put("Blur Button", new BlurButtonAction(this.model, this.view));
    buttonClickedMap.put("Sharpen Button", new SharpenButtonAction(this.model, this.view));
    buttonClickedMap.put("Sepia Button", new SepiaButtonAction(this.model, this.view));
    buttonClickedMap.put("Greyscale Button", new GreyscaleButtonAction(this.model, this.view));
    buttonClickedMap.put("Mosaic Button", new MosaicButtonAction(this.model, this.view));
    buttonClickedMap.put("Refresh GUI Button", new RefreshButtonAction(this.view));

    buttonListener.setButtonClickedActionMap(buttonClickedMap);

    this.view.addActionListener(buttonListener);

  }

  /**
   * Start the imgprocessor and configure the buttons.
   * Execute if the input is valid
   *
   * @throws IllegalStateException if the input is invalid or impossible right now.
   */
  @Override
  public void startProcessing() throws IllegalStateException {
    this.configureButler();
  }
}
